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
package net.larry1123.elec.util.logger;

import net.larry1123.elec.util.factorys.FactoryManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.*;

/**
 * @author Larry1123
 * @since 10/19/2014 - 4:37 AM
 */
public class UtilFileHandler extends Handler {

    protected final String filePath;
    protected FileHandler fileHandler;

    protected volatile Filter filter;
    protected volatile Formatter formatter;
    protected volatile Level logLevel = Level.ALL;
    protected volatile ErrorManager errorManager = new ErrorManager();
    protected volatile String encoding;

    public UtilFileHandler(String filePath) throws IOException {
        if (filePath == null) { throw new NullPointerException(); }
        this.filePath = filePath;
        filter = new UtilFilter();
        ((UtilFilter) filter).setLogAll(true);
        formatter = new UtilsLogFormat();
        encoding = "UTF-8";
        fileHandler = FileManager.getHandler(this);
    }

    @Override
    public synchronized void publish(LogRecord record) {
        getFileHandler().publish(record);
    }

    @Override
    public synchronized void close() throws SecurityException {
        getFileHandler().close();
    }

    /**
     * Returns the filePath along with the file's name and extension.
     * This pattern has %g added to the file's name.
     * A return could look like this:
     * logs/LoggerName.%u.log
     *
     * @return path_SplitDateTime.%g.FileType
     */
    public String getFilePattern() {
        String path = getFilePath();
        if (!getConfig().getSplit().equals(FileSplits.NONE)) {
            path += "_" + FileManager.dateTime();
        }
        path += ".%u." + getConfig().getFileType();
        return path;
    }

    @Override
    public synchronized void flush() {
        getFileHandler().flush();
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public synchronized void setFormatter(Formatter newFormatter) throws SecurityException {
        if (newFormatter.equals(getFormatter())) { return; }
        getFileHandler().setFormatter(newFormatter);
        formatter = newFormatter;
    }

    @Override
    public Formatter getFormatter() {
        return formatter;
    }

    protected LoggerSettings getConfig() {
        return FactoryManager.getFactoryManager().getEELoggerFactory().getLoggerSettings();
    }

    @Override
    public synchronized void setEncoding(String encoding) throws UnsupportedEncodingException {
        if (encoding.equals(getEncoding())) { return; }
        getFileHandler().setEncoding(encoding);
        this.encoding = encoding;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }

    @Override
    public synchronized void setFilter(Filter newFilter) throws SecurityException {
        if (newFilter.equals(getFilter())) { return; }
        getFileHandler().setFilter(newFilter);
        filter = newFilter;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    @Override
    public synchronized void setErrorManager(ErrorManager em) {
        if (em.equals(getErrorManager())) { return; }
        getFileHandler().setErrorManager(em);
        errorManager = em;
    }

    @Override
    public ErrorManager getErrorManager() {
        return errorManager;
    }

    @Override
    public synchronized void setLevel(Level newLevel) throws SecurityException {
        if (newLevel.equals(getLevel())) { return; }
        getFileHandler().setLevel(newLevel);
        logLevel = newLevel;
    }

    @Override
    public Level getLevel() {
        return logLevel;
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return getFileHandler().isLoggable(record);
    }

    public boolean updateFileHandler() {
        try {
            FileHandler tempFileHandler = FileManager.getHandler(this);
            if (tempFileHandler.equals(fileHandler)) {
                return false;
            }
            else {
                fileHandler = tempFileHandler;
                return true;
            }
        }
        catch (IOException e) {
            return false;
        }
    }

    public FileHandler getFileHandler() {
        if (fileHandler == null) {
            updateFileHandler();
        }
        return fileHandler;
    }

}