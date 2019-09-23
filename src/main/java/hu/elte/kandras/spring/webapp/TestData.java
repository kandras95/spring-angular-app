package hu.elte.kandras.spring.webapp;

import hu.elte.kandras.spring.webapp.model.Person;
import hu.elte.kandras.spring.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestData {

    private final PersonService personService;

    @Autowired
    public TestData(PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    public void initTestData() {
        Person person1 = new Person();
        person1.setName("Person 1");
        personService.save(person1);

        Person person2 = new Person();
        person2.setName("Person 2");
        personService.save(person2);

        Person person3 = new Person();
        person3.setName("Person 3");
        personService.save(person3);
    }
}
