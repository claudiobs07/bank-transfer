package br.com.claudiobs.transacao.controller

import br.com.claudiobs.transacao.AbstractTests
import br.com.claudiobs.transacao.domain.BankTransfer
import br.com.claudiobs.transacao.fixture.BankTransfers
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static br.com.claudiobs.transacao.controller.Endpoints.TRANSFER
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class BankTransferControllerIT extends AbstractTests {
    
    @Autowired
    BankTransferController bankTransferController
    
    @Autowired
    ObjectMapper objectMapper
    
    def setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build()
    }
    
    def "should check created bank transfer response"() {
        given:
            def bankTransfer = BankTransfers.create()
        when:
            ResultActions result = mvc.perform(post(TRANSFER)
                    .content(objectMapper.writeValueAsString(bankTransfer))
                    .contentType(APPLICATION_JSON)
            )
        then:
            result.andExpect(status().isCreated())
            String content = result.andReturn().response.contentAsString
            def persisted = objectMapper.readValue(content, BankTransfer)
            persisted.id
            persisted.sourceAccount == bankTransfer.sourceAccount
    }

}
