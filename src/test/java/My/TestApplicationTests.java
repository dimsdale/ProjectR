package My;

import My.model.Contact;
import My.services.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebAppConfiguration
public class TestApplicationTests {

	public static final Logger log = Logger.getLogger(TestApplicationTests.class.getName());

	private static List<Contact> testList = new ArrayList<Contact>();

	@Autowired
	ContactService contactService;

	static {

		for (int i = 0; i < 100; i++)
		{
			Contact contact = new Contact(i, "Tomcitty");
			testList.add(contact);
		}

	}

	@Test
	public void testBothSizeCollections() {
		Integer size = testList.size();
		List<Contact> filterTest = contactService.regExFilterContacts(testList, "");
		Assert.isTrue(size.equals(filterTest.size()));
	}
	
}
