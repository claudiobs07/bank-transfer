package br.com.claudiobs.banktransfer.controller;

import br.com.claudiobs.banktransfer.domain.BankTransfer;
import br.com.claudiobs.banktransfer.service.BankTransferService;
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
import static org.springframework.http.HttpStatus.OK;

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

    @ResponseStatus(OK)
    @GetMapping
    List<BankTransfer> getAll() {
        return bankTransferService.getAll();
    }

}
