package nl.app.check.rates.mortgage.core.service.validator;

import nl.app.check.rates.mortgage.core.domain.MortgageCheckRequest;
import nl.app.check.rates.mortgage.core.domain.MortgageCheckResponse;
import nl.app.check.rates.mortgage.core.service.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IncomeToLoanValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(IncomeToLoanValidator.class);

    @Override
    public MortgageCheckResponse validate(MortgageCheckRequest request) {
        logger.debug("Validating Income to Loan ratio for request: {}", request);

        if (request.loanValue().doubleValue() > 4 * request.income().doubleValue()) {
            logger.warn("Loan value exceeds 4 times the income for request: {}", request);
            return new MortgageCheckResponse(false, null);
        }

        return null;
    }
}

