package km.Projekt.iterators;

import km.Projekt.entity.User;

// L2 Iterator
public abstract class UserIterator {
    abstract public boolean hasNext();

    abstract public User getNext();

    abstract public void reset();
}
