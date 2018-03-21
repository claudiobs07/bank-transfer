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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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
            ResultActions postResult = mvc.perform(post(TRANSFER)
                    .content(objectMapper.writeValueAsString(bankTransfer))
                    .contentType(APPLICATION_JSON)
            )
        then:
            postResult.andExpect(status().isCreated())
            String postContent = postResult.andReturn().response.contentAsString
            def created = objectMapper.readValue(postContent, BankTransfer)
            created.id
            created.sourceAccount == bankTransfer.sourceAccount
            created.destinationAccount == bankTransfer.destinationAccount
        when:
            ResultActions getResult = mvc.perform(get(TRANSFER)
                    .accept(APPLICATION_JSON)
            )
        then:
            getResult.andExpect(status().isOk())
            String getContent = getResult.andReturn().response.contentAsString
            def founds = objectMapper.readValue(getContent, BankTransfer[])
            founds.size() == 1
            founds.head().id == created.id
    }

}
