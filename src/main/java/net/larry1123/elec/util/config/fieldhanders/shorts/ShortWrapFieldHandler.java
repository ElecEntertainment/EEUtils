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
package net.larry1123.elec.util.config.fieldhanders.shorts;

import net.larry1123.elec.util.config.ConfigBase;
import net.larry1123.elec.util.config.fieldhanders.FieldHandler;
import net.visualillusionsent.utils.UtilityException;

import java.lang.reflect.Field;

/**
 * @author Larry1123
 * @since 4/30/2014 - 3:56 AM
 */
public class ShortWrapFieldHandler extends FieldHandler<Short> {

    public ShortWrapFieldHandler(Field field, ConfigBase configBase, String fieldName) throws NoSuchFieldException {
        super(field, configBase, fieldName);
    }

    public ShortWrapFieldHandler(Field field, ConfigBase configBase) throws NoSuchFieldException {
        super(field, configBase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setToFile(Short value) {
        getPropertiesFile().setShort(getFieldName(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short getFromFile() {
        try {
            return getPropertiesFile().getShort(getFieldName());
        }
        catch (UtilityException utilityException) {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short getFromField() {
        try {
            return (Short) getField().get(getConfig());
        }
        catch (IllegalAccessException e) {
            return 0;
        }
    }

}