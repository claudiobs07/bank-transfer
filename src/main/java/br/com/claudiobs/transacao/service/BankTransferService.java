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
import java.util.Optional;

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
        BigDecimal tax = calculateTax(bankTransfer);
        bankTransfer.setTax(tax);
    }

    private BigDecimal calculateTax(BankTransfer bankTransfer) {
        long daysToBankTransfer = getDaysToBankTransfer(bankTransfer.getDate());
        Optional<TaxCalculator> optTaxCalculator = taxCalculatorManager.getTaxCalculator(daysToBankTransfer, bankTransfer.getAmount());
        TaxCalculator taxCalculator = optTaxCalculator.orElseThrow(RuntimeException::new);
        return taxCalculator.getTax(daysToBankTransfer, bankTransfer.getAmount());
    }

    private long getDaysToBankTransfer(LocalDate transferDate) {
        return ChronoUnit.DAYS.between(LocalDate.now(), transferDate);
    }

    public List<BankTransfer> getAll() {
        return bankTransferRepository.findAll();
    }
}
