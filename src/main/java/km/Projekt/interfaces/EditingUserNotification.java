package km.Projekt.interfaces;

public class EditingUserNotification extends AbstractEditingUser {

    @Override
    public void editUserDataNotification(Long dataId, String newData) {
        displayMessageOnConsole("EDIT: " + dataId + ", " + newData);
    }

    private static void displayMessageOnConsole(String message) {
        System.out.println(message);
    }
}
