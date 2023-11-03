package PracticeJavaMemoryModelLinksObject;

public class Task6 {
    public static String method(String str) {
        if (str == null || str.isEmpty()) return "";
        String trim = str.trim();
        String[] strings = trim.split(";");
        if (strings.length < 3) {
            return "";
        }
        if (strings[0].startsWith("test")) {
            strings[0] = strings[0].toUpperCase();
        }
        if (strings[1].contains("abc")) {
            strings[1] = strings[1].replaceAll("abc", "cba");
        }
        String string = strings[2];
        if (string.length() >= 5 && string.charAt(5) == 'X') {
            strings[2] = string.substring(5);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(strings[i]);
            if (i < 2) {
                sb.append('|');
            }
        }
        return String.format("result join = %s and array strings size = %d", sb, strings.length);
    }
}
