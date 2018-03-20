package br.com.claudiobs.transacao.service.tax;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DefaultTaxCalculator implements TaxCalculator {
    @Override
    public BigDecimal getTax(long daysToBankTransfer, BigDecimal amount) {
        return new BigDecimal(12.0);
    }

    @Override
    public boolean isValid(long daysToBankTransfer, BigDecimal amount) {
        return true;
    }
}
