package hu.elte.kandras.spring.webapp.service;

import hu.elte.kandras.spring.webapp.model.Subject;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SubjectService {

    Subject save(Subject subject);

    @Transactional
    Optional<Subject> findById(Integer id);

    List<Subject> findAll();

    void delete(Subject subject);
}
