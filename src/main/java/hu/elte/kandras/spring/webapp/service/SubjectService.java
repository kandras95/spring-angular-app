package hu.elte.kandras.spring.webapp.service;

import hu.elte.kandras.spring.webapp.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    Subject save(Subject subject);

    Optional<Subject> findById(Integer id);

    List<Subject> findAll();

    void delete(Subject subject);
}
