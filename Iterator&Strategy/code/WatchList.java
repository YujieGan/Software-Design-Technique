package A2;

import java.util.*;

/*
    Restrict the generic type to {Movies, Episodes, WatchList}
 */
public class WatchList <T extends Watchable> implements Bingeable<WatchList, T> {

    private final List<T> list = new LinkedList<>();
    private String aName;


    public WatchList(String pName) {
        aName = pName;
    }

    public String getName() {
        return aName;
    }

    public void setName(String pName) {
        aName = pName;
    }


    public void add(T in) {
        list.add(in);
    }


    public T removeNext() {
        return list.remove(0);
    }

    public List<Movie> getMovies() {
        return (List<Movie>) Collections.unmodifiableList(list);
    }

    /*
    create an iterator to access all the elements in the watchlist
     */
    public Iterator<T> iterator() { return list.iterator(); }
}
