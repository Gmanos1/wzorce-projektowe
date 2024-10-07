package km.Projekt.logging;

// L1 - COMPOSITE - klasa do załadowanie wiadomosci do listy
public class ShowMessage {
    private MessageHandler handler = new MessageHandler();

    public void loadMessages(LoggerCompositeInterface... messages) {
        handler.addToList(messages);
    }
}
