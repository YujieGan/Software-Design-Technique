package A2;

import java.util.*;

/*
This interface is used to decide what to add to a watchlist.
The method tooAdd() returns a list of all elements that the client wants to include in a new watchlist.
 */

public interface Feature <T> {
    List<T> toAdd(Library lib);
}
