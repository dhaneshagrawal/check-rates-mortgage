package nl.app.check.rates.mortgage.delivery;

import nl.app.check.rates.mortgage.core.CoreConfiguration;
import nl.app.check.rates.mortgage.integration.IntegrationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        CoreConfiguration.class,
        IntegrationConfiguration.class
})
public class CheckRatesMortgageApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckRatesMortgageApplication.class, args);
    }
}
