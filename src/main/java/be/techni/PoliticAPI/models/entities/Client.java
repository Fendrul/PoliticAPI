package be.techni.PoliticAPI.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Long client_id;

    private String name;

    @OneToMany(mappedBy = "author")
    List<Argument> arguments = new ArrayList<>();
}
