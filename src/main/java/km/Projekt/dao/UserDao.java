package km.Projekt.dao;
import km.Projekt.entity.User;
import org.springframework.data.repository.CrudRepository;
public interface UserDao extends CrudRepository<User, Integer> {
    public User findByLogin(String login);
}