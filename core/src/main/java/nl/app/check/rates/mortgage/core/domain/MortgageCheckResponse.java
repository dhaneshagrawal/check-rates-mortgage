package nl.app.check.rates.mortgage.core.domain;

import java.math.BigDecimal;

public record MortgageCheckResponse(boolean feasible, BigDecimal monthlyCosts) { }
