package A1;

import java.util.*;

public class Libarary {

    private ArrayList<Movie> allmovies = new ArrayList<Movie>();
    private ArrayList<Watchlist> allwls = new ArrayList<Watchlist>();

    public void addMovie(Movie m) {
        this.allmovies.add(m);
    }

    public void addWatchlist(Watchlist w) {
        this.allwls.add(w);
    }
}
