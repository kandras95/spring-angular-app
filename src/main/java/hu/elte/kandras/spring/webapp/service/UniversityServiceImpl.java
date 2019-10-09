package hu.elte.kandras.spring.webapp.service;

import hu.elte.kandras.spring.webapp.model.University;
import hu.elte.kandras.spring.webapp.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University save(University university) {
        return universityRepository.save(university);
    }

    @Override
    public Optional<University> findById(Integer id) {
        return universityRepository.findById(id);
    }

    @Override
    public List<University> findAll() {
        return (List<University>) universityRepository.findAll();
    }

    @Override
    public void delete(University university) {
        universityRepository.delete(university);
    }
}
