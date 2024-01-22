package PracticeClassesInterfacesPackagesModules.WorkWithEnumAndRecord;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;

import static PracticeClassesInterfacesPackagesModules.WorkWithEnumAndRecord.Month.*;

public class WorkWithEnumAndRecordTest {
    private static List<Arguments> provideParameters() {
        return List.of(
                Arguments.of(January, new MonthWithInfo(Season.Winter, 31, 1, "Январь")),
                Arguments.of(February, new MonthWithInfo(Season.Winter, 29, 2, "Февраль")),
                Arguments.of(March, new MonthWithInfo(Season.Spring, 31, 3, "Март")),
                Arguments.of(April, new MonthWithInfo(Season.Spring, 30, 4, "Апрель")),
                Arguments.of(May, new MonthWithInfo(Season.Spring, 31, 5, "Май")),
                Arguments.of(June, new MonthWithInfo(Season.Summer, 30, 6, "Июнь")),
                Arguments.of(July, new MonthWithInfo(Season.Summer, 31, 7, "Июль")),
                Arguments.of(August, new MonthWithInfo(Season.Summer, 31, 8, "Август")),
                Arguments.of(September, new MonthWithInfo(Season.Autumn, 30, 9, "Сентябрь")),
                Arguments.of(October, new MonthWithInfo(Season.Autumn, 31, 10, "Октябрь")),
                Arguments.of(November, new MonthWithInfo(Season.Autumn, 30, 11, "Ноябрь")),
                Arguments.of(December, new MonthWithInfo(Season.Winter, 31, 12, "Декабрь"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void information_about_every_month_should_be_right(Month month, MonthWithInfo monthWithInfo) {
        MonthWithInfo monthWithInformation = MethodGetsJustOneEnum.makeRecord(month);
        Assertions.assertEquals(monthWithInfo, monthWithInformation);
    }
}