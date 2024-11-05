package km.Projekt.interfaces;

public class DeletingNoteNotification extends AbstractNoteDeleting {
    @Override
    public void deleteNoteDataNotification(Integer dataId) {
        System.out.println("DELETE: " + dataId);
    }
}
