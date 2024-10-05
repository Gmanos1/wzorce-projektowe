package km.Projekt.dao;

import km.Projekt.entity.Note;
import km.Projekt.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteDao extends CrudRepository<Note, Integer> {
    public List<Note> findAllByUser(User user);
    public List<Note> findAllByIsPublicIsTrue();
    public void deleteAllByUser(User user);
}
