package main.com.weather.jg.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @ManyToOne
    @JoinColumn(name ="userFK")
    private User userFK;

}
