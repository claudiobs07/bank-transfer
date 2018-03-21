package br.com.claudiobs.transacao.service.tax;

import java.math.BigDecimal;

public interface TaxCalculator {

    BigDecimal getTax(long daysToBankTransfer, BigDecimal amount);

    boolean isValid(long daysToBankTransfer, BigDecimal amount);

    default BigDecimal percentage(BigDecimal percent, BigDecimal amount) {
        return (amount.divide(new BigDecimal(100))).multiply(percent);
    }

}
