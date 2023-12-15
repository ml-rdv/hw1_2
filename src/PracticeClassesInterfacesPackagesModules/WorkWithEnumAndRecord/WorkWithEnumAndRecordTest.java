package PracticeClassesInterfacesPackagesModules.WorkWithEnumAndRecord;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.Year;
import java.util.ArrayList;


public class WorkWithEnumAndRecordTest {
    @ParameterizedTest
    @EnumSource(Month.class)
    void information_about_every_month_should_be_right(Month month) {
        MonthWithInfo monthWithInformation = MethodGetsJustOneEnum.makeRecord(month);
        MonthWithInfo expectedElement = null;
        switch (month) {
            case January -> {
                expectedElement = new MonthWithInfo(Season.Winter, 31, 1, "Январь");
            }
            case February -> {
                int daysInMonth = Year.now().getValue() % 4 == 0 ? 29 : 28;
                expectedElement = new MonthWithInfo(Season.Winter, daysInMonth, 2, "Февраль");
            }
            case March -> {
                expectedElement = new MonthWithInfo(Season.Spring, 31, 3, "Март");
            }
            case April -> {
                expectedElement = new MonthWithInfo(Season.Spring, 30, 4, "Апрель");
            }
            case May -> {
                expectedElement = new MonthWithInfo(Season.Spring, 31, 5, "Май");
            }
            case June -> {
                expectedElement = new MonthWithInfo(Season.Summer, 30, 6, "Июнь");
            }
            case July -> {
                expectedElement = new MonthWithInfo(Season.Summer, 31, 7, "Июль");
            }
            case August -> {
                expectedElement = new MonthWithInfo(Season.Summer, 31, 8, "Август");
            }
            case September -> {
                expectedElement = new MonthWithInfo(Season.Autumn, 30, 9, "Сентябрь");
            }
            case October -> {
                expectedElement = new MonthWithInfo(Season.Autumn, 31, 10, "Октябрь");

            }
            case November -> {
                expectedElement = new MonthWithInfo(Season.Autumn, 30, 11, "Ноябрь");
            }
            case December -> {
                expectedElement = new MonthWithInfo(Season.Winter, 31, 12, "Декабрь");
            }
        }
        Assertions.assertEquals(expectedElement, monthWithInformation);
    }

    @Test
    void information_about_array_of_months_should_be_right() {
        ArrayList<MonthWithInfo> monthsWithInformation = MethodGetsArrayOfEnums.checkMonth(new Month[]{Month.January, Month.February});
        ArrayList<MonthWithInfo> expectedElement = new ArrayList<>();
        expectedElement.add(new MonthWithInfo(Season.Winter, 31, 1, "Январь"));
        int daysInMonth = Year.now().getValue() % 4 == 0 ? 29 : 28;
        expectedElement.add(new MonthWithInfo(Season.Winter, daysInMonth, 2, "Февраль"));
        Assertions.assertEquals(expectedElement, monthsWithInformation);
    }
}