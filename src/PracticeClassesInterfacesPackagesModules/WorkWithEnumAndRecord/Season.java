package PracticeClassesInterfacesPackagesModules.WorkWithEnumAndRecord;

import java.util.Objects;

public enum Season {
    Winter,
    Spring,
    Autumn,
    Summer;

    static public Season of(final Month month) {
        Objects.requireNonNull(month, "ERROR - Received null where a `Month` is expected.");
        return
                switch (Objects.requireNonNull(month)) {
                    case March, April, May -> Season.Spring;
                    case June, July, August -> Season.Summer;
                    case September, October, November -> Season.Autumn;
                    case December, January, February -> Season.Winter;
                };
    }
}
