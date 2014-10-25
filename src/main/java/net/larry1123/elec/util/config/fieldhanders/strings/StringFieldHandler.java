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
package net.larry1123.elec.util.config.fieldhanders.strings;

import net.larry1123.elec.util.config.ConfigBase;
import net.larry1123.elec.util.config.fieldhanders.FieldHandler;
import net.visualillusionsent.utils.UtilityException;

import java.lang.reflect.Field;

/**
 * @author Larry1123
 * @since 4/29/2014 - 2:44 AM
 */
public class StringFieldHandler extends FieldHandler<String> {

    public StringFieldHandler(Field field, ConfigBase configBase, String fieldName) {
        super(field, configBase, fieldName);
    }

    public StringFieldHandler(Field field, ConfigBase configBase) {
        super(field, configBase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setToFile(String value) {
        getPropertiesFile().setString(getPropertyKey(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFromFile() {
        try {
            return getPropertiesFile().getString(getPropertyKey());
        }
        catch (UtilityException utilityException) {
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFromField() {
        try {
            return (String) getField().get(getConfig());
        }
        catch (IllegalAccessException e) {
            return "";
        }
    }

}
