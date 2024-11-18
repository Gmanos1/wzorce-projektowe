package km.Projekt.tags;

import java.util.HashSet;
import java.util.Set;

public class TagSystemManager { //globalne zarządzanie tagami
    private final Set<String> availableTags = new HashSet<>();

    public void addTag(String tagName) {
        validateTagName(tagName);
        if (!availableTags.add(tagName)) {
            throw new IllegalArgumentException("Tag '" + tagName + "' already exists.");
        }
    }

    public boolean isTagAvailable(String tagName) {
        return availableTags.contains(tagName);
    }

    public Set<String> getAvailableTags() {
        return new HashSet<>(availableTags);
    }

    private void validateTagName(String tagName) {
        if (tagName == null || tagName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name cannot be null or empty.");
        }
    }
}