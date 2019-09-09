package ru.netcrackeredu.SivaschenkoPavel.consoleCalc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static ru.netcrackeredu.SivaschenkoPavel.consoleCalc.ExitConstants.FILE_NOT_FOUND;
import static ru.netcrackeredu.SivaschenkoPavel.consoleCalc.ExitConstants.IO_ERROR;

public class PropertiesReader {

    private FileInputStream configStream;
    private Properties properties;

    private static String getAbsPath() {
        return System.getProperty("user.dir");
    }

    private FileInputStream getSteam(String filename) throws FileNotFoundException {
        return new FileInputStream(filename);
    }

    public PropertiesReader(String filename) {
        try {
            this.configStream = getSteam(filename);
            this.properties = new Properties();
            this.properties.load(configStream);
        } catch (FileNotFoundException fnf_exception) {
            System.out.println(fnf_exception.getLocalizedMessage());
            System.exit(FILE_NOT_FOUND);
        } catch (IOException io_exception) {
            System.out.println(io_exception.getLocalizedMessage());
            closeStream();
            System.exit(IO_ERROR);
        }
    }


    public Object[] getKeys() {
        return this.properties.stringPropertyNames().toArray();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void closeStream() {
        try {
            this.configStream.close();
        } catch (IOException io_exception) {
            System.out.println(io_exception.getLocalizedMessage());
            System.exit(IO_ERROR);
        }
    }
}
