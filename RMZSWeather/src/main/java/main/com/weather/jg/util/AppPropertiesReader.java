package main.com.weather.jg.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppPropertiesReader
{
    public static Properties appProperties;

    private AppPropertiesReader() {
    }

    private static Properties readPropertiesFile() {
        Properties prop = new Properties();
        try (InputStream stream = AppPropertiesReader.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            prop.load(stream);
            return prop;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load properties file", e);
        }
    }

    public static Properties getAppProperties() {
        if (appProperties == null) {
            appProperties = readPropertiesFile();
        }
        return appProperties;
    }

}
