package hu.elte.kandras.spring.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String code;

    private String description;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<Person> persons = new ArrayList<>();

    @ManyToOne(targetEntity = University.class, optional = false)
    @JoinColumn
    private University university;

    public int getPersonSize() {
        return persons.size();
    }
}
