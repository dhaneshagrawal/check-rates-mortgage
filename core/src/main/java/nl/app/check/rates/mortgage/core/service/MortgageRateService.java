package nl.app.check.rates.mortgage.core.service;

import jakarta.validation.constraints.NotNull;
import nl.app.check.rates.mortgage.core.domain.MortgageRate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public interface MortgageRateService {

    public List<MortgageRate> getInterestRates();

    MortgageRate findMortgageRate(Integer request);
}
