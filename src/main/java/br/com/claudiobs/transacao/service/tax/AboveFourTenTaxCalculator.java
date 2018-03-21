package br.com.claudiobs.transacao.service.tax;

import java.math.BigDecimal;

public class AboveFourTenTaxCalculator implements TaxCalculator {

    @Override
    public BigDecimal getTax(long daysToBankTransfer, BigDecimal amount) {
        return percentage(new BigDecimal(2), amount);
    }

    @Override
    public boolean isValid(long daysToBankTransfer, BigDecimal amount) {
        return daysToBankTransfer > 40 && amount.compareTo(new BigDecimal(100000)) > 0;
    }
}
