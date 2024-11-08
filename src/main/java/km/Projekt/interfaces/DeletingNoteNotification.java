package km.Projekt.interfaces;

public class DeletingNoteNotification extends AbstractNoteDeleting {
    @Override
    public void deleteNoteDataNotification(Integer dataId) {
        displayMessageOnConsole("DELETE: " + dataId);
    }

    private static void displayMessageOnConsole(String message) {
        System.out.println(message);
    }
}
