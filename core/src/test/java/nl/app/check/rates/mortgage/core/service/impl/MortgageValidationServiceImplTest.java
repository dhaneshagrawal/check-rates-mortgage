package nl.app.check.rates.mortgage.core.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import nl.app.check.rates.mortgage.core.domain.MortgageCheckRequest;
import nl.app.check.rates.mortgage.core.domain.MortgageCheckResponse;
import nl.app.check.rates.mortgage.core.service.validator.IncomeToLoanValidator;
import nl.app.check.rates.mortgage.core.service.validator.LoanToHomeValueValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;

public class MortgageValidationServiceImplTest {

    @Mock
    private LoanToHomeValueValidator loanToHomeValueValidator;

    @Mock
    private IncomeToLoanValidator incomeToLoanValidator;

    @InjectMocks
    private MortgageValidationServiceImpl mortgageValidationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mortgageValidationService.validators = Arrays.asList(loanToHomeValueValidator, incomeToLoanValidator);
    }

    @Test
    public void testRunAllValidations_WithNoFailures() {
        MortgageCheckRequest request = new MortgageCheckRequest(15, new BigDecimal("60000"), new BigDecimal("200000"), new BigDecimal("250000"));
        when(loanToHomeValueValidator.validate(request)).thenReturn(null);
        when(incomeToLoanValidator.validate(request)).thenReturn(null);

        MortgageCheckResponse response = mortgageValidationService.runAllValidations(request);

        assertNull(response);
        verify(loanToHomeValueValidator).validate(request);
        verify(incomeToLoanValidator).validate(request);
    }

    @Test
    public void testRunAllValidations_WithFailure() {
        MortgageCheckRequest request = new MortgageCheckRequest(15, new BigDecimal("60000"), new BigDecimal("200000"), new BigDecimal("250000"));
        MortgageCheckResponse failedResponse = new MortgageCheckResponse(false, null);
        when(loanToHomeValueValidator.validate(request)).thenReturn(failedResponse);

        MortgageCheckResponse response = mortgageValidationService.runAllValidations(request);

        assertNotNull(response);
        assertFalse(response.feasible());
        verify(loanToHomeValueValidator).validate(request);
        verify(incomeToLoanValidator, never()).validate(request);
    }
}
