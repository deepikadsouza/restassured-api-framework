package GenericUtility;
import java.util.Random;

public class JavaUtility {

    /**
     * Generates a random integer within a specific range.
     */
    public int getRandomNumber(int limit) {
        Random random = new Random();
        return random.nextInt(limit);
    }

    /**
     * Generates a random string (useful for unique names or emails).
     */
    public String getRandomString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }
}