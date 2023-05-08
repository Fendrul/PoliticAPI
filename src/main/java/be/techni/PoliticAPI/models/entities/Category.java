package be.techni.PoliticAPI.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long category_id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @ManyToMany
    @JoinTable(
            name = "argument_in_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "argument_id")
    )
    List<Argument> arguments = new ArrayList<>();

    public Category() {}

    public Category(String name) {
        this.name = name;
    }
}
