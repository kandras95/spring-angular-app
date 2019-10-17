package hu.elte.kandras.spring.webapp.web;

import hu.elte.kandras.spring.webapp.dto.AppError;
import hu.elte.kandras.spring.webapp.dto.UserDTO;
import hu.elte.kandras.spring.webapp.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final PersonService personService;

    @Autowired
    public AuthenticationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAuthenticatedUser() {
        logger.info("getAuthenticatedUser called");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            logger.info("User not logged in!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AppError("User not logged in!"));
        }
        logger.info("User logged in, auth.getPrincipal(): " + auth.getPrincipal());
        return ResponseEntity.ok(auth.getPrincipal());
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/user-details")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            logger.info("User is anonymous. User not logged in!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AppError("User not logged in!"));
        }
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) auth.getAuthorities();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(auth.getName());
        userDTO.setPerson(personService.findByUsername(auth.getName()).orElse(null));
        for (GrantedAuthority authority : authorities) {
            userDTO.getRoles().add(authority.getAuthority());
        }
        return ResponseEntity.ok(userDTO);
    }
}
