package km.Projekt.controllers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import km.Projekt.dao.NoteDao;
import km.Projekt.dao.UserDao;
import km.Projekt.entity.Entity;
import km.Projekt.entity.User;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import km.Projekt.entity.UserFactory;
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

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private NoteDao noteDao;
    @GetMapping("/login")
    public String loginPage() {
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));;
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
        return "profile";
    }
    @GetMapping("/users")
    public String usersPage(Model model) {
        Iterable<User> users = userDao.findAll();
        List<Integer> numberOfNotes = new ArrayList<>();
        for (User user : users) {
            numberOfNotes.add(noteDao.findAllByUser(user).size());
        }
        model.addAttribute("usersList", userDao.findAll());
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
        return "profile";
    }
    @PostMapping("/editpassword")
    public String editPagePasswordPOST(@ModelAttribute @Validated(PasswordValidation.class) User user,
                               BindingResult bindingResult,
                               Model model,
                               Principal principal) {
        User previousUser = userDao.findByLogin(principal.getName());
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        user.setUserid(previousUser.getUserid());
        user.setLogin(previousUser.getLogin());
        user.setName(previousUser.getName());
        user.setSurname(previousUser.getSurname());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return "profile";
    }
}
