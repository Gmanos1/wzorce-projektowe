package km.Projekt.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import km.Projekt.logging.Logger;
import km.Projekt.validation.EditingValidation;
import km.Projekt.validation.PasswordValidation;
import km.Projekt.validation.RegistrationValidation;
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
@Table(name = "Users")
public class User implements Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long userid;
    @Size(min = 3, message = "Imię musi mieć co najmiej 3 znaki", groups = {RegistrationValidation.class, EditingValidation.class})
    public String name;
    @Size(min = 3, message = "Nazwisko musi mieć co najmniej 3 znaki", groups = {RegistrationValidation.class, EditingValidation.class})
    public String surname;
    @NotEmpty(message = "Login nie może być pusty", groups = {RegistrationValidation.class})
    public String login;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&{}./\\\\|_+-])[A-Za-z\\d@$!%*?&{}./\\\\|_+-]{8,}$", message = "Hasło musi zawierać co najmniej jedną dużą, mała literę, cyfre, znak specjalny i mieć 8 znaków", groups = {RegistrationValidation.class, PasswordValidation.class})
    public String password;
    @JoinColumn(name = "notes_id")
    @OneToMany(cascade = CascadeType.ALL)
    public List<Note> notes;

    public void displayInfo() {
        Logger logger = Logger.getInstance();
        logger.log("Jestem uzytkownikiem o id " + userid.toString() + ", i nazwie " + name);
    }
}