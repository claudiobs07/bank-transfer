package br.com.claudiobs.transacao.service;

import br.com.claudiobs.transacao.domain.BankTransfer;
import br.com.claudiobs.transacao.repository.BankTransferRepository;
import br.com.claudiobs.transacao.service.tax.TaxCalculator;
import br.com.claudiobs.transacao.service.tax.TaxCalculatorManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BankTransferService {

    private TaxCalculatorManager taxCalculatorManager;

    private BankTransferRepository bankTransferRepository;

    BankTransferService(TaxCalculatorManager taxCalculatorManager, BankTransferRepository bankTransferRepository) {
        this.taxCalculatorManager = taxCalculatorManager;
        this.bankTransferRepository = bankTransferRepository;
    }

    public BankTransfer create(BankTransfer bankTransfer) {
        applyTax(bankTransfer);
        return bankTransferRepository.save(bankTransfer);
    }

    private void applyTax(BankTransfer bankTransfer) {
        long daysToBankTransfer = getDaysToBankTransfer(bankTransfer.getDate());
        TaxCalculator taxCalculator = taxCalculatorManager.getTaxCalculator(daysToBankTransfer, bankTransfer.getAmount());
        BigDecimal tax = taxCalculator.getTax(daysToBankTransfer, bankTransfer.getAmount());
        bankTransfer.setTax(tax);
    }

    private long getDaysToBankTransfer(LocalDate transferDate) {
        return ChronoUnit.DAYS.between(LocalDate.now(), transferDate);
    }

    public List<BankTransfer> getAll() {
        return bankTransferRepository.findAll();
    }
}
