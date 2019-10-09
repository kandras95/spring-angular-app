package hu.elte.kandras.spring.webapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private Integer popularity;

    @OneToMany(targetEntity = Subject.class, mappedBy = "university")
    private List<Subject> subjects;
}
