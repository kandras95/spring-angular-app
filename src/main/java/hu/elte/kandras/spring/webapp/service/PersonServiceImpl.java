package hu.elte.kandras.spring.webapp.service;

import hu.elte.kandras.spring.webapp.model.Person;
import hu.elte.kandras.spring.webapp.model.enums.PersonRole;
import hu.elte.kandras.spring.webapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepositorySpring;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepositorySpring) {
        this.personRepositorySpring = personRepositorySpring;
    }

    @Override
    public Person save(Person person) {
        return personRepositorySpring.save(person);
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return personRepositorySpring.findById((id));
    }

    @Override
    public List<Person> findAll() {
        return (List<Person>) (personRepositorySpring.findAll());
    }

    @Override
    public void delete(Person person) {
        if (person != null) {
            personRepositorySpring.delete(person);
        }
    }

    @Override
    public List<Person> findAllByRole(PersonRole role) {
        return personRepositorySpring.findAllByRole(role);
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return personRepositorySpring.findByUsername(username);
    }
}
