package My.controller;

import My.model.Contact;
import My.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping
@EnableCaching
public class MainController {

    public static final Logger log = Logger.getLogger(MainController.class.getName());

    @Autowired
    private  ContactService contactService;

    @RequestMapping(value = "/hello/contacts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Contact>> getContacts(@RequestParam(value = "nameFilter", defaultValue = "") String name)
    {
        log.info("Filter String is " + name);
        List<Contact> filterContacts = contactService.regExFilterContacts(name);
        if(filterContacts==null){
            log.info("Contacts not found");
            return new ResponseEntity<Collection<Contact>>(filterContacts, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Contact>>(filterContacts, HttpStatus.OK);
    }
}
