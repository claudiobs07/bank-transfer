package br.com.claudiobs.transacao.service.tax

import spock.lang.Specification
import spock.lang.Unroll

class TwoTenUntilThreeTenTaxCalculatorTest extends Specification {
    
    TwoTenUntilThreeTenTaxCalculator taxCalculator = new TwoTenUntilThreeTenTaxCalculator()
    
    @Unroll
    def "check tax calculator valid when dayToBankTransfer=#dayToBankTransfer then expect=#expected"() {
        when:
            def valid = taxCalculator.isValid(dayToBankTransfer, new BigDecimal(100))
        then:
            valid == expected
        where:
            dayToBankTransfer   | expected
            10l                 | false
            11l                 | true
            20l                 | true
            30l                 | true
            40l                 | true
            41l                 | false
    }
    
    @Unroll
    def "given a daysToBankTransfer=#daysToBankTransfer should get tax=#expected"() {
        when:
            def tax = taxCalculator.getTax(daysToBankTransfer, new BigDecimal(100))
        then:
            tax == expected
        where:
            daysToBankTransfer  | expected
            11                  | 8.0
            20                  | 8.0
            21                  | 6.0
            30                  | 6.0
            31                  | 4.0
            40                  | 4.0
    }
    
    
}
