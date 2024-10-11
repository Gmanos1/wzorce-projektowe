package km.Projekt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
@Table(name = "Notes")
public class Note implements Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @NotEmpty(message = "Tytuł nie może być pusty")
    public String title;
    @NotEmpty(message = "Zawartość nie może być pusta")
    public String text;
    public Boolean isPublic = false;
    public String content;
    @ElementCollection
    public List<String> tags; //lista tagów dla notatki

    @ManyToOne
    public User user;

    @Override
    public void displayInfo() {
        System.out.println("Jestem notatka o id " + id.toString() + ", o tytule " + title);
    }
}
