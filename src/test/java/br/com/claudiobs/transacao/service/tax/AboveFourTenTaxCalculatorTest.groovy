package br.com.claudiobs.transacao.service.tax

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
            40l                 | 1230.00    | false
            41l                 | 123.00     | false
            41l                 | 1230.00    | true
            50l                 | 1230.00    | true
    }
    
}
