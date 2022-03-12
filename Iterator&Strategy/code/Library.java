package A2;

import java.util.*;

public class Library {

    /*
    A library contains Movies, WatchLists, and TVShows.
     */

    private List<Movie> aMovies = new ArrayList<>();
    private List<WatchList> aWatchLists = new ArrayList<>();
    private List<TVShow> aTVSs = new ArrayList<>();

    public void addMovie(Movie pMovie) {
        aMovies.add(pMovie);
    }

    public void addWatchlist(WatchList wl) { aWatchLists.add(wl); }

    public void addTVShow(TVShow tvs) {
        aTVSs.add(tvs);
    }

    public List<Movie> getMovies() {
        return Collections.unmodifiableList(aMovies);
    }

    public List<WatchList> getLists() {
        return Collections.unmodifiableList(aWatchLists);
    }

    public List<TVShow> getTVSs() {
        return Collections.unmodifiableList(aTVSs);
    }

    /*
    Generate three types of watchlists by given feature.
     */

    public WatchList<Movie> mvList_generation (String WLname, Feature<Movie> f ) {
        WatchList w = new WatchList<Movie>(WLname);
        for (Movie m : f.toAdd(this)) {
            w.add(m);
        }
        return w;
    }
    public WatchList<Episode> epiList_generation (String WLname, Feature<Episode> f ) {
        WatchList w = new WatchList<Episode>(WLname);
        for (Episode e : f.toAdd(this)) {
            w.add(e);
        }
        return w;
    }
    public WatchList<TVShow> TVSlist_generation (String WLname, Feature<TVShow> f ) {
        WatchList w = new WatchList<TVShow>(WLname);
        for (TVShow tvs : f.toAdd(this)) {
            w.add(tvs);
        }
        return w;
    }
}