package br.com.claudiobs.transacao.service.tax;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DefaultTaxHandler implements TaxHandler {
    @Override
    public BigDecimal getTax(long daysToBankTransfer) {
        return null;
    }

    @Override
    public boolean isValid(long daysToBankTransfer) {
        return false;
    }
}
