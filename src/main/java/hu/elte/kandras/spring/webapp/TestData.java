package hu.elte.kandras.spring.webapp;

import hu.elte.kandras.spring.webapp.model.Person;
import hu.elte.kandras.spring.webapp.model.Subject;
import hu.elte.kandras.spring.webapp.model.enums.PersonRole;
import hu.elte.kandras.spring.webapp.service.PersonService;
import hu.elte.kandras.spring.webapp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestData {

    private final PersonService personService;

    private final SubjectService subjectService;

    @Autowired
    public TestData(PersonService personService, SubjectService subjectService) {
        this.personService = personService;
        this.subjectService = subjectService;
    }

    @PostConstruct
    public void initTestData() {
        initPersons();
        initSubjects();
    }

    private void initPersons() {
        Person person1 = new Person();
        person1.setName("Person 1");
        person1.setRole(PersonRole.STUDENT);
        personService.save(person1);

        Person person2 = new Person();
        person2.setName("Person 2");
        person2.setRole(PersonRole.STUDENT);
        personService.save(person2);

        Person person3 = new Person();
        person3.setName("Person 3");
        person3.setRole(PersonRole.INSTRUCTOR);
        personService.save(person3);
    }

    private void initSubjects() {
        Subject subject1 = new Subject();
        subject1.setName("Subject1");
        subject1.setCode("sub001");
        subject1.setDescription("Discription of Subject1");
        subjectService.save(subject1);

        Subject subject2 = new Subject();
        subject2.setName("Subject2");
        subject2.setCode("sub002");
        subject2.setDescription("Discription of Subject2");
        subjectService.save(subject2);

        Subject subject3 = new Subject();
        subject3.setName("Subject3");
        subject3.setCode("sub003");
        subject3.setDescription("Discription of Subject3");
        subjectService.save(subject3);
    }
}
