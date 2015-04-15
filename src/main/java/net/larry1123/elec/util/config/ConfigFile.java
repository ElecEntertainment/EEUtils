/*
 * Copyright 2014 ElecEntertainment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.larry1123.elec.util.config;

import net.larry1123.elec.util.config.fieldhanders.FieldHandler;
import net.larry1123.elec.util.factorys.FactoryManager;
import net.larry1123.elec.util.factorys.FieldHandlerFactory;
import net.visualillusionsent.utils.PropertiesFile;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

public class ConfigFile {

    private final ConfigBase config;

    /**
     * Holds a list of usable Fields
     */
    protected Field[] configFields = new Field[0];

    /**
     * Creates a ConfigFile controller for a given PropertiesFile
     * Will load data from file to ConfigBase
     *
     * @param config ConfigBase to use
     */
    public ConfigFile(ConfigBase config) {
        this.config = config;
        collectFields(getConfigBase(), null);
        load();
    }

    /**
     * Will search Class and super Classes to find fields for use and add them to our known field list.
     *
     * @param config    The Object to use to search
     * @param lastClass Mostly an internal PlaceHolder
     */
    protected void collectFields(ConfigBase config, Class lastClass) {
        Class currentClass = lastClass == null ? config.getClass() : lastClass.getSuperclass();
        for (Field field : currentClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigField.class)) {
                configFields = ArrayUtils.add(configFields, field);
            }
        }
        if (currentClass.getSuperclass() != null) { collectFields(config, currentClass); }
    }

    /**
     * Gets the controlled PropertiesFile
     *
     * @return PropertiesFile being controlled
     */
    public PropertiesFile getPropFile() {
        return getConfigBase().getPropertiesFile();
    }

    /**
     * Updates the ConfigBase to match the current file without saving first
     */
    public void reload() {
        getPropFile().reload();
        load();
    }

    /**
     * Load data from disk and if a key is miss it will create it.
     */
    public void load() {
        for (Field field : configFields) {
            FieldHandler<?> fieldHandler = getFieldHandlerFactory().createFieldHandler(field.getGenericType(), field, getConfigBase());
            fieldHandler.load();
            fieldHandler.setComments();
        }
        // Well lets SAVE!!!
        getPropFile().save();
    }

    /**
     * Saves data to disk if any change has been made
     */
    public void save() {
        for (Field field : configFields) {
            FieldHandler<?> fieldHandler = getFieldHandlerFactory().createFieldHandler(field.getGenericType(), field, getConfigBase());
            fieldHandler.save();
            fieldHandler.setComments();
        }
        // Well lets SAVE!!!
        getPropFile().save();
    }

    protected FieldHandlerFactory getFieldHandlerFactory() {
        return FactoryManager.getFactoryManager().getMainFieldHandlerFactory();
    }

    /**
     * The ConfigBase to base the PropertiesFile on
     */
    public ConfigBase getConfigBase() {
        return config;
    }

}
