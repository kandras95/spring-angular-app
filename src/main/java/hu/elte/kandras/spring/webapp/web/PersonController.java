package hu.elte.kandras.spring.webapp.web;

import hu.elte.kandras.spring.webapp.model.Person;
import hu.elte.kandras.spring.webapp.model.enums.PersonRole;
import hu.elte.kandras.spring.webapp.service.PersonService;
import hu.elte.kandras.spring.webapp.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public List<Person> persons(@RequestParam(value = "role", defaultValue = "") PersonRole role) {
        if (role == null) {
            return personService.findAll();
        } else {
            return personService.findAllByRole(role);
        }
    }

    @GetMapping("/persons/{id}")
    public Object findById(@PathVariable Integer id) {
        Optional<Person> byId = personService.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return "Person with id (" + id + ") was not found";
        }
    }

    @DeleteMapping("/persons/{id}/delete")
    public String delete(@PathVariable Integer id) {
        Optional<Person> byId = personService.findById(id);
        if (byId.isPresent()) {
            personService.delete(byId.get());
            return "Person deleted";
        } else {
            return "Person with id (" + id + ") was not found";
        }
    }

    @PostMapping("/persons")
    public Person save(@RequestBody Person person) {
        return personService.save(person);
    }
}
