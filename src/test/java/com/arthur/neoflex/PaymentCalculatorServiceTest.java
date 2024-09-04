package com.arthur.neoflex;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.arthur.neoflex.controllers.PaymentCalculatorController;
import com.arthur.neoflex.dtos.PaymentForVacationDTO;
import com.arthur.neoflex.services.impl.PaymentCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentCalculatorServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PaymentCalculatorServiceImpl paymentCalculatorService;
    @InjectMocks
    private PaymentCalculatorController paymentCalculatorController;

    PaymentForVacationDTO paymentForVacationDTO;


    @BeforeEach
    void setUp() {
        paymentForVacationDTO = PaymentForVacationDTO.builder()
                .avgSalary(BigDecimal.valueOf(30000))
                .startDate(LocalDate.of(2024, 9, 2))
                .endDate(LocalDate.of(2024, 9, 6))
                .build();
    }

    @Test
    void calculateWithValidInputsControllerTest() throws Exception {

        BigDecimal expected = BigDecimal.valueOf(5119.45);

        mockMvc.perform(get("/api/v1/paymentCalculator/calculate")
                .param("avgSalary", String.valueOf(paymentForVacationDTO.getAvgSalary()))
                .param("startDate", paymentForVacationDTO.getStartDate().toString())
                .param("endDate", paymentForVacationDTO.getEndDate().toString()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expected)));
    }

    @Test
    void calculateWithoutRequiredParamsControllerTest() throws Exception {
        mockMvc.perform(get("/api/v1/paymentCalculator/calculate")
                        .param("avgSalary", String.valueOf(paymentForVacationDTO.getAvgSalary()))
                        .param("startDate", paymentForVacationDTO.getStartDate().toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void calculateWithAvgSalaryIsZero() throws Exception {
        BigDecimal expected = BigDecimal.valueOf(0).setScale(2, BigDecimal.ROUND_HALF_UP);

        mockMvc.perform(get("/api/v1/paymentCalculator/calculate")
                        .param("avgSalary", String.valueOf(0))
                        .param("startDate", paymentForVacationDTO.getStartDate().toString())
                        .param("endDate", paymentForVacationDTO.getEndDate().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expected)));
    }

    @Test
    void calculateWithIncorrectDates() throws Exception {

        mockMvc.perform(get("/api/v1/paymentCalculator/calculate")
                        .param("avgSalary", String.valueOf(0))
                        .param("startDate", paymentForVacationDTO.getEndDate().toString())
                        .param("endDate", paymentForVacationDTO.getStartDate().toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
