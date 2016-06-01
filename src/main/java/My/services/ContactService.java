package My.services;


import My.model.Contact;

import java.util.List;

public interface ContactService {

    public List<Contact> getAllContacts();

    public List<Contact> regExContacts (String filter);
}
