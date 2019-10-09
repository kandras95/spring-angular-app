package hu.elte.kandras.spring.webapp.service;

import hu.elte.kandras.spring.webapp.model.University;

import java.util.List;
import java.util.Optional;

public interface UniversityService {
    University save(University university);

    Optional<University> findById(Integer id);

    List<University> findAll();

    void delete(University university);
}
