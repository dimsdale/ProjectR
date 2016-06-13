package My.controller;

import My.model.Contact;
import My.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MainController {

    private static final Logger log = Logger.getLogger(MainController.class.getName());

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/hello/contacts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Collection<Contact>> getContacts(@RequestParam(value = "nameFilter", defaultValue = "") String name) {
        log.info("Filter String is " + name);
        if (!validateRegExp(name))
        {
            return new ResponseEntity<Collection<Contact>>(HttpStatus.BAD_REQUEST);
        }
        List<Contact> filterContacts = contactService.regExFilterContacts(name);
        Integer filterContactSize = filterContacts.size();
        if (filterContactSize.equals(0)) {
            log.info("For you query contacts not found");
            return new ResponseEntity<Collection<Contact>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Contact>>(filterContacts, HttpStatus.OK);
    }

    public boolean validateRegExp(String regExp){
        int counterScopeFigure = 0;
        int counterScopeSquare = 0;
        for(int i = 0, k = regExp.length(); i < k; i++)
        {
            if (regExp.charAt(i)=='(')
            {
                counterScopeFigure++;
                continue;
            }
            if (regExp.charAt(i)==')')
            {
                counterScopeFigure--;
                continue;
            }
            if (regExp.charAt(i)=='[')
            {
                counterScopeSquare++;
                continue;
            }
            if (regExp.charAt(i)==']')
            {
                counterScopeSquare--;
                continue;
            }
        }
        if ((counterScopeFigure == 0) && (counterScopeSquare == 0))  return true;
        return false;
    }
}
