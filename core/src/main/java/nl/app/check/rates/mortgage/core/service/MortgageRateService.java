package nl.app.check.rates.mortgage.core.service;

import nl.app.check.rates.mortgage.core.domain.MortgageRate;
import java.util.List;

public interface MortgageRateService {

    public List<MortgageRate> getInterestRates();

    MortgageRate findMortgageRate(Integer request);
}
