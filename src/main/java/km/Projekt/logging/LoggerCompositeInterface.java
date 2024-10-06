package km.Projekt.logging;


//L1 - COMPOSITE - interfejs dla obiektów struktury
public interface LoggerCompositeInterface {
    void logMessage(String message);
    boolean isError();
    String textPosition(String position);

}
