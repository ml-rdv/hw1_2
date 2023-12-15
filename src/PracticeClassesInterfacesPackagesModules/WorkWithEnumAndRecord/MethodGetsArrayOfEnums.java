package PracticeClassesInterfacesPackagesModules.WorkWithEnumAndRecord;

import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;

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
        Season s = null;
        int index = 0;
        String name = null;
        switch (month) {
            case January -> {
                s = Season.Winter;
                index = 1;
                name = "Январь";
            }
            case February -> {
                s = Season.Winter;
                index = 2;
                name = "Февраль";
            }
            case March -> {
                s = Season.Spring;
                index = 3;
                name = "Март";
            }
            case April -> {
                s = Season.Spring;
                index = 4;
                name = "Апрель";
            }
            case May -> {
                s = Season.Spring;
                index = 5;
                name = "Май";
            }
            case June -> {
                s = Season.Summer;
                index = 6;
                name = "Июнь";
            }
            case July -> {
                s = Season.Summer;
                index = 7;
                name = "Июль";
            }
            case August -> {
                s = Season.Summer;
                index = 8;
                name = "Август";
            }
            case September -> {
                s = Season.Autumn;
                index = 9;
                name = "Сентябрь";
            }
            case October -> {
                s = Season.Autumn;
                index = 10;
                name = "Октябрь";
            }
            case November -> {
                s = Season.Autumn;
                index = 11;
                name = "Ноябрь";
            }
            case December -> {
                s = Season.Winter;
                index = 12;
                name = "Декабрь";
            }
            default -> System.out.println("It is impossible date.");
        }
        YearMonth yearMonthObject = YearMonth.of(Year.now().getValue(), index);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        return new MonthWithInfo(s, daysInMonth, index, name);
    }

    public static void main(String[] args) {
        ArrayList<MonthWithInfo> monthWithInformation = checkMonth(new Month[]{Month.January, Month.February});
        for (MonthWithInfo m : monthWithInformation) {
            System.out.println(m);
        }
    }
}
