package My.services.impl;


import My.model.Contact;
import My.repositories.ContactRepository;
import My.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

    private static final Logger logger = Logger.getLogger(ContactServiceImpl.class.getName());

    @Autowired
    private ContactRepository contactRepository;

    @Override
    @Transactional
    @Cacheable(value = "contacts", key = "#contact.getId")
    public List<Contact> getAllContacts() {
        logger.info("Getting contacts from DB");
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> regExFilterContacts(String filter) throws PatternSyntaxException {
        List<Contact> filterListContact = new ArrayList<Contact>();
        Pattern pattern = Pattern.compile(filter);
        Matcher matcher;
        List<Contact> allContacts = getAllContacts();
        if (filter.equals("") || filter.equals(null)) {
            return allContacts;
        }

        logger.info("Start filtering...");
        for (Contact oneContact : allContacts) {
            matcher = pattern.matcher(oneContact.getName());
            if (!matcher.find()) {
                filterListContact.add(oneContact);
            }
        }
        return filterListContact;
    }



}
