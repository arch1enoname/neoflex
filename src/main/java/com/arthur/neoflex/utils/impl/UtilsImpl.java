package com.arthur.neoflex.utils.impl;

import com.arthur.neoflex.enums.Holiday;
import com.arthur.neoflex.utils.Utils;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class UtilsImpl implements Utils {

    @Override
    public boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    @Override
    public boolean isHoliday(LocalDate date) {
        return Arrays.stream(Holiday.values())
                .anyMatch(holiday -> holiday.getMonth().equals(date.getMonth()) && holiday.getDay() == date.getDayOfMonth());
    }
}
