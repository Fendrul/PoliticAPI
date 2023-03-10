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

    public Argument() {
        this.date = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "argument_id", nullable = false, insertable = false, updatable = false)
    private Long argument_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "answer_to", nullable = true)
    Argument answerTo;

    @OneToMany(mappedBy = "answerTo")
    List<Argument> answers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    Client author;

    @ManyToMany(mappedBy = "argumentsSourced")
    List<Source> sources = new ArrayList<>();

    @ManyToMany(mappedBy = "argumentsInCategory")
    List<Category> categories = new ArrayList<>();
}
