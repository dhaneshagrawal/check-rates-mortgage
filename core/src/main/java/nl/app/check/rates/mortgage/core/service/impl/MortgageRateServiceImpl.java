package nl.app.check.rates.mortgage.core.service.impl;

import nl.app.check.rates.mortgage.core.domain.MortgageRate;
import nl.app.check.rates.mortgage.core.exception.MortgageValidationException;
import nl.app.check.rates.mortgage.core.service.MortgageRateService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class MortgageRateServiceImpl implements MortgageRateService {
    @Override
    public List<MortgageRate> getInterestRates() {
        return Arrays.asList(
                new MortgageRate(1, new BigDecimal("4.96"), ZonedDateTime.now()),
                new MortgageRate(2, new BigDecimal("4.76"), ZonedDateTime.now()),
                new MortgageRate(3, new BigDecimal("4.46"), ZonedDateTime.now()),
                new MortgageRate(4, new BigDecimal("4.36"), ZonedDateTime.now()),
                new MortgageRate(5, new BigDecimal("4.28"), ZonedDateTime.now()),
                new MortgageRate(6, new BigDecimal("4.08"), ZonedDateTime.now()),
                new MortgageRate(7, new BigDecimal("4.08"), ZonedDateTime.now()),
                new MortgageRate(8, new BigDecimal("4.08"), ZonedDateTime.now()),
                new MortgageRate(9, new BigDecimal("4.08"), ZonedDateTime.now()),
                new MortgageRate(10, new BigDecimal("4.08"), ZonedDateTime.now()),
                new MortgageRate(11, new BigDecimal("4.31"), ZonedDateTime.now()),
                new MortgageRate(12, new BigDecimal("4.33"), ZonedDateTime.now()),
                new MortgageRate(13, new BigDecimal("4.36"), ZonedDateTime.now()),
                new MortgageRate(14, new BigDecimal("4.38"), ZonedDateTime.now()),
                new MortgageRate(15, new BigDecimal("4.41"), ZonedDateTime.now()),
                new MortgageRate(20, new BigDecimal("4.46"), ZonedDateTime.now()),
                new MortgageRate(25, new BigDecimal("4.63"), ZonedDateTime.now()),
                new MortgageRate(30, new BigDecimal("4.76"), ZonedDateTime.now())
        );
    }

    @Override
    public MortgageRate findMortgageRate(Integer maturityPeriod) {
        return getInterestRates().stream()
                .filter(r -> r.maturityPeriod() == maturityPeriod)
                .findFirst()
                .orElseThrow(() -> new MortgageValidationException(
                        "Mortgage Interest Rate not defined for given maturity period. " +
                                "Please select another maturity period."
                ));
    }
}
