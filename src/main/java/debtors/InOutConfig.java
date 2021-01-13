package debtors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.ini4j.*;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class InOutConfig {
    private static final Logger LOG = LoggerFactory.getLogger(InOutConfig.class);
    private static final String PATH_CONFIG = "config.ini";

    public static String readParameter(String sectionName, String parametrName) {
        try {
            Ini ini = new Ini();
            File file = new File(PATH_CONFIG);
            ini.load(file);
            Ini.Section section = ini.get(sectionName);
            String parameter = section.get(parametrName);
            return new String(parameter.getBytes(), "windows-1251");
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
            return "";
        } catch (Exception e) {
            LOG.error("Error reading ini " + e.getMessage());
            return "";
        }   
    }

    public static boolean writeParameter(String sectionName, String parametrName, String newValue) {        
        try {
            Ini ini = new Ini();
            File file = new File(PATH_CONFIG);
            ini.load(file);
            ini.put(sectionName, parametrName, newValue);
            ini.store(file);
            return true;
        } catch (Exception e) {
            LOG.error("Error writing ini " + e.getMessage());
            return false;
        }   
    }
}