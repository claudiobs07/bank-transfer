package br.com.claudiobs.transacao.service.tax

import spock.lang.Specification

class TaxCalculatorManagerTest extends Specification {
    
    TaxCalculatorManager manager
    
    DefaultTaxCalculator defaultTaxCalculatorMock
    
    def setup() {
        defaultTaxCalculatorMock = Mock(DefaultTaxCalculator)
        
        manager = new TaxCalculatorManager(taxCalculators: [defaultTaxCalculatorMock])
    }
    
    
    def "should get default tax calculator"() {
        given:
            def daysToBankTransfer = 1l
            def amount = new BigDecimal(20.0)
        when:
            def taxCalculator = manager.getTaxCalculator(daysToBankTransfer, amount)
        then:
            defaultTaxCalculatorMock.isValid(_ as long, _ as BigDecimal) >> true
            taxCalculator.isPresent()
            taxCalculator instanceof DefaultTaxCalculator
    }
    
    def "should get optional empty when none match"() {
        given:
            def daysToBankTransfer = 1l
            def amount = new BigDecimal(20.0)
        when:
            def taxCalculator = manager.getTaxCalculator(daysToBankTransfer, amount)
        then:
            defaultTaxCalculatorMock.isValid(_ as long, _ as BigDecimal) >> false
            !taxCalculator.isPresent()
    }
}
