package nl.app.check.rates.mortgage.core.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import nl.app.check.rates.mortgage.core.domain.MortgageCheckRequest;
import nl.app.check.rates.mortgage.core.domain.MortgageCheckResponse;
import nl.app.check.rates.mortgage.core.domain.MortgageRate;
import nl.app.check.rates.mortgage.core.service.MortgageRateService;
import nl.app.check.rates.mortgage.core.service.MortgageValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

public class MortgageCheckServiceImplTest {

    @Mock
    private MortgageRateService mortgageRateService;

    @Mock
    private MortgageValidationService mortgageValidationService;

    @InjectMocks
    private MortgageCheckServiceImpl mortgageCheckService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckMortgage_WithValidData() {
        MortgageCheckRequest request = new MortgageCheckRequest(15, new BigDecimal("60000"), new BigDecimal("200000"), new BigDecimal("250000"));
        MortgageRate rate = new MortgageRate(15, new BigDecimal("4.5"), null);
        when(mortgageValidationService.runAllValidations(request)).thenReturn(null); // No validation failure
        when(mortgageRateService.findMortgageRate(15)).thenReturn(rate);

        MortgageCheckResponse response = mortgageCheckService.checkMortgage(request);

        assertTrue(response.feasible());
        assertNotNull(response.monthlyCosts());
        verify(mortgageRateService).findMortgageRate(15);
        verify(mortgageValidationService).runAllValidations(request);
    }

    @Test
    public void testCheckMortgage_WithFailedValidation() {
        MortgageCheckRequest request = new MortgageCheckRequest(15, new BigDecimal("60000"), new BigDecimal("200000"), new BigDecimal("250000"));
        MortgageCheckResponse failedResponse = new MortgageCheckResponse(false, null);
        when(mortgageValidationService.runAllValidations(request)).thenReturn(failedResponse);

        MortgageCheckResponse response = mortgageCheckService.checkMortgage(request);

        assertFalse(response.feasible());
        assertNull(response.monthlyCosts());
        verify(mortgageValidationService).runAllValidations(request);
        verify(mortgageRateService, never()).findMortgageRate(anyInt());
    }
}
