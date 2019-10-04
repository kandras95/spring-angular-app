package hu.elte.kandras.spring.webapp.service;


import hu.elte.kandras.spring.webapp.model.Person;
import hu.elte.kandras.spring.webapp.model.enums.PersonRole;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person save(Person person);

    Optional<Person> findById(Integer id);

    List<Person> findAll();

    void delete(Person person);

    List<Person> findAllByRole(PersonRole role);
}
