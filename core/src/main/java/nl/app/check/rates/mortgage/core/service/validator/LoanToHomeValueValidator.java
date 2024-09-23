package nl.app.check.rates.mortgage.core.service.validator;

import nl.app.check.rates.mortgage.core.domain.MortgageCheckRequest;
import nl.app.check.rates.mortgage.core.domain.MortgageCheckResponse;
import nl.app.check.rates.mortgage.core.service.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoanToHomeValueValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(LoanToHomeValueValidator.class);

    @Override
    public MortgageCheckResponse validate(MortgageCheckRequest request) {
        logger.debug("Validating Loan to Home Value ratio for request: {}", request);

        if (request.loanValue().doubleValue() > request.homeValue().doubleValue()) {
            logger.warn("Loan value exceeds home value for request: {}", request);
            return new MortgageCheckResponse(false, null);
        }

        return null;
    }
}
