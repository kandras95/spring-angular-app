package hu.elte.kandras.spring.webapp.repository;

import hu.elte.kandras.spring.webapp.model.Person;
import hu.elte.kandras.spring.webapp.model.enums.PersonRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAllByRole(PersonRole role);

    Optional<Person> findByUsername(String username);
}
