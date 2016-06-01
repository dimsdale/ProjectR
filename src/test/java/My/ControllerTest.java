package My;

import My.model.Contact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebAppConfiguration
public class ControllerTest {

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mvc;

    protected void setMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    protected String toJson(Contact obj) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    protected  <T> T mapFromJson(String json, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(json, tClass);
    }
}
