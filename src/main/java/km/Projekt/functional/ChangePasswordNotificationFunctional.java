package km.Projekt.functional;

import km.Projekt.entity.User;

@FunctionalInterface
public interface ChangePasswordNotificationFunctional {
    String changePasswordNotify(User user);
}
