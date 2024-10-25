package km.Projekt.entity.strategy;

public class PriorityStyle implements NoteStyleStrategy {
    private String priority;

    public PriorityStyle(String priority) {
        this.priority = priority;
    }

    @Override
    public String applyStyle(String noteContent) {
        return "Style: priority " + noteContent + "; " + priority;
    }
}
