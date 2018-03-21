package br.com.claudiobs.transacao.service.tax;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FirstTenTaxCalculator implements TaxCalculator {

    @Override
    public BigDecimal getTax(long daysToBankTransfer, BigDecimal amount) {
        return amount.multiply(new BigDecimal(daysToBankTransfer));
    }

    @Override
    public boolean isValid(long daysToBankTransfer, BigDecimal amount) {
        return daysToBankTransfer > 0 && daysToBankTransfer <= 10;
    }
}
