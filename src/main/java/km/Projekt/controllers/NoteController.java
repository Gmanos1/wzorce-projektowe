package km.Projekt.controllers;

import jakarta.validation.Valid;
import km.Projekt.dao.NoteDao;
import km.Projekt.dao.UserDao;
import km.Projekt.entity.Note;
import km.Projekt.entity.NoteManagerFacade;
import km.Projekt.entity.memento.NoteCaretaker;
import km.Projekt.entity.memento.NoteMemento;
import km.Projekt.entity.statistics.SessionStatistics;
import km.Projekt.logging.*;
import km.Projekt.notesView.NoteStyle;
import km.Projekt.notesView.NoteStyleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    SessionStatistics sessionStatistics = SessionStatistics.getInstance();
    @GetMapping("/notes")
    public String getNote(@RequestParam(required = false) Boolean my, Model model, Principal principal) {
        sessionStatistics.incrementNumberOfViewedNotes();

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
        sessionStatistics.incrementNumberOfAddedNotes();

        if (bindingResult.hasErrors()) {
            return "addnote";
        }
        note.setUser(userDao.findByLogin(principal.getName()));
        noteDao.save(note);

        NoteManagerFacade noteManagerFacade = new NoteManagerFacade(); //tworzenie fasady

        String[] tags = {"newNote"};
        noteManagerFacade.addNoteWithFeatures(note, principal, tags); //dodanie notatki z tagami, powiadomieniem i kopią zapasowa

        NoteStyle newNote = NoteStyleFactory.getNoteStyle("Emergency", true); //tworzenie obiektów notatek FLYWEIGHT
        NoteStyle newNote2 = NoteStyleFactory.getNoteStyle("Standard", false);
        NoteStyle newNote3 = NoteStyleFactory.getNoteStyle("Emergency", true); //współdzielenie stylu z note1

        Note note1 = new Note(newNote);
        Note note2 = new Note(newNote2);
        Note note3 = new Note(newNote3);

        note1.displayNote();
        note2.displayNote();
        note3.displayNote(); //wyświetlenie styli wraz z notatkami // FLYWEIGHT

        return "redirect:/notes";
    }

    @GetMapping("/editnote/{id}")
    public String editNote(@PathVariable Integer id, Model model) {
        Optional<Note> noteToBeFound = noteDao.findById(id);
        if (!noteToBeFound.isPresent()) { return "notes"; }
        Note foundNote = noteToBeFound.get();
        model.addAttribute("note", foundNote);

        // L2 - MEMENTO - zapis stanu przed edycją
        Note newNote = noteDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        noteCaretaker.saveState(newNote.saveToMemento());

        model.addAttribute("note", newNote);

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

        sessionStatistics.incrementNumberOfDeletesNotes();
        return "redirect:/notes";
    }


    // L2 - MEMENTO:
    private NoteCaretaker noteCaretaker = new NoteCaretaker(); //obiekt zarządzający notatkami

    @PostMapping("/notes/memento/undo/{id}") //przywraca ostatni stan notatki
    public String undoEdit(@PathVariable("id") Integer id, Model model) {
        Note note = noteDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid note ID"));

        // Restore the last saved state if available
        NoteMemento lastState = noteCaretaker.restoreState();
        if (lastState != null) {
            System.out.println("LAST STATE: " + lastState);
            note.restoreFromMemento(lastState);
            noteDao.save(note);
        }

        model.addAttribute("note", note);
        return "redirect:/editnote/" + id;
    }
}
