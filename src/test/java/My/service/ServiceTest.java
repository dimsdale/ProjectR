package My.service;

import My.TestApplication;
import My.model.Contact;
import My.services.ContactService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebAppConfiguration
@Transactional
public class ServiceTest {

    @Autowired
    private ContactService service;

    @Before
    public void setUp() {
        service.evictCache();
    }

    @Test
    public void getContactsTest()
    {
        List<Contact> allContacts = service.getAllContacts();
        Assert.assertNotNull(allContacts);

    }

    @Test
    public void getFilterContactTest(){
        String filter = "^A. *$";
        List<Contact> allContacts = service.getAllContacts();
        List<Contact> filterContacts = service.regExFilterContacts(filter);
        Assert.assertNotEquals("Filter not work, or work not correctly", allContacts.size(), filterContacts.size());
    }

    @Test
    public void getNullSizeContactListTest(){
        String notFoundUri = "/hello/contacts?nameFilter=^.*[a-z].*$";
        List<Contact> filterContact = service.regExFilterContacts(notFoundUri);
        Assert.assertEquals("Filter working not correct", 0, filterContact.size());
    }
}
