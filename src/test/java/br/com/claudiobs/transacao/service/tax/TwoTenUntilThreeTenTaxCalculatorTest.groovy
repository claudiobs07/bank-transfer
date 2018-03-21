package br.com.claudiobs.transacao.service.tax

import spock.lang.Specification
import spock.lang.Unroll

class TwoTenUntilThreeTenTaxCalculatorTest extends Specification {
    
    TwoTenUntilThreeTenTaxCalculator taxCalculator = new TwoTenUntilThreeTenTaxCalculator()
    
    @Unroll
    def "check tax calculator valid when dayToBankTransfer=#dayToBankTransfer then expect=#expected"() {
        when:
            def valid = taxCalculator.isValid(dayToBankTransfer, new BigDecimal(amount))
        then:
            valid == expected
        where:
            dayToBankTransfer   | amount    | expected
            10l                 | 123.00    | false
            11l                 | 123.00    | true
            20l                 | 123.00    | true
            20l                 | 123.00    | true
            40l                 | 123.00    | true
            41l                 | 123.00    | false
    }
    
    
}
