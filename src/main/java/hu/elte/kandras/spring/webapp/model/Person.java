package hu.elte.kandras.spring.webapp.model;

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
    private List<Subject> subjects;
}
