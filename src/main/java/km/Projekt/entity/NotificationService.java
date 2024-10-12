package km.Projekt.entity;

import java.security.Principal;

// L1 - FACADE - wysylanie powiadomień do użytkownika
public class NotificationService {
    public void sendNotification(Principal principal, String message) {
        System.out.println("Notyfikacja wysłana do " + principal.getName() + ": " + message); //wysyłanie powiadomień
    }
}
