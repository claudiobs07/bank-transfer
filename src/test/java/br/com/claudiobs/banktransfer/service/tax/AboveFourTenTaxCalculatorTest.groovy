package br.com.claudiobs.banktransfer.service.tax

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
    
    @Unroll
    def "given a amount=#amount should get tax=#expected"() {
        when:
            def tax = taxCalculator.getTax(45, new BigDecimal(amount))
        then:
            tax == expected
        where:
            amount    | expected
            100       | 2.0
            200       | 4.0
            400       | 8.0
            1000      | 20.0
    }
    
}
