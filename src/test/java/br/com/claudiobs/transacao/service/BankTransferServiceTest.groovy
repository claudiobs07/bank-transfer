package br.com.claudiobs.transacao.service

import br.com.claudiobs.transacao.domain.BankTransfer
import br.com.claudiobs.transacao.fixture.BankTransfers
import br.com.claudiobs.transacao.repository.BankTransferRepository
import br.com.claudiobs.transacao.service.tax.TaxCalculator
import br.com.claudiobs.transacao.service.tax.TaxCalculatorManager
import spock.lang.Specification

class BankTransferServiceTest extends Specification {
    
    BankTransferRepository bankTransferRepositoryMock
    
    TaxCalculatorManager taxManagerMock
    
    BankTransferService service
    
    def setup() {
        bankTransferRepositoryMock = Mock(BankTransferRepository)
        taxManagerMock = Mock(TaxCalculatorManager)
        
        service = new BankTransferService(taxManagerMock, bankTransferRepositoryMock)
    }
    
    def "should apply tax and save bank transfer"() {
        given:
            TaxCalculator taxHandlerMock = Mock(TaxCalculator)
            BankTransfer bankTransfer = BankTransfers.create()
            BigDecimal tax = new BigDecimal(3.0)
        when:
            service.create(bankTransfer)
        then:
            1 * taxManagerMock.getTaxHandler(_ as Long) >> taxHandlerMock
            1 * taxHandlerMock.getTax(_ as Long) >> tax
            1 * bankTransferRepositoryMock.save(_ as BankTransfer) >> { BankTransfer transfer ->
                assert transfer.tax == tax
                
                transfer
            }
    }
    
}
