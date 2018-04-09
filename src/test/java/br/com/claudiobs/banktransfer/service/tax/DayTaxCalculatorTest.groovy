package br.com.claudiobs.banktransfer.service.tax

import spock.lang.Specification
import spock.lang.Unroll

class DayTaxCalculatorTest extends Specification {
    
    DayTaxCalculator taxCalculator = new DayTaxCalculator()
    
    @Unroll
    def "check tax calculator valid when dayToBankTransfer=#dayToBankTransfer then expect=#expected"() {
        when:
            def valid = taxCalculator.isValid(dayToBankTransfer, new BigDecimal(123.00))
        then:
            valid == expected
        where:
            dayToBankTransfer   | expected
            0l                  | true
            1l                  | false
            20l                 | false
    }
    
    @Unroll
    def "given a amount=#amount should get tax=#expected"() {
        when:
            def tax = taxCalculator.getTax(0l, new BigDecimal(amount))
        then:
            tax == expected
        where:
            amount    | expected
            100.00    | 6.0
            200.00    | 9.0
            1.00      | 3.03
    }
}
