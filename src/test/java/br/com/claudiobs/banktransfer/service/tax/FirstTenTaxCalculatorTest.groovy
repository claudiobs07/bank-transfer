package br.com.claudiobs.banktransfer.service.tax

import spock.lang.Specification
import spock.lang.Unroll

class FirstTenTaxCalculatorTest extends Specification {
    
    FirstTenTaxCalculator taxCalculator = new FirstTenTaxCalculator()
    
    @Unroll
    def "check tax calculator valid when dayToBankTransfer=#dayToBankTransfer then expect=#expected"() {
        when:
            def valid = taxCalculator.isValid(dayToBankTransfer, new BigDecimal(10.0))
        then:
            valid == expected
        where:
            dayToBankTransfer   | expected
            0l                  | false
            1l                  | true
            10l                 | true
    }
    
    @Unroll
    def "given a dayToBankTransfer=#dayToBankTransfer should get tax=#expected"() {
        when:
            def tax = taxCalculator.getTax(dayToBankTransfer, new BigDecimal(100))
        then:
            tax == expected
        where:
            dayToBankTransfer   | expected
            1l                  | 12.0
            2l                  | 24.0
            4l                  | 48.0
            6l                  | 72.0
            8l                  | 96.0
            9l                  | 108.0
            10l                 | 120.0
    }
}
