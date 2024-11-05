package km.Projekt.interfaces;

public abstract class AbstractEditingUser implements NotifyEditingUserInterface {
    public abstract void editUserDataNotification(Long dataId, String newData);
}
