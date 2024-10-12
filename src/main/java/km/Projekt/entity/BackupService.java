package km.Projekt.entity;

// L1 - FACADE - tworzenie kopii zapasowej notatek
public class BackupService {
    public void createBackup(Note note) {
        System.out.println("Backup stworzony dla notatki " + note.getContent()); //Tworzenie kopii zapasowej
    }
}
