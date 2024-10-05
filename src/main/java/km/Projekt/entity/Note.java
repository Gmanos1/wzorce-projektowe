package km.Projekt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Tytuł nie może być pusty")
    private String title;
    @NotEmpty(message = "Zawartość nie może być pusta")
    private String text;
    private Boolean isPublic = false;

    @ManyToOne
    private User user;
}
