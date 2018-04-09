package br.com.claudiobs.banktransfer.controller

import br.com.claudiobs.banktransfer.config.SafeDateFormat
import br.com.claudiobs.banktransfer.domain.BankTransfer
import br.com.claudiobs.banktransfer.fixture.BankTransfers
import br.com.claudiobs.banktransfer.service.BankTransferService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static Endpoints.TRANSFER
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
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
            def bankTransfer = BankTransfers.create()
        when:
            ResultActions result = mvc.perform(MockMvcRequestBuilders.post(TRANSFER)
                    .content(objectMapper.writeValueAsString(bankTransfer))
                    .contentType(APPLICATION_JSON)
            )
        then:
            1 * bankTransferServiceMock.create(_ as BankTransfer) >> bankTransfer
            result.andExpect(status().isCreated())
    }
    
    def "should get status Bad Request when destination account is out of pattern"() {
        given:
            def bankTransfer = BankTransfers.create().with {
                destinationAccount = "0123456"
                it
            }
        when:
            ResultActions result = mvc.perform(MockMvcRequestBuilders.post(TRANSFER)
                    .content(objectMapper.writeValueAsString(bankTransfer))
                    .contentType(APPLICATION_JSON)
            )
        then:
            result.andExpect(status().isBadRequest())
    }
    
    def "should get a list with all bank transfers"() {
        given:
            def bankTransfer = BankTransfers.create()
        when:
            ResultActions result = mvc.perform(MockMvcRequestBuilders.get(TRANSFER)
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON)
            )
        then:
            1 * bankTransferServiceMock.getAll() >> [bankTransfer]
            result.andExpect(status().isOk())
                    .andExpect(jsonPath('$[0]').exists())
                    .andExpect(jsonPath('$[0].sourceAccount').exists())
    }
    
}
