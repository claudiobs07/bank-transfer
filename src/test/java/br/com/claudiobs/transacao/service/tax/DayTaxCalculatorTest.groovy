package br.com.claudiobs.transacao.service.tax

import spock.lang.Specification

class DayTaxCalculatorTest extends Specification {
    
    DayTaxCalculator taxCalculator = new DayTaxCalculator()
    
    def "should check tax calculator valid"() {
        when:
            def valid = taxCalculator.isValid(dayToBankTransfer, new BigDecimal(amount))
        then:
            valid == expected
        where:
            dayToBankTransfer   | amount    | expected
            0l                  | 123.00    | true
            1l                  | 123.00    | false
            20l                 | 123.00    | false
    }
    
    def "should"() {
        when:
            def tax = taxCalculator.getTax(0l, new BigDecimal(amount))
        then:
            tax == expected
        where:
            amount    | expected
            100.00    | 106.0
            200.00    | 212.0
            1.00      | 4.06
    }
}
