package km.Projekt.entity;

import java.util.ArrayList;
import java.util.List;

public class TagManager { //dodawanie tagów do notatek
    public void addTags(Note note, String[] tags) {
        List<String> noteTags = note.getTags();

        if (noteTags == null) {
            noteTags = new ArrayList<>();
            note.setTags(noteTags);
        }

        for (String tag: tags) {
            noteTags.add(tag);
        }

        System.out.println("Tagi dodane do notatki" + String.join(", ", tags));
    }
}
