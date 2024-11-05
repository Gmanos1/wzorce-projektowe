package km.Projekt.interfaces;

public class EditingUserNotification extends AbstractEditingUser {

    @Override
    public void editUserDataNotification(Long dataId, String newData) {
        System.out.println("EDIT: " + dataId + ", " + newData);
    }
}
