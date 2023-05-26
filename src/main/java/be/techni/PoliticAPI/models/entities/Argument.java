package be.techni.PoliticAPI.models.entities;

import be.techni.PoliticAPI.models.enums.ArgumentState;
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
    @Column(name = "date", nullable = false)
    LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "answer_to", nullable = true)
    Argument answerTo;
    @OneToMany(mappedBy = "answerTo")
    List<Argument> answers = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User author;
    @ManyToMany(mappedBy = "argumentsSourced")
    List<Source> sources = new ArrayList<>();
    @ManyToMany(mappedBy = "arguments")
    List<Category> categories = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "argument_id", nullable = false, insertable = false, updatable = false)
    private Long argument_id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private ArgumentState state;


    @OneToMany(mappedBy = "argument")
    private List<ArgumentLog> argumentLogs = new ArrayList<>();

    public Argument() {
        this.date = LocalDateTime.now();
    }
}
