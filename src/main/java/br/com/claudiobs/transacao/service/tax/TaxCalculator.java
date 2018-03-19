package br.com.claudiobs.transacao.service.tax;

import java.math.BigDecimal;

public interface TaxCalculator {

    BigDecimal getTax(long daysToBankTransfer);

    boolean isValid(long daysToBankTransfer);

}
