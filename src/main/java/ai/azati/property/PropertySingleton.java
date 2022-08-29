package ai.azati.property;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * This class is needed for convenient access to
 * properties from anywhere in program.
 */
@Slf4j
public class PropertySingleton {

    private static Properties applicationProperties = null;

    private PropertySingleton() {
    }

    public static Properties getProperties() {
        if(applicationProperties == null) {
            try {
                applicationProperties = Parser.parseProperties();
            } catch (Exception ex) {
                log.error("Failed to parse properties: ", ex);
            }
        }
        return applicationProperties;
    }
}
