package hu.elte.kandras.spring.webapp.web;

import hu.elte.kandras.spring.webapp.model.University;
import hu.elte.kandras.spring.webapp.service.UniversityService;
import hu.elte.kandras.spring.webapp.service.UniversityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UniversityController {

    private UniversityService universityService;

    @Autowired
    public UniversityController(UniversityServiceImpl universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/universities")
    public List<University> universities() {
        return universityService.findAll();
    }

    @GetMapping("/universities/{id}")
    public Object findById(@PathVariable Integer id) {
        Optional<University> byId = universityService.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return "Subject with id (" + id + ") was not found";
        }
    }

    @GetMapping("/universities/{id}/subjects")
    public Object findSubjectsById(@PathVariable Integer id) {
        Optional<University> byId = universityService.findById(id);
        if (byId.isPresent()) {
            return byId.get().getSubjects();
        } else {
            return "Subject with id (" + id + ") was not found";
        }
    }

    @DeleteMapping("/universities/{id}/delete")
    public String delete(@PathVariable Integer id) {
        Optional<University> byId = universityService.findById(id);
        if (byId.isPresent()) {
            universityService.delete(byId.get());
            return "P deleted";
        } else {
            return "Subject with id (" + id + ") was not found";
        }
    }

    @PostMapping("/universities")
    public String save(@RequestBody University university) {
        University saved = universityService.save(university);
        return "Subject saved, id: " + saved.getId();
    }
}
