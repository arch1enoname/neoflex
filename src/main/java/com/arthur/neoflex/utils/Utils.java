package com.arthur.neoflex.utils;

import java.time.LocalDate;

public interface Utils {
    boolean isWeekend(LocalDate date);
    boolean isHoliday(LocalDate date);
}
