package br.com.claudiobs.transacao.repository

import br.com.claudiobs.transacao.ApplicationTests
import br.com.claudiobs.transacao.fixture.BankTransfers
import org.springframework.beans.factory.annotation.Autowired

class BankTransferRepositoryTest extends ApplicationTests {
    
    @Autowired
    BankTransferRepository repository
    
    def "should save and get a bank transfer"() {
        given:
            def bankTransfer = BankTransfers.create(0.3)
        when:
            def persisted = repository.save(bankTransfer)
        then:
            persisted
        when:
            def found = repository.findById(persisted.id)
        then:
            found.isPresent()
            found.get() == persisted
    }

}
