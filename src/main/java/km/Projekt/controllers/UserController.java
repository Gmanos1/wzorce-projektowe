package km.Projekt.controllers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import km.Projekt.dao.NoteDao;
import km.Projekt.dao.UserDao;
import km.Projekt.entity.statistics.SessionStatistics;
import km.Projekt.entity.User;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import km.Projekt.interfaces.AbstractEditingUser;
import km.Projekt.interfaces.EditingUserNotification;
import km.Projekt.iterators.UserIterator;
import km.Projekt.iterators.UserNameAnotherIterator;
import km.Projekt.iterators.UserNameIterator;
import km.Projekt.validation.EditingValidation;
import km.Projekt.validation.PasswordValidation;
import km.Projekt.validation.RegistrationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private NoteDao noteDao;
    SessionStatistics sessionStatistics = SessionStatistics.getInstance();
    private AbstractEditingUser abstractEditingUser;
    public UserController() {
        this.abstractEditingUser = new EditingUserNotification();
    }

    public void editUserAction(Long userId, String newData) {
        abstractEditingUser.editUserDataNotification(userId, newData);
    }

    @GetMapping("/login")
    public String loginPage() {
        sessionStatistics.incrementNumberOfLogins();
        System.out.println("LOGIN");
        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerPagePOST(@ModelAttribute @Validated(RegistrationValidation.class) User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userDao.findByLogin(user.getLogin()) != null){
            bindingResult.rejectValue("login",
                    "error.user",
                    "Taki użytkownik już istnieje");
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return "redirect:/login";
    }
    @GetMapping("/")
    public String rootDocPage(Model model, Principal principal) {
        model.addAttribute("user", userDao.findByLogin(principal.getName()));
        return "profile";
    }
    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        model.addAttribute("user", userDao.findByLogin(principal.getName()));
        sessionStatistics.incrementNumberOfProfileViews();
        return "profile";
    }

    private void addUsersToList(List<User> users) {
        Iterable<User> iUsers = userDao.findAll();
        for (User iUser : iUsers) {
            users.add(iUser);
        }
    }

    @GetMapping("/users")
    public String usersPage(Model model, @RequestParam(name = "filterName", required = false) String filterName) {
        List<User> users = new ArrayList<>();
        if (filterName == null || filterName.isBlank() || filterName.isEmpty()) {
            addUsersToList(users);
        } else {
            UserIterator userIterator = new UserNameIterator(filterName);
            // L3 Liskov
            UserIterator userAnotherIterator = new UserNameAnotherIterator(filterName);
            List<UserIterator> userIteratorList = new ArrayList<UserIterator>();
            userIteratorList.add(userIterator);
            userIteratorList.add(userAnotherIterator);

            for(UserIterator ui : userIteratorList){
                ui.getNext();
            }
            while(userIterator.hasNext()) users.add(userIterator.getNext());
        }
        List<Integer> numberOfNotes = new ArrayList<>();
        for (User user : users) {
            numberOfNotes.add(noteDao.findAllByUser(user).size());
        }
        model.addAttribute("usersList", users);
        model.addAttribute("numberOfNotes", numberOfNotes);
        return "users";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }

    @GetMapping("/delete")
    @Transactional
    public String deleteUser(Principal principal){
        User user = userDao.findByLogin(principal.getName());
        noteDao.deleteAllByUser(user);
        userDao.delete(user);
        return "redirect:/logout";
    }

    @GetMapping("/edit")
    public String editUser(Model model, Principal principal){
        model.addAttribute("user", userDao.findByLogin(principal.getName()));
        return "edit";
    }

    @PostMapping("/edit")
    public String editPagePOST(@ModelAttribute @Validated(EditingValidation.class) User user,
                               BindingResult bindingResult,
                               Principal principal) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        User previousUser = userDao.findByLogin(principal.getName());
        user.setUserid(previousUser.getUserid());
        user.setLogin(previousUser.getLogin());
        user.setPassword(previousUser.getPassword());
        userDao.save(user);

        editUserAction(user.getUserid(), user.getName());

        return "profile";
    }
    @PostMapping("/editpassword")
    public String editPagePasswordPOST(@ModelAttribute @Validated(PasswordValidation.class) User user,
                               BindingResult bindingResult,
                               Principal principal) {
        User previousUser = userDao.findByLogin(principal.getName());
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        setUserProperties(user, previousUser, passwordEncoder);
        userDao.save(user);
        return "profile";
    }

    private static void setUserProperties(User user, User previousUser, PasswordEncoder passwordEncoder) {
        user.setUserid(previousUser.getUserid());
        user.setLogin(previousUser.getLogin());
        user.setName(previousUser.getName());
        user.setSurname(previousUser.getSurname());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
