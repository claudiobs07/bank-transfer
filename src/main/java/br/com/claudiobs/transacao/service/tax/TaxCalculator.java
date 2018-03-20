package br.com.claudiobs.transacao.service.tax;

import java.math.BigDecimal;

public interface TaxCalculator {

    BigDecimal getTax(long daysToBankTransfer, BigDecimal amount);

    boolean isValid(long daysToBankTransfer, BigDecimal amount);

}
