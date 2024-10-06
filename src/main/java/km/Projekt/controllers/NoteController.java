package km.Projekt.controllers;

import jakarta.validation.Valid;
import km.Projekt.dao.NoteDao;
import km.Projekt.dao.UserDao;
import km.Projekt.entity.Note;
import km.Projekt.entity.User;
import km.Projekt.logging.*;
import km.Projekt.validation.RegistrationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class NoteController {
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private UserDao userDao;
    ShowMessage messages = new ShowMessage();
    @GetMapping("/notes")
    public String getNote(@RequestParam(required = false) Boolean my, Model model, Principal principal) {
        List<Note> foundNotes;
        if (my == null) my = true;
        if (my) foundNotes = noteDao.findAllByUser(userDao.findByLogin(principal.getName()));
        else foundNotes =  noteDao.findAllByIsPublicIsTrue();
        model.addAttribute("my", my);
        model.addAttribute("notesList", foundNotes);

        //L1 - COMPOSITE - wywołanie klasy, stworzenie listy wiadomosci od wyswietlenia
        if (foundNotes == null) {
            messages.loadMessages(
                    new ErrorLogger("error", true)
            );
        } else {
            messages.loadMessages(
                    new MessageLogger("wyświetl notatki", false)
            );
        }

        return "notes";
    }
    @GetMapping("/addnote")
    public String addNote(Model model) {
        model.addAttribute("note", new Note());


        return "addnote";
    }

    @PostMapping("/addnote")
    public String addNotePOST(@ModelAttribute @Valid Note note, BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()) {
            return "addnote";
        }
        note.setUser(userDao.findByLogin(principal.getName()));
        noteDao.save(note);

        return "redirect:/notes";
    }

    @GetMapping("/editnote/{id}")
    public String editNote(@PathVariable Integer id, Model model) {
        Optional<Note> noteToBeFound = noteDao.findById(id);
        if (!noteToBeFound.isPresent()) { return "notes"; }
        Note foundNote = noteToBeFound.get();
        model.addAttribute("note", foundNote);

        return "editnote";
    }

    @PostMapping("/editnote/{id}")
    public String editNotePOST(@ModelAttribute @Valid Note note, BindingResult bindingResult, @PathVariable Long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "editnote";
        }
        note.setUser(userDao.findByLogin(principal.getName()));
        note.setId(id);
        noteDao.save(note);

        return "redirect:/notes";
    }

    @GetMapping("/deletenote/{id}")
    public String deleteNote(@PathVariable Integer id, Principal principal) {
        Optional<Note> noteToBeFound = noteDao.findById(id);
        if (!noteToBeFound.isPresent()) { return "notes"; }
        Note foundNote = noteToBeFound.get();
        if (foundNote.getUser() !=
                userDao.findByLogin(principal.getName())) { return "notes"; }
        noteDao.deleteById(id);

        return "redirect:/notes";
    }
}
