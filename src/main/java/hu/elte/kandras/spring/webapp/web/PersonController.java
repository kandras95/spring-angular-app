package hu.elte.kandras.spring.webapp.web;

import hu.elte.kandras.spring.webapp.model.Person;
import hu.elte.kandras.spring.webapp.model.Subject;
import hu.elte.kandras.spring.webapp.model.enums.PersonRole;
import hu.elte.kandras.spring.webapp.service.PersonService;
import hu.elte.kandras.spring.webapp.service.PersonServiceImpl;
import hu.elte.kandras.spring.webapp.service.SubjectService;
import hu.elte.kandras.spring.webapp.service.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    private PersonService personService;
    private SubjectService subjectService;

    @Autowired
    public PersonController(PersonServiceImpl personService, SubjectServiceImpl subjectService) {
        this.personService = personService;
        this.subjectService = subjectService;
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

    @PutMapping("/persons/{id}")
    public Object put(@RequestBody Person person, @PathVariable Integer id) {
        Optional<Person> byId = personService.findById(id);
        if (byId.isPresent()) {
            person.setId(id);
            personService.save(person);
            return byId.get();
        } else {
            return "Person with id (" + id + ") was not found";
        }
    }

    @DeleteMapping("/persons/{id}")
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

    @PostMapping("/{id}/subjects")
    public ResponseEntity<Person> insertSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        Optional<Person> byId = personService.findById(id);
        if (byId.isPresent()) {
            Person person = byId.get();
            Subject newSubject = subjectService.save(subject);
            person.getSubjects().add(newSubject);
            personService.save(person);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
