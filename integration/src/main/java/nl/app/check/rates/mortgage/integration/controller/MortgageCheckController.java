package nl.app.check.rates.mortgage.integration.controller;

import nl.app.check.rates.mortgage.core.domain.MortgageCheckRequest;
import nl.app.check.rates.mortgage.core.domain.MortgageCheckResponse;
import nl.app.check.rates.mortgage.core.exception.MortgageValidationException;
import nl.app.check.rates.mortgage.core.service.MortgageCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mortgage-check")
public class MortgageCheckController {
    @Autowired
    private MortgageCheckService mortgageCheckService;

    @PostMapping
    public ResponseEntity<MortgageCheckResponse> checkMortgage(@RequestBody MortgageCheckRequest request) {
        try {
            MortgageCheckResponse response = mortgageCheckService.checkMortgage(request);
            return ResponseEntity.ok(response);
        } catch (MortgageValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong while processing the request");
        }
    }
}
