package nl.app.check.rates.mortgage.core.service.validator;

import nl.app.check.rates.mortgage.core.service.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ValidatorConfig {

    @Bean
    public List<Validator> validators() {
        return Arrays.asList(new LoanToHomeValueValidator(), new IncomeToLoanValidator());
    }
}

