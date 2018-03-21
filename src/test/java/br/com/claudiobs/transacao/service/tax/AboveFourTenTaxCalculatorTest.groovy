package br.com.claudiobs.transacao.service.tax

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

class AboveFourTenTaxCalculatorTest extends Specification {
    
    AboveFourTenTaxCalculator taxCalculator = new AboveFourTenTaxCalculator()
    
    @Unroll
    def "check tax calculator valid when dayToBankTransfer=#dayToBankTransfer then expect=#expected"() {
        when:
            def valid = taxCalculator.isValid(dayToBankTransfer, new BigDecimal(amount))
        then:
            valid == expected
        where:
            dayToBankTransfer   | amount     | expected
            40l                 | 123.00     | false
            40l                 | 123000.00  | false
            41l                 | 123.00     | false
            41l                 | 123000.00  | true
            50l                 | 123000.00  | true
    }
    
    @Ignore
    @Unroll
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
