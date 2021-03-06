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
package net.larry1123.elec.util.test;

import net.larry1123.elec.util.logger.FileSplits;
import net.larry1123.elec.util.logger.LoggerSettings;

import java.io.File;

/**
 * @author Larry
 * @since 4/8/2015 - 6:00 AM
 */
public class TestLoggerSettings implements LoggerSettings {

    private String loggerPath = "target" + File.separator + "tests" + File.separator + "logs" + File.separator;
    private boolean pastingAllowed = false;
    private FileSplits split = FileSplits.NONE;
    private long currentSplit = 0;
    private String fileType = "log";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoggerPath() {
        return loggerPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoggerPath(String path) {
        loggerPath = path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPastingAllowed() {
        return pastingAllowed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPastingAllowed(boolean state) {
        pastingAllowed = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileSplits getSplit() {
        return split;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileSplit(FileSplits fileSplit) {
        split = fileSplit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getCurrentSplit() {
        return currentSplit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentSplit(long current) {
        currentSplit = current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFileType() {
        return fileType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileType(String type) {
        fileType = type;
    }

}
