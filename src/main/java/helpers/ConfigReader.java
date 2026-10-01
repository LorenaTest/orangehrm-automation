package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

    private static final String CONFIG_PATH = "resources/config.properties";
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream(CONFIG_PATH)) {
            properties.load(input);
            logger.info("Configuracion leida de {}", CONFIG_PATH);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer " + CONFIG_PATH, e);
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
