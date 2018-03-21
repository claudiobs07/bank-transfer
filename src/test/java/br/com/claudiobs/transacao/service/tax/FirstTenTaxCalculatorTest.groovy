package br.com.claudiobs.transacao.service.tax

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
            1l                  | false
            10l                 | false
    }
}
