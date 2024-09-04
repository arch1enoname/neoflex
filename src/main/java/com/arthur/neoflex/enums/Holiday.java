package com.arthur.neoflex.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.Month;

import static java.time.Month.*;

@AllArgsConstructor
@Getter
public enum Holiday {

    NEW_YEAR(JANUARY, 1),
    CHRISTMAS(JANUARY, 7),
    DEFENDER_OF_THE_FATHERLAND_DAY(FEBRUARY, 23),
    INTERNATIONAL_WOMENS_DAY(MARCH, 8),
    LABOR_DAY(MAY, 1),
    VICTORY_DAY(MAY, 9),
    DAY_OF_Russian_LANGUAGE(JUNE, 6),
    DAY_OF_SPORT(SEPTEMBER, 1),
    DAY_OF_CONSTITUTION(DECEMBER, 12),
    NEW_YEAR_EVE(DECEMBER, 31);

    private final Month month;

    private final int day;
}
