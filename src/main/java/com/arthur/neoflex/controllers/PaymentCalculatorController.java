package com.arthur.neoflex.controllers;

import com.arthur.neoflex.dtos.PaymentForVacationDTO;
import com.arthur.neoflex.exceptions.InputException;
import com.arthur.neoflex.services.impl.PaymentCalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/paymentCalculator")
public class PaymentCalculatorController {
    private final PaymentCalculatorServiceImpl paymentCalculatorServiceImpl;

    @Autowired
    public PaymentCalculatorController(PaymentCalculatorServiceImpl service) {
        this.paymentCalculatorServiceImpl = service;
    }

    @GetMapping("/calculate")
    public ResponseEntity<String> calculateSalary(@Valid PaymentForVacationDTO paymentForVacationDTO)  {
        if (paymentForVacationDTO.getStartDate() == null || paymentForVacationDTO.getEndDate() == null) {
            throw new InputException("There are some problems with DTO format: " + paymentForVacationDTO);
        } else {
            return ResponseEntity.ok(paymentCalculatorServiceImpl.calculate(paymentForVacationDTO).toString());
        }
    }


}
