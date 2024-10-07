package km.Projekt.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import km.Projekt.dao.NoteDao;
import km.Projekt.dao.UserDao;
import km.Projekt.entity.SessionStatistics;
import km.Projekt.entity.User;
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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StatisticsController {
    SessionStatistics sessionStatistics = SessionStatistics.getInstance();
    @GetMapping("/statistics")
    public String statisticsPage(Model m) {
        sessionStatistics.incrementNumberOfLogins();
        m.addAttribute("statistics", SessionStatistics.getInstance());
        return "statistics";
    }
}
