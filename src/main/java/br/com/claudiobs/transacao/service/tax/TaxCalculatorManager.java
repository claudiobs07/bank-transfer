package br.com.claudiobs.transacao.service.tax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class TaxCalculatorManager {

    @Autowired
    private List<TaxCalculator> taxCalculators;


    public TaxCalculator getTaxCalculator(long daysToBankTransfer, BigDecimal amount) {
        Optional<TaxCalculator> optionalTaxCalculator = taxCalculators
                .stream()
                .filter(taxCalculator -> taxCalculator.isValid(daysToBankTransfer, amount))
                .findFirst();
        return optionalTaxCalculator.get();
    }

}
