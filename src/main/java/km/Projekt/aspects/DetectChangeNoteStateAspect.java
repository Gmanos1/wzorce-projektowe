package km.Projekt.aspects;

import km.Projekt.entity.Note;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DetectChangeNoteStateAspect {

    @Before("execution(* km.Projekt.controllers.NoteController.editNotePOST(..)) && args(note, ..)")
    public void logStateChange(Note note) {
        if (!note.isPublic) {
            System.out.println("Notatka z id " + note.getId() + " stała się prywatna");
        } else {
            System.out.println("Notatka z id " + note.getId() + " stała się publiczna");
        }
    }
}
