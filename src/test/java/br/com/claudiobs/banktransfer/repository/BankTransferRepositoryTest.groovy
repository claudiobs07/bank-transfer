package br.com.claudiobs.banktransfer.repository

import br.com.claudiobs.banktransfer.BankTransferApplicationTests
import br.com.claudiobs.banktransfer.fixture.BankTransfers
import org.springframework.beans.factory.annotation.Autowired

class BankTransferRepositoryTest extends BankTransferApplicationTests {
    
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
