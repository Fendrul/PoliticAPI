package be.techni.PoliticAPI.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "argument")
@Getter
@Setter
public class Argument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "argument_id", nullable = false)
    private Long argument_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    LocalDateTime date;

    @ManyToMany
    @JoinTable(name = "argument_answer_to",
            joinColumns = @JoinColumn(name = "argument_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_to_id"))
    List<Argument> answerTo = new ArrayList<>();

    @ManyToMany(mappedBy = "answerTo")
    List<Argument> answerFrom = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    Client author;

    @ManyToMany(mappedBy = "argumentsSourced")
    List<Source> sources = new ArrayList<>();

    @ManyToMany(mappedBy = "argumentsInCategory")
    List<Category> categories = new ArrayList<>();
}
