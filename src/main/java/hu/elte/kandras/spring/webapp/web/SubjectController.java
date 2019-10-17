package hu.elte.kandras.spring.webapp.web;

import hu.elte.kandras.spring.webapp.model.Subject;
import hu.elte.kandras.spring.webapp.service.SubjectService;
import hu.elte.kandras.spring.webapp.service.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SubjectController {

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectServiceImpl subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public List<Subject> subjects() {
        return subjectService.findAll();
    }

    @GetMapping("/subjects/{id}")
    public Object findById(@PathVariable Integer id) {
        Optional<Subject> byId = subjectService.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return "Subject with id (" + id + ") was not found";
        }
    }

    @GetMapping("/subjects/{id}/persons")
    public Object findPersonsById(@PathVariable Integer id) {
        Optional<Subject> byId = subjectService.findById(id);
        if (byId.isPresent()) {
            return byId.get().getPersons();
        } else {
            return "Subject with id (" + id + ") was not found";
        }
    }

    @DeleteMapping("/subjects/{id}/delete")
    public String delete(@PathVariable Integer id) {
        Optional<Subject> byId = subjectService.findById(id);
        if (byId.isPresent()) {
            subjectService.delete(byId.get());
            return "P deleted";
        } else {
            return "Subject with id (" + id + ") was not found";
        }
    }

    @PostMapping("/subjects")
    public String save(@RequestBody Subject subject) {
        Subject saved = subjectService.save(subject);
        return "Subject saved, id: " + saved.getId();
    }
}
