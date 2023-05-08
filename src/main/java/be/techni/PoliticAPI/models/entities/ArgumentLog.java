package be.techni.PoliticAPI.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "argument_log")
public class ArgumentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "argument_log_id", nullable = false)
    private Long ArgumentLogId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "modification_date", nullable = false)
    private LocalDateTime modificationDate;

    @ManyToOne
    @JoinColumn(name = "argument_id", nullable = false)
    private Argument argument;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}