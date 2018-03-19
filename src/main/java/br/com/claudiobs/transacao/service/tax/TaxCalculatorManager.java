package br.com.claudiobs.transacao.service.tax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaxCalculatorManager {

    @Autowired
    private List<TaxCalculator> taxCalculators;


    public TaxCalculator getTaxCalculator(long daysToBankTransfer) {
        return null;
    }

}
