package nl.app.check.rates.mortgage.core.service;

import nl.app.check.rates.mortgage.core.domain.MortgageCheckRequest;
import nl.app.check.rates.mortgage.core.domain.MortgageCheckResponse;

public interface Validator {
    MortgageCheckResponse validate(MortgageCheckRequest request);
}
