package com.arthur.neoflex.services;

import com.arthur.neoflex.dtos.PaymentForVacationDTO;

import java.math.BigDecimal;

public interface PaymentCalculatorService {
    BigDecimal calculate(PaymentForVacationDTO paymentForVacationDTO);
}
