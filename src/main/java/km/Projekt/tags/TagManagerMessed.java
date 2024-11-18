package km.Projekt.tags;

import java.util.*;

public class TagManagerMessed {
    private List<String> tags = new ArrayList<>(); // lista wszystkich tagów w systemie
    private Map<String, List<String>> tagsPerNote = new HashMap<>(); // mapa tagów przypisanych do notatek

    public TagManagerMessed() {
        System.out.println("Tag manager initialized");
    }

    // Dodawanie tagu do systemu
    public void addTagMessed(String tagName, boolean isPublic, int level, String description) {
        if (tagName != null && !tagName.isEmpty()) {
            tags.add(tagName + " (public: " + isPublic + ", poziom: " + level + ", opis: " + description + ")");
            System.out.println("Tag " + tagName + " został dodany do systemu");
        } else {
            System.out.println("Error: błędna nazwa tagu");
        }
    }

    // Przypisywanie tagu do notatki
    public void assignTagToNote(String noteId, String tagName) {
        if (tagsPerNote.containsKey(noteId)) {
            tagsPerNote.get(noteId).add(tagName);
        } else {
            List<String> newList = new ArrayList<>();
            newList.add(tagName);
            tagsPerNote.put(noteId, newList);
        }
        System.out.println("Tag " + tagName + " został przypisany do notatki " + noteId);
    }

    // Wyszukiwanie notatek po tagu
    public List<String> findNotes(String tagName) {
        List<String> results = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : tagsPerNote.entrySet()) {
            if (entry.getValue().contains(tagName)) {
                results.add(entry.getKey());
            }
        }
        return results;
    }

    // Usuwanie tagu z notatki
    public void removeTag(String noteId, String tagName) {
        if (!tagsPerNote.containsKey(noteId)) {
            System.out.println("Error: notatka nie została znaleziona");
            return;
        }

        List<String> tagList = tagsPerNote.get(noteId);
        if (!tagList.contains(tagName)) {
            System.out.println("Error: tag nie został znaleziony");
            return;
        }

        tagList.remove(tagName);
        if (tagList.isEmpty()) {
            tagsPerNote.remove(noteId);
            System.out.println("Note " + noteId + " nie ma tagów i zostanie usunięta z listy");
        } else {
            System.out.println("Tag " + tagName + " usunięty z notatki " + noteId);
        }
    }

    // Wyświetlenie wszystkich tagów
    public void showTags() {
        for (String tag : tags) {
            System.out.println("Tag: " + tag);
        }
    }

    // Przetwarzanie tagów
    public void processTags(String noteId, String tagName, boolean add) {
        if (add) {
            assignTagToNote(noteId, tagName);
        } else {
            if (tagsPerNote.containsKey(noteId) && tagsPerNote.get(noteId).contains(tagName)) {
                tagsPerNote.get(noteId).remove(tagName);
                if (tagsPerNote.get(noteId).isEmpty()) {
                    tagsPerNote.remove(noteId);
                }
                System.out.println("Tag " + tagName + " usunięty z notatki " + noteId);
            } else {
                System.out.println("Tag " + tagName + " nie znaleziony dla notatki " + noteId);
            }
        }
    }

    // Liczba tagów przypisanych do notatki
    public int countTags(String noteId) {
        if (tagsPerNote.containsKey(noteId)) {
            return tagsPerNote.get(noteId).size();
        }
        return 0;
    }
}