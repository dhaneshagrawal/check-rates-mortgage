package nl.app.check.rates.mortgage.core.service.impl;

import nl.app.check.rates.mortgage.core.domain.MortgageCheckRequest;
import nl.app.check.rates.mortgage.core.domain.MortgageCheckResponse;
import nl.app.check.rates.mortgage.core.domain.MortgageRate;
import nl.app.check.rates.mortgage.core.service.MortgageCheckService;
import nl.app.check.rates.mortgage.core.service.MortgageRateService;
import nl.app.check.rates.mortgage.core.service.MortgageValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class MortgageCheckServiceImpl implements MortgageCheckService {

    private static final Logger logger = LoggerFactory.getLogger(MortgageCheckServiceImpl.class);

    @Autowired
    private MortgageRateService mortgageRateService;

    @Autowired
    private MortgageValidationService mortgageValidationService;

    @Override
    public MortgageCheckResponse checkMortgage(MortgageCheckRequest request) {
        logger.info("Starting mortgage check for request: {}", request);

        MortgageCheckResponse validationResponse = mortgageValidationService.runAllValidations(request);
        if (validationResponse != null) {
            logger.warn("Validation failed during mortgage check for request: {}", request);
            return validationResponse;
        }

        MortgageRate mortgageRate = mortgageRateService.findMortgageRate(request.maturityPeriod());
        logger.debug("Found mortgage rate: {} for request: {}", mortgageRate, request);

        double monthlyCost = calculateMonthlyCost(request, mortgageRate);
        logger.info("Mortgage check passed. Calculated monthly cost: {}", monthlyCost);

        return new MortgageCheckResponse(true, BigDecimal.valueOf(monthlyCost));
    }

    private double calculateMonthlyCost(MortgageCheckRequest request, MortgageRate mortgageRate) {
        double monthlyRate = mortgageRate.interestRate().doubleValue() / 100 / 12;
        int numberOfPayments = request.maturityPeriod() * 12;
        double compoundedRateFactor = Math.pow(1 + monthlyRate, numberOfPayments);
        double monthlyCost = (request.loanValue().doubleValue() * monthlyRate) / (compoundedRateFactor - 1);
        return monthlyCost;
    }
}
