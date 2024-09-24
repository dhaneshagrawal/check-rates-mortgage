package nl.app.check.rates.mortgage.integration.controller;

import nl.app.check.rates.mortgage.core.domain.MortgageRate;
import nl.app.check.rates.mortgage.core.service.MortgageRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/interest-rates")
public class MortgageRateController {
    @Autowired
    private MortgageRateService mortgageRateService;

    @GetMapping
    public List<MortgageRate> getInterestRates() {
        return mortgageRateService.getInterestRates();
    }
}