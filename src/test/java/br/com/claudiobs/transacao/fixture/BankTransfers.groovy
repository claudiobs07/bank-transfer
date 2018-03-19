package br.com.claudiobs.transacao.fixture

import br.com.claudiobs.transacao.domain.BankTransfer

import java.time.LocalDate

class BankTransfers {
    
    static BankTransfer create(BigDecimal tax = null) {
        new BankTransfer(
                sourceAccount: "123456",
                destinationAccount: "654321",
                amount: 12.5,
                tax: tax,
                date: LocalDate.now()
        )
    }
    
}
