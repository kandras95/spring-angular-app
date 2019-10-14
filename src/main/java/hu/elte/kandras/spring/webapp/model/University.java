package hu.elte.kandras.spring.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Subject> subjects;
}
