package br.com.claudiobs.transacao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@ActiveProfiles('test')
@ContextConfiguration(loader = SpringBootContextLoader, classes = Application)
@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class AbstractTests extends Specification{
    
    @Autowired
    WebApplicationContext context
    
    MockMvc mvc
}
