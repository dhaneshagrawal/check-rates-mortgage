package nl.app.check.rates.mortgage.core.service.impl;

import nl.app.check.rates.mortgage.core.domain.MortgageCheckRequest;
import nl.app.check.rates.mortgage.core.domain.MortgageCheckResponse;
import nl.app.check.rates.mortgage.core.service.MortgageValidationService;
import nl.app.check.rates.mortgage.core.service.Validator;
import nl.app.check.rates.mortgage.core.service.validator.IncomeToLoanValidator;
import nl.app.check.rates.mortgage.core.service.validator.LoanToHomeValueValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MortgageValidationServiceImpl implements MortgageValidationService {

    private static final Logger logger = LoggerFactory.getLogger(MortgageValidationServiceImpl.class);

    private List<Validator> validators = List.of(
            new IncomeToLoanValidator(),
            new LoanToHomeValueValidator()
    );

    @Override
    public MortgageCheckResponse runAllValidations(MortgageCheckRequest request) {
        logger.info("Running validations for mortgage request: {}", request);

        for (Validator validator : validators) {
            logger.debug("Running validation with validator: {}", validator.getClass().getSimpleName());
            MortgageCheckResponse response = validator.validate(request);
            if (response != null) {
                logger.warn("Validation failed with validator: {}", validator.getClass().getSimpleName());
                return response;
            }
        }

        logger.info("All validations passed for mortgage request.");
        return null;
    }
}
