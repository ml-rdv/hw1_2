package PracticeClassesInterfacesPackagesModules.WorkWithEnumAndRecord;

import java.time.Year;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Изначально  неправильно поняла условие задачи и подумала, что метод должед принимать
 * массив, состоящий из Enum
 * Поэтому решила уже оставить этой способ и второй в классе MethodGetsJustOneEnum
 * <p>
 * Задача:
 * Реализовать метод (с тестами),  который
 *    Принимает Enum с перечислением названиев месяцев
 *    Определяет по месяцу следующие признаки
 *          К какому времени года относится
 *          Кол-во дней (если текущий год високосный, то у февраля должно быть 29)
 *          Порядковый номер в году
 *          Название на русском
 *    Возвращает record, который содержит вышеописанные признаки
 */
public class MethodGetsArrayOfEnums {
    public static <T extends Enum<T>> ArrayList<MonthWithInfo> checkMonth(T[] values) {
        System.out.println(java.util.Arrays.asList(values));
        ArrayList<MonthWithInfo> monthWithInformation = new ArrayList<>();
        for (T month : values) {
            MonthWithInfo monthWithInfo = makeRecord((Month) month);
            monthWithInformation.add(monthWithInfo);
        }
        return monthWithInformation;
    }

    public static MonthWithInfo makeRecord(Month month) {
        int index = month.ordinal() + 1;

        YearMonth yearMonthObject = YearMonth.of(Year.now().getValue(), index);
        int daysInMonth = yearMonthObject.lengthOfMonth();

        java.time.Month monthCopy = java.time.Month.of(index);
        Locale loc = Locale.forLanguageTag("ru");
        String name = monthCopy.getDisplayName(TextStyle.FULL_STANDALONE, loc);
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        Season s = Season.of(month);

        return new MonthWithInfo(s, daysInMonth, index, name);
    }

    public static void main(String[] args) {
        ArrayList<MonthWithInfo> monthWithInformation = checkMonth(new Month[]{Month.January, Month.February});
        for (MonthWithInfo m : monthWithInformation) {
            System.out.println(m);
        }
    }
}
