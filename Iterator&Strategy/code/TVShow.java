package A2;

import java.util.*;

/*
A TVShow is both watchable and bingeable.
Each TVShow has a name and a list of episodes.
 */
public class TVShow implements Bingeable<TVShow, Episode>, Watchable {
    private final List<Episode> list = new LinkedList<>();
    private String name;

    public TVShow(String n) {
        this.name = n;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void add_Episode (Episode e) {
        this.list.add(e);
    }

    public Episode get_Episode (int num) {
        for (Episode e: this.list) {
            if (e.getNum() == num) {
                return e;
            }
        }
        throw new IllegalArgumentException("invalid sequential number");
    }

    public Iterator<Episode> iterator() { return list.iterator(); }
}
