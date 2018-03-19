package br.com.claudiobs.transacao.controller

import br.com.claudiobs.transacao.config.SafeDateFormat
import br.com.claudiobs.transacao.domain.BankTransfer
import br.com.claudiobs.transacao.service.BankTransferService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDate

import static br.com.claudiobs.transacao.controller.Endpoints.TRANSFER
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class BankTransferControllerTest extends Specification {
    
    MockMvc mvc
    
    BankTransferController controller
    
    BankTransferService bankTransferServiceMock
    
    static ObjectMapper objectMapper
    
    static  {
        objectMapper = new ObjectMapper()
        objectMapper.setDateFormat(new SafeDateFormat(objectMapper.getDateFormat()))
        objectMapper.registerModule(new JavaTimeModule())
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.setSerializationInclusion(NON_NULL)
    }
    
    def setup() {
        bankTransferServiceMock = Mock(BankTransferService)
        controller = new BankTransferController(bankTransferServiceMock)
        
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
    }
    
    def "should get status CREATED and a complete bank transfer"() {
        given:
            def bankTransfer =  new BankTransfer(
                    sourceAccount: 123456,
                    destinationAccount: 654321,
                    amount: 12.5,
                    date: LocalDate.now()
            )
        when:
            ResultActions result = mvc.perform(MockMvcRequestBuilders.post(TRANSFER)
                    .content(objectMapper.writeValueAsString(bankTransfer))
                    .contentType(APPLICATION_JSON)
            )
        then:
            1 * bankTransferServiceMock.create(_ as BankTransfer) >> bankTransfer
            result.andExpect(status().isCreated())
    }
    
}
