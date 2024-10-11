package km.Projekt.entity;

import java.security.Principal;

// L1 - FACADE - manager notatek
public class NoteManagerFacade { //uproszczenie korzystania z funkcji, wykonuje je automatycznie
    private TagManager tagManager;
    private NotificationService notificationService;
    private BackupService backupService;

    public NoteManagerFacade() {
        this.tagManager = new TagManager();
        this.notificationService = new NotificationService();
        this.backupService = new BackupService();
    }

    public void addNoteWithFeatures(Note note, Principal principal, String[] tags) {
        tagManager.addTags(note, tags); //dodawanie tagów do notatek
        notificationService.sendNotification(principal, "Dodano notatkę: " + note.getContent()); //wysyłanie powiedomień
        backupService.createBackup(note); //stworzenie kopii zapasowej notatki
    }
}
