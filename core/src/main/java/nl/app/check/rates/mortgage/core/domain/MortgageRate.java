package nl.app.check.rates.mortgage.core.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record MortgageRate(int maturityPeriod, BigDecimal interestRate, ZonedDateTime lastUpdate) { }
