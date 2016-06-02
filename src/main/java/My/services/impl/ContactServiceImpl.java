package My.services.impl;


import My.model.Contact;
import My.repositories.ContactRepository;
import My.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContactServiceImpl implements ContactService {

    Logger logger = Logger.getLogger(ContactServiceImpl.class.getName());

    @Autowired
    private ContactRepository contactRepository;

    @Override
    @Cacheable("contacts")
    @Transactional
    public List<Contact> getAllContacts() {
        logger.info("Getting contacts from DB");
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> regExFilterContacts(String filter) {
        Pattern pattern = Pattern.compile(filter);
        Matcher matcher;
        List<Contact> allContacts = getAllContacts();
        if (filter.equals("") || filter.equals(null))
        {
            return allContacts;
        }
        logger.info("Start filtering...");
        List<Contact> filterListContact = new ArrayList<Contact>();
        for (Contact oneContact: allContacts)
        {
            matcher = pattern.matcher(oneContact.getName());
            if (!matcher.find())
            {
                filterListContact.add(oneContact);
            }
        }
        return filterListContact;
    }

    @Override
    @CacheEvict(value = "contacts", allEntries = true)
    public void evictCache() {
        logger.info("Evict Cache");
    }
}
