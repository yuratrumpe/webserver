package com.yuratrumpe.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoaderImpl implements PropertiesLoader {

    @Override
    public Properties getPropertiesFromFile(String propertiesFile) {

        //ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ClassLoader classLoader = this.getClass().getClassLoader();

        //doesn't work with ClassLoader.getSystemClassLoader() dbResource == null
        InputStream resource = classLoader.getResourceAsStream(propertiesFile);

        Properties properties = new Properties();

        try {

            properties.load(resource);

        } catch (IOException e) {
            throw new RuntimeException("Load properties exception", e);
        }

        return properties;
    }
}
