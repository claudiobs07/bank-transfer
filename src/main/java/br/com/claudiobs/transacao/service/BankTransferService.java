package br.com.claudiobs.transacao.service;

import br.com.claudiobs.transacao.domain.BankTransfer;
import br.com.claudiobs.transacao.service.tax.TaxHandler;
import br.com.claudiobs.transacao.service.tax.TaxManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class BankTransferService {

    TaxManager taxManager;

    BankTransferService(TaxManager taxManager) {
        this.taxManager = taxManager;
    }

    public BankTransfer create(BankTransfer bankTransfer) {
        applyTax(bankTransfer);
    }

    private void applyTax(BankTransfer bankTransfer) {
        long daysToBankTransfer = getDaysToBankTransfer(bankTransfer.getDate());
        TaxHandler taxHandler = taxManager.getTaxHandler(daysToBankTransfer);
        BigDecimal tax = taxHandler.getTax(daysToBankTransfer);
        bankTransfer.setTax(tax);
    }

    private long getDaysToBankTransfer(LocalDate transferDate) {
        return ChronoUnit.DAYS.between(LocalDate.now(), transferDate);
    }
}
