package br.com.claudiobs.banktransfer.service.tax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class TaxCalculatorFactory {

    @Autowired
    private List<TaxCalculator> taxCalculators;


    public Optional<TaxCalculator> getTaxCalculator(long daysToBankTransfer, BigDecimal amount) {
        return taxCalculators
                .stream()
                .filter(taxCalculator -> taxCalculator.isValid(daysToBankTransfer, amount))
                .findFirst();
    }

}
