package PracticeJavaMemoryModelLinksObject;

public class Task8 {
    public static String stringDefinition(String str) {
        if (str == null || str.isEmpty()) {
            return "Other";
        }
        str = str.trim();
        // FullName ????????
        if (str.matches("^[а-яА-Я]*(\\s)^[а-яА-Я]*(\\s)^[а-яА-Я]$")) {
            return "FullName";
        }

        // Email
        if (str.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return "Email";
        }
        // PhoneNumber
        if (str.matches("(\\+)\\d(\\()\\d{3}(\\))\\d{7}")) {
            return "PhoneNumber";
        }
        // Number
        if (str.matches("^\\d*$")) {
            return "Number";
        }
        return "Other";
    }
}