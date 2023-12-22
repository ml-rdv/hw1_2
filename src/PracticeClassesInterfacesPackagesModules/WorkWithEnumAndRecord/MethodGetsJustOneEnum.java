package PracticeClassesInterfacesPackagesModules.WorkWithEnumAndRecord;

import java.time.Year;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

/**
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

public class MethodGetsJustOneEnum {

    private static final Season[] seasons = {
            Season.Winter, Season.Winter, Season.Spring, Season.Spring, Season.Spring, Season.Summer,
            Season.Summer, Season.Summer, Season.Autumn, Season.Autumn, Season.Autumn, Season.Winter
    };

    public static MonthWithInfo makeRecord(Month month) {
        int index = Month.valueOf(month.name()).ordinal() + 1;

        YearMonth yearMonthObject = YearMonth.of(Year.now().getValue(), index);
        int daysInMonth = yearMonthObject.lengthOfMonth();

        java.time.Month monthCopy = java.time.Month.of(index);
        Locale loc = Locale.forLanguageTag("ru");
        String name = monthCopy.getDisplayName(TextStyle.FULL_STANDALONE, loc);
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        Season s = seasons[index - 1];

//      second variant (using in class MethodGetsArrayOfEnums)
//      Season s = Season.of(month);

        return new MonthWithInfo(s, daysInMonth, index, name);
    }
}
