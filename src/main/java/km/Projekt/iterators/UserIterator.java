package km.Projekt.iterators;

import km.Projekt.entity.User;

// L2 Iterator
public interface UserIterator {
    boolean hasNext();

    User getNext();

    void reset();
}
