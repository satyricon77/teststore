package org.volovich.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private static final String PROP_FILE = "/application.properties";
    private static PropertyLoader instance = null;
    private Properties properties;

    public synchronized static PropertyLoader getInstance() {
        if (instance == null){
            instance = new PropertyLoader();
        }
        return instance;
    }

    private PropertyLoader() {
        properties = new Properties();
        try {
            properties.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadProperty(String name) {
        return getInstance().properties.getProperty(name);
    }
}
