package nl.app.check.rates.mortgage.core.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record MortgageCheckRequest(
        @NotNull
        Integer maturityPeriod,

        @DecimalMin(value = "0.0", inclusive = false)
        @NotNull
        BigDecimal income,

        @DecimalMin(value = "0.0", inclusive = false)
        @NotNull BigDecimal loanValue,

        @DecimalMin(value = "0.0", inclusive = false)
        @NotNull
        BigDecimal homeValue
        ) { }
