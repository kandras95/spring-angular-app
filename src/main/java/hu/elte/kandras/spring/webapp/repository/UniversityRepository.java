package hu.elte.kandras.spring.webapp.repository;

import hu.elte.kandras.spring.webapp.model.University;
import org.springframework.data.repository.CrudRepository;

public interface UniversityRepository extends CrudRepository<University, Integer> {
}
