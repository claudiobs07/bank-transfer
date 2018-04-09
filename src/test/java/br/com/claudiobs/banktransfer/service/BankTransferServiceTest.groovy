package br.com.claudiobs.banktransfer.service

import br.com.claudiobs.banktransfer.domain.BankTransfer
import br.com.claudiobs.banktransfer.fixture.BankTransfers
import br.com.claudiobs.banktransfer.repository.BankTransferRepository
import br.com.claudiobs.banktransfer.service.tax.TaxCalculator
import br.com.claudiobs.banktransfer.service.tax.TaxCalculatorFactory
import spock.lang.Specification

class BankTransferServiceTest extends Specification {
    
    BankTransferRepository bankTransferRepositoryMock
    
    TaxCalculatorFactory taxCalculatorFactoryMock
    
    BankTransferService service
    
    def setup() {
        bankTransferRepositoryMock = Mock(BankTransferRepository)
        taxCalculatorFactoryMock = Mock(TaxCalculatorFactory)
        
        service = new BankTransferService(taxCalculatorFactoryMock, bankTransferRepositoryMock)
    }
    
    def "should apply tax and save bank transfer"() {
        given:
            TaxCalculator taxHandlerMock = Mock(TaxCalculator)
            BankTransfer bankTransfer = BankTransfers.create()
            BigDecimal tax = new BigDecimal(3.0)
        when:
            service.create(bankTransfer)
        then:
            1 * taxCalculatorFactoryMock.getTaxCalculator(_ as Long, _ as BigDecimal) >> Optional.of(taxHandlerMock)
            1 * taxHandlerMock.getTax(_ as Long,  _ as BigDecimal) >> tax
            1 * bankTransferRepositoryMock.save(_ as BankTransfer) >> { BankTransfer transfer ->
                assert transfer.tax == tax
                
                transfer
            }
    }
    
}
