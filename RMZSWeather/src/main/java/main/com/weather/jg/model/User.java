package main.com.weather.jg.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String login;
    private String password;

    @OneToMany(mappedBy = "userFK", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Session> session;

}




