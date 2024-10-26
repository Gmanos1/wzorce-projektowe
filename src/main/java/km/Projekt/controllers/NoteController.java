package km.Projekt.controllers;

import jakarta.validation.Valid;
import km.Projekt.dao.NoteDao;
import km.Projekt.dao.UserDao;
import km.Projekt.entity.Note;
import km.Projekt.entity.NoteManagerFacade;
import km.Projekt.entity.mediator.NoteMediator;
import km.Projekt.entity.memento.NoteCaretaker;
import km.Projekt.entity.memento.NoteMemento;
import km.Projekt.entity.observer.LoggerObserver;
import km.Projekt.entity.observer.Notifier;
import km.Projekt.entity.statistics.SessionStatistics;
import km.Projekt.logging.ErrorLogger;
import km.Projekt.logging.MessageLogger;
import km.Projekt.logging.ShowMessage;
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
    private final NoteCaretaker noteCaretaker = new NoteCaretaker();

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

        //L2 - OBSERVER - dodanie obserwatorów i powiadomienie ich, jeżeli notatka jest publicza i została utworzona
        if (note.isPublic) {
        //L2 - MEDIATOR - zmiana nie tworzymy loggera i notifiera, tylko tworzymy dla nich mediatora
        LoggerObserver logger = new LoggerObserver();
        Notifier notifier = new Notifier();

        note.addObserver(logger);
        note.addObserver(notifier);

        NoteMediator mediator = new NoteMediator(logger, notifier);
        Note publicNote = new Note(mediator);

        publicNote.setText(note.getText());
        publicNote.setIsPublic(note.isPublic);

        if (publicNote.isPublic) {
            publicNote.createNoteText(publicNote.getText());
        }
        }

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

        noteCaretaker.saveMemento(foundNote.saveToMemento());
        System.out.println("Saved: " + foundNote);

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

        sessionStatistics.incrementNumberOfDeletesNotes();
        return "redirect:/notes";
    }

    // L2 - MEMENTO
    @PostMapping("/notes/memento/undo/{id}")
    @ResponseBody
    public String undoEdit(@PathVariable("id") Integer id) {
        Note note = noteDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));

        NoteMemento previousState = noteCaretaker.restoreMemento();
        if (previousState != null) {
            note.restoreFromMemento(previousState);
            noteDao.save(note);
        }

        return note.getText();
    }
}

