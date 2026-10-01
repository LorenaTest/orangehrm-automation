package utils;

import java.util.concurrent.atomic.AtomicInteger;

public class RandomUtil {

    private static final AtomicInteger SEQUENCE = new AtomicInteger();

    private RandomUtil() {
    }

    public static String generateNameSuffix() {
        long value = System.currentTimeMillis() * 100 + SEQUENCE.incrementAndGet() % 100;
        StringBuilder suffix = new StringBuilder();
        while (value > 0 && suffix.length() < 7) {
            suffix.append((char) ('a' + value % 26));
            value /= 26;
        }
        return suffix.toString();
    }

    public static String generateEmployeeId() {
        long value = System.currentTimeMillis() % 10_000_000L * 100 + SEQUENCE.incrementAndGet() % 100;
        return String.valueOf(value);
    }

    public static String generateUsername(String firstName, String lastName) {
        return (firstName + "." + lastName).toLowerCase();
    }
}
