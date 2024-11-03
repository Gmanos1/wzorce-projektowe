package km.Projekt.iterators;

import km.Projekt.config.SpringContext;
import km.Projekt.dao.UserDao;
import km.Projekt.entity.User;

import java.util.ArrayList;
import java.util.List;

// L2 Iterator
public class UserNameAnotherIterator extends UserIterator{
    private int currentPosition = 0;
    private final List<User> listOfUsers = new ArrayList<>();
    private UserDao userDao;
    private final String filterName;

    public UserNameAnotherIterator(String filterName) {
        userDao = SpringContext.getApplicationContext().getBean(UserDao.class);

        this.filterName = filterName;
    }

    private void lazyLoad() {
        if (listOfUsers.isEmpty()) {
            Iterable<User> iUsers = userDao.findAll();
            for (User iuser : iUsers) {
                if (iuser.getName().equalsIgnoreCase(filterName))
                    this.listOfUsers.add(iuser);
            }
        }
    }

    @Override
    public boolean hasNext() {
        lazyLoad();
        return currentPosition < listOfUsers.size();
    }

    @Override
    public User getNext() {
        if (!hasNext()) {
            return null;
        }
        User user = listOfUsers.get(currentPosition);
        currentPosition++;
        return user;
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}
