package GenericUtility;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class FileUtility {

    /**
     * Reads a value from a .properties file given a key.
     * Useful for retrieving BaseURI, Username, or Password.
     */
    public String getDataFromProperties( String key) throws Exception {
        FileInputStream fis = new FileInputStream("./configenv/configdata.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String value = prop.getProperty(key);
        fis.close();
        return value;
    }

    /**
     * Updates or adds a key-value pair to a .properties file.
     */
    public void writeDataToProperties(String filePath, String key, String value) throws Exception {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(filePath);
        prop.load(fis);
        fis.close();

        prop.setProperty(key, value);
        FileOutputStream fos = new FileOutputStream(filePath);
        prop.store(fos, "Updated via Automation Framework");
        fos.close();
    }
}