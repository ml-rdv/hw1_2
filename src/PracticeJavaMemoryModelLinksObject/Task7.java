package PracticeJavaMemoryModelLinksObject;

/**
 * Написать метод (с тестами) без использования регулярных выражений, который принимает строку и определяет чем является строка . Примеры данных и типов:
 * “Иванов Иван Иванович” -> FullName
 * “test@test.ru” -> Email
 * “+7(999)9999999” -> PhoneNumber
 * “123456” -> Number
 * всё остальное -> Other
 */

public class Task7 {
    public static String stringDefinition(String str) {
        if (str == null || str.isEmpty()) {
            return "Other";
        }
        str = str.trim();
        // FullName
        String[] strings = str.split(" ");
        if (strings.length == 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < strings[i].length(); j++) {
                    char c = strings[i].charAt(j);
                    if (!(c >= 'А' && c <= 'Я') && !(c >= 'а' && c <= 'я')) {
                        return "Other";
                    }
                }
            }
            return "FullName";
        }
        // Email
        if (str.contains("@") && (str.contains("."))
                && str.indexOf("@") > 0 && str.length() > str.indexOf("@") + 1) {
            str = str.replaceFirst("@", "");
            str = str.replaceAll("\\.", "");
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z') && !(c >= '0' && c <= '9') && c != '.') {
                    return "Other";
                }
            }
            return "Email";
        }
        // PhoneNumber
        if (str.charAt(0) == '+' && str.charAt(2) == '(' && str.charAt(6) == ')') {
            for (int j = 1; j < str.length(); j++) {
                if (j == 2 || j == 6) {
                    continue;
                }
                char c = str.charAt(j);
                if (!(c >= '0' && c <= '9')) {
                    return "Other";
                }
            }
            return "PhoneNumber";
        }
        // Number
        for (int j = 0; j < str.length(); j++) {
            char c = str.charAt(j);
            if (!(c >= '0' && c <= '9')) {
                return "Other";
            }
        }
        return "Number";
    }
}
