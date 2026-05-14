package GenericUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Map;

public class JsonUtility {

    /**
     * Reads a JSON file and converts it into a Map. 
     * Useful for passing dynamic data into a request body.
     */
    public Map<String, Object> getJsonDataAsMap(String jsonFilePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(jsonFilePath), Map.class);
    }

    /**
     * Reads a specific value from a JSON file based on a key.
     */
    public String readJsonValue(String jsonFilePath, String key) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new File(jsonFilePath)).get(key).asText();
    }
}