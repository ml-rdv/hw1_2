package PracticeJavaMemoryModelLinksObject;

/**
 * Написать метод (с тестами), который:
 * Принимает строку, если строка пустая, то метод возвращает пустую строку.
 * Входящую строку нужно очистить от пробелов в начале и в конце.
 * Входящую строку нужно разделить на массив по символу “;”. Если в массиве меньше 3х элементов, то метод возвращает пустую строку.
 * Первую строку в массиве нужно преобразовать к верхнему регистру, если она начинается со слова “test”.
 * Во второй строке нужно заменить все подстроки “abc” на “сba”, если такие подстроки имеются.
 * В третьей строке, если 5 символ равен символу “X’’, то обрезать строку до этого символа.
 * Остальные строки в массиве игнорируются.
 * Первые 3 преобразованные строки массива нужно объединить в одну строку с разделителем “|”.
 * Метод должен вернуть строку вида “result join = {итоговая строка} and array strings size = {кол-во элементов в массиве строк}” (Использовать String.format()).
 * Пример:
 * Входящая строка: “  testString;abc123abc;!testXstring;foo;bar  ”
 * Исходящая строка: “TESTSTRING|cba123cba|Xstring and array strings size = 5”
 */

public class Task6 {
    public static String method(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
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
        String strResult = String.join("|", strings[0], strings[1], strings[2]);
        return String.format("result join = %s and array strings size = %d", strResult, strings.length);
    }
}
