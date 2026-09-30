package utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    private RandomUtil() {
    }

    public static String generateEmployeeId() {
        return String.valueOf(
                ThreadLocalRandom.current().nextInt(10000, 99999)
        );
    }

    public static String generateUsername(String firstName, String lastName) {
        return firstName.toLowerCase() + "." +
                lastName.toLowerCase() +
                ThreadLocalRandom.current().nextInt(100, 999);
    }

    public static String generatePassword() {
        return "Password" +
                ThreadLocalRandom.current().nextInt(100, 999) +
                "!";
    }
}
