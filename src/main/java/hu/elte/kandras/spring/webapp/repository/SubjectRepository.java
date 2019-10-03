package hu.elte.kandras.spring.webapp.repository;

import hu.elte.kandras.spring.webapp.model.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}
