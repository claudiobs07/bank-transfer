package br.com.claudiobs.transacao.repository

import br.com.claudiobs.transacao.TransacaoApplicationTests
import br.com.claudiobs.transacao.domain.BankTransfer
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDate

class BankTransactionRepositoryTest extends TransacaoApplicationTests {
    
    @Autowired
    BankTransferRepository repository
    
    def "should save and get a bank transfer"() {
        given:
            def bankTransaction =  new BankTransfer(
                    sourceAccount: 123456,
                    destinationAccount: 654321,
                    amount: 12.5,
                    tax: 0.3,
                    date: LocalDate.now()
            )
        when:
            def persisted = repository.save(bankTransaction)
        then:
            persisted
        when:
            def found = repository.findById(persisted.id)
        then:
            found.isPresent()
            found.get() == persisted
    }

}
