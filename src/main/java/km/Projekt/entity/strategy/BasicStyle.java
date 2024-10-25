package km.Projekt.entity.strategy;

public class BasicStyle implements NoteStyleStrategy {

    @Override
    public String applyStyle(String noteContent) {
        return "Style: basic " + noteContent;
    }
}
