package ai.azati.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is responsible for parsing Properties file from
 * resources/application.properties in classpath.
 *
 * The file is mainly needed to set the base URL for API.
 */
public class Parser {

    public static final String PROPERTIES_FILENAME = "application.properties";

    public static Properties parseProperties() throws IOException {
        Properties properties;
        try(InputStream is = Parser.class.getClassLoader().getResourceAsStream(PROPERTIES_FILENAME)) {
            properties = new Properties();
            properties.load(is);
            return properties;
        }
    }
}
