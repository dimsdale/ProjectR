package My;

import My.model.Contact;
import My.services.ContactService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebAppConfiguration
@Transactional
public class MainControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    private void setMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        contactService.evictCache();
    }

    private String toJson(Contact obj) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    private   <T> T mapFromJson(String json, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(json, tClass);
    }

    @Autowired
    private ContactService contactService;

    @Test
    public void getListContactTest() throws Exception {
        String uri = "/hello/contacts";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int code = result.getResponse().getStatus();
        Assert.assertEquals("Failure!", 200, code);
        Assert.assertTrue("Failure - must have Content", content.trim().length() > 0);
    }
    @Test
    public void getFilterListContactsTest() throws Exception {
        String urifilter = "/hello/contacts?nameFilter=^A .$";
        String uri = "/hello/contacts";
        MvcResult resultFilter = mvc.perform(MockMvcRequestBuilders.get(urifilter).accept(MediaType.APPLICATION_JSON)).andReturn();
        String contentFilter = resultFilter.getResponse().getContentAsString();
        int codeFilter = resultFilter.getResponse().getStatus();
        Assert.assertEquals("Failure!", 200, codeFilter);
        Assert.assertTrue("Failure - must have Content", contentFilter.trim().length() > 0);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = resultFilter.getResponse().getContentAsString();
        int code = result.getResponse().getStatus();
        Assert.assertEquals("Failure!", 200, code);
        Assert.assertTrue("Failure - must have Content", content.trim().length() > 0);
        Assert.assertNotEquals(resultFilter, result);

    }
    String notfounrUri = "/hello/contacts?nameFilter=^.*[aeiy].*$";
}