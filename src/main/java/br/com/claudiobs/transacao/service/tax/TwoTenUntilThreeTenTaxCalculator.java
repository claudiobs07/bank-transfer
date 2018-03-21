package br.com.claudiobs.transacao.service.tax;

import java.math.BigDecimal;

public class TwoTenUntilThreeTenTaxCalculator implements TaxCalculator {

    @Override
    public BigDecimal getTax(long daysToBankTransfer, BigDecimal amount) {
        BigDecimal percent = calculatePercent(daysToBankTransfer);
        return percentage(amount, percent);
    }

    @Override
    public boolean isValid(long daysToBankTransfer, BigDecimal amount) {
        return daysToBankTransfer > 10 && daysToBankTransfer <= 40;
    }

    private BigDecimal calculatePercent(long daysToBankTransfer) {
        double x = Math.ceil(daysToBankTransfer / 10);
        return new BigDecimal((x * 2) - 12);
    }
}
