package My;

import My.services.ContactService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;


@Transactional
public class MainControllerTest extends ControllerTest {

    @Autowired
    private ContactService contactService;

    @Before
    public void setMvc(){
        super.setMvc();
        contactService.evictCache();
    }

    @Test
    public void getListContactTest() throws Exception {
        String uri = "/hello/contacts";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int code = result.getResponse().getStatus();
        Assert.assertEquals("Failure!", 200, code);
        Assert.assertTrue("Failure - must have Content", content.trim().length() > 0);
    }
}
