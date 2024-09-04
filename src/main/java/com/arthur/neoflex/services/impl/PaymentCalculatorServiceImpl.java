package com.arthur.neoflex.services.impl;

import com.arthur.neoflex.dtos.PaymentForVacationDTO;
import com.arthur.neoflex.exceptions.InputException;
import com.arthur.neoflex.services.PaymentCalculatorService;
import com.arthur.neoflex.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;


@Service
public class PaymentCalculatorServiceImpl implements PaymentCalculatorService {

    private final Utils utils;

    @Autowired
    public PaymentCalculatorServiceImpl(Utils utils) {
        this.utils = utils;
    }

    public BigDecimal calculate(PaymentForVacationDTO paymentForVacationDTO) {

        LocalDate startDate = paymentForVacationDTO.getStartDate();
        LocalDate endDate = paymentForVacationDTO.getEndDate();
        BigDecimal avgSalary = paymentForVacationDTO.getAvgSalary();

        if (startDate.isAfter(endDate)) {
            throw new InputException("Start date cannot be after end date !");
        }

        if (avgSalary.longValue() < 0) {
            throw new InputException("Avg salary cannot be less than zero !");
        }

        Long days = startDate.datesUntil(endDate)
                .filter(day -> !utils.isHoliday(day) && !utils.isWeekend(day))
                .count();


        return avgSalary.multiply(BigDecimal.valueOf((days+1) / 29.3)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
