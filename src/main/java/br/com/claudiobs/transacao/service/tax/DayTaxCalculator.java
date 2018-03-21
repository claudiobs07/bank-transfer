package br.com.claudiobs.transacao.service.tax;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DayTaxCalculator implements TaxCalculator {

    @Override
    public BigDecimal getTax(long daysToBankTransfer, BigDecimal amount) {
        return new BigDecimal(3).add(percentage(new BigDecimal(3), amount));
    }

    @Override
    public boolean isValid(long daysToBankTransfer, BigDecimal amount) {
        return daysToBankTransfer == 0;
    }
}
