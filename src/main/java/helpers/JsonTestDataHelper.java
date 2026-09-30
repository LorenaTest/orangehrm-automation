package helpers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class JsonTestDataHelper {

    private static JsonTestDataHelper instance;
    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    private JsonTestDataHelper() {
    }

    public static JsonTestDataHelper getInstance() {
        if (instance == null) {
            synchronized (JsonTestDataHelper.class) {
                if (instance == null) {
                    instance = new JsonTestDataHelper();
                    logger.info("JsonTestDataHelper creado");
                }
            }
        }
        return instance;
    }

    public <T> Object[] getTestData(String filePath, Class<T> clazz) throws FileNotFoundException {
        logger.info("Leyendo datos de prueba de {}", filePath);
        JsonReader reader = new JsonReader(new FileReader(filePath));
        List<T> testDataList = new Gson()
                .fromJson(reader, TypeToken.getParameterized(ArrayList.class, clazz).getType());
        logger.info("Casos leidos: {}", testDataList.size());
        return testDataList.toArray();
    }
}
