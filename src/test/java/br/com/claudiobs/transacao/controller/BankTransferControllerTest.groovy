package br.com.claudiobs.transacao.controller

import br.com.claudiobs.transacao.domain.BankTransfer
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDate

class BankTransferControllerTest extends Specification {
    
    MockMvc mvc
    
    BankTransferController controller
    
    ObjectMapper objectMapper = new ObjectMapper()
    
    void setup() {
        controller = new BankTransferController()
        
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
    }
    
    def "should"() {
        given:
            def bankTransaction =  new BankTransfer(
                    sourceAccount: 123456,
                    destinationAccount: 654321,
                    amount: 12.5,
                    date: LocalDate.now()
            )
        when:
            ResultActions result = mvc.perform(MockMvcRequestBuilders.post(Endpoints.TRANSFER)
                    .content(objectMapper.writeValueAsString(bankTransaction))
                    .contentType(MediaType.APPLICATION_JSON)
            )
        then:
            result.andExpect(MockMvcResultMatchers.status().isCreated())
    }
    
}
