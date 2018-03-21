package br.com.claudiobs.transacao.service.tax

import spock.lang.Specification

class TaxCalculatorFactoryTest extends Specification {
    
    TaxCalculatorFactory manager
    
    DayTaxCalculator taxCalculatorMock
    
    def setup() {
        taxCalculatorMock = Mock(DayTaxCalculator)
        
        manager = new TaxCalculatorFactory(taxCalculators: [taxCalculatorMock])
    }
    
    
    def "should get default tax calculator"() {
        given:
            def daysToBankTransfer = 0l
            def amount = new BigDecimal(20.0)
        when:
            def taxCalculator = manager.getTaxCalculator(daysToBankTransfer, amount)
        then:
            taxCalculatorMock.isValid(_ as long, _ as BigDecimal) >> true
            taxCalculator.isPresent()
            taxCalculator instanceof DayTaxCalculator
    }
    
    def "should get optional empty when none match"() {
        given:
            def daysToBankTransfer = 1l
            def amount = new BigDecimal(20.0)
        when:
            def taxCalculator = manager.getTaxCalculator(daysToBankTransfer, amount)
        then:
            taxCalculatorMock.isValid(_ as long, _ as BigDecimal) >> false
            !taxCalculator.isPresent()
    }
}
