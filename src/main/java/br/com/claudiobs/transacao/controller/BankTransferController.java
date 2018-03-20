package br.com.claudiobs.transacao.controller;

import br.com.claudiobs.transacao.domain.BankTransfer;
import br.com.claudiobs.transacao.service.BankTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(Endpoints.TRANSFER)
public class BankTransferController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private BankTransferService bankTransferService;

    BankTransferController(BankTransferService bankTransferService) {
        this.bankTransferService = bankTransferService;
    }

    @ResponseStatus(CREATED)
    @PostMapping
    BankTransfer create(@Valid @RequestBody BankTransfer bankTransfer) {
        log.info("creating bankTransfer=" + bankTransfer);
        return bankTransferService.create(bankTransfer);
    }

    @GetMapping
    List<BankTransfer> getAll() {
        return bankTransferService.getAll();
    }

}
