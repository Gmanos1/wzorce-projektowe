package km.Projekt.logging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// L1 - COMPOSITE - handler tworzacy liste wiadomosci
public class MessageHandler extends Logger {
    protected List<LoggerCompositeInterface> messagesHandler = new ArrayList<>();

    public MessageHandler(LoggerCompositeInterface... messages) {
        super("", false);
        addToList(messages);
    }

    public void addToList(LoggerCompositeInterface... messages) {
        messagesHandler.addAll(Arrays.asList(messages));
    }

    public void removeFromList(LoggerCompositeInterface... messages) {
        messagesHandler.removeAll(Arrays.asList(messages));
    }

    @Override
    public void logMessage(String message) {
        for (LoggerCompositeInterface handleMessage : messagesHandler) {
            handleMessage.logMessage(message);
        }
    }

    @Override
    public boolean isError() {
        for (LoggerCompositeInterface handleMessage : messagesHandler) {
            return handleMessage.isError();
        }
        return true;
    }

    @Override
    public String textPosition(String position) {
        for (LoggerCompositeInterface handleMessage : messagesHandler) {
            return handleMessage.textPosition(position);
        }
        return "";
    }

}