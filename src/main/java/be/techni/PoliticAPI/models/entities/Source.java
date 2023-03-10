package be.techni.PoliticAPI.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "source")
@Getter
@Setter
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "source_id", nullable = false)
    private Long source_id;

    @Column(name = "description", nullable = false, unique = true)
    String description;

    @ManyToMany
    @JoinTable(
            name = "argument_sourced",
            joinColumns = @JoinColumn(name = "source_id"),
            inverseJoinColumns = @JoinColumn(name = "argument_id")
    )
    List<Argument> argumentsSourced = new ArrayList<>();
}
