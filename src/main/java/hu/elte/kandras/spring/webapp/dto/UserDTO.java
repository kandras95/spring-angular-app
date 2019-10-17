package hu.elte.kandras.spring.webapp.dto;

import hu.elte.kandras.spring.webapp.model.Person;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {

    private String username;

    private List<String> roles = new ArrayList<>();

    private Person person;
}
