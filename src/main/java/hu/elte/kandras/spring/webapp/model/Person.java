package hu.elte.kandras.spring.webapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hu.elte.kandras.spring.webapp.model.enums.PersonRole;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private PersonRole role;

    @ManyToMany
    @JoinTable
    @JsonBackReference
    private List<Subject> subjectList;
}
