package km.Projekt.tags;

import java.util.*;

public class NoteTagManager { //przypisywanie tagów do notatek
    private final Map<String, Set<String>> noteTags = new HashMap<>();
    private final TagSystemManager tagSystemManager;
    private final int NO_TAGS = 0;

    public NoteTagManager(TagSystemManager tagSystemManager) {
        this.tagSystemManager = tagSystemManager;
    }

    public void assignTagToNote(String noteId, String tagName) {
        validateNoteId(noteId);
        validateTagExists(tagName);

        noteTags.computeIfAbsent(noteId, k -> new HashSet<>()).add(tagName);
    }

    public int countTags(String noteId) {
        if (noteTags.containsKey(noteId)) {
            return noteTags.get(noteId).size();
        } else {
            return NO_TAGS;
        }
    }

    public void removeTagFromNote(String noteId, String tagName) {
        validateNoteId(noteId);

        if (!noteTags.containsKey(noteId)) {
            throw new IllegalArgumentException("Note '" + noteId + "' does not exist.");
        }

        Set<String> tags = noteTags.get(noteId);
        if (!tags.remove(tagName)) {
            throw new IllegalArgumentException("Tag '" + tagName + "' is not assigned to note '" + noteId + "'.");
        }

        if (tags.isEmpty()) {
            noteTags.remove(noteId);
        }
    }

    public Set<String> getTagsForNote(String noteId) {
        return noteTags.getOrDefault(noteId, Collections.emptySet());
    }

    public List<String> findNotesByTag(String tagName) {
        validateTagExists(tagName);

        List<String> notes = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : noteTags.entrySet()) {
            if (entry.getValue().contains(tagName)) {
                notes.add(entry.getKey());
            }
        }
        return notes;
    }

    private void validateNoteId(String noteId) {
        if (noteId == null || noteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Note ID cannot be null or empty.");
        }
    }

    private void validateTagExists(String tagName) {
        if (!tagSystemManager.isTagAvailable(tagName)) {
            throw new IllegalArgumentException("Tag '" + tagName + "' does not exist in the system.");
        }
    }
}