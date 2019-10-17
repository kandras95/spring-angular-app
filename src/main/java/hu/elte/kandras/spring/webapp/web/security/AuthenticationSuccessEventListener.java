package hu.elte.kandras.spring.webapp.web.security;

import hu.elte.kandras.spring.webapp.model.Person;
import hu.elte.kandras.spring.webapp.model.enums.PersonRole;
import hu.elte.kandras.spring.webapp.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessEventListener.class);

    private final PersonService personService;

    @Autowired
    public AuthenticationSuccessEventListener(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        UserDetails principal = (UserDetails) event.getAuthentication().getPrincipal();
        String username = principal.getUsername();
        logger.info("User successfully logged in, username: {}", username);
        List<String> roles = new ArrayList<>();
        principal.getAuthorities().forEach(o -> {
            String authority = o.getAuthority();
            roles.add(authority);
        });
        logger.info("User's authorities: {}", roles);
        Optional<Person> byUsername = personService.findByUsername(username);
        if (byUsername.isPresent()) {
            Person existingPerson = byUsername.get();
            PersonRole newRole = detectRole(roles);
            if (!newRole.equals(existingPerson.getRole())) {
                existingPerson.setRole(newRole);
                personService.save(existingPerson);
            }
        } else {
            Person newPerson = new Person();
            newPerson.setUsername(username);
            newPerson.setRole(detectRole(roles));
            personService.save(newPerson);
        }

    }

    private PersonRole detectRole(List<String> roles) {
        if (roles.contains("ROLE_INSTRUCTOR")) {
            return PersonRole.INSTRUCTOR;
        } else {
            return PersonRole.INSTRUCTOR;
        }
    }
}