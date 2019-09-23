package hu.elte.kandras.spring.webapp.repository;

import hu.elte.kandras.spring.webapp.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
