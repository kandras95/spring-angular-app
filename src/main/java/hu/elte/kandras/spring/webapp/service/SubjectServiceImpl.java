package hu.elte.kandras.spring.webapp.service;

import hu.elte.kandras.spring.webapp.model.Subject;
import hu.elte.kandras.spring.webapp.repository.SubjectRepository;
import hu.elte.kandras.spring.webapp.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final UniversityRepository universityRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              UniversityRepository universityRepository) {
        this.subjectRepository = subjectRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Subject save(Subject subject) {
        if (subject.getUniversity() == null) {
            subject.setUniversity(universityRepository.findById(1).orElse(null));
        }
        return subjectRepository.save(subject);
    }

    @Override
    public Optional<Subject> findById(Integer id) {
        return subjectRepository.findById(id);
    }

    @Override
    public List<Subject> findAll() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }
}
