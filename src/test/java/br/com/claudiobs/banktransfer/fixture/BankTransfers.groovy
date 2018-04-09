package br.com.claudiobs.banktransfer.fixture

import br.com.claudiobs.banktransfer.domain.BankTransfer

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
