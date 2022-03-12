package comp303.assignment5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Driver {
    static void main(String[] args){
        Movie mv1;
        Movie mv2;
        TVShow tvs1;

        WatchList wl = new WatchList("watchlist");
        wl.addWatchable(mv1);
        wl.addWatchable(tvs1);

        mv1.watch();
        // watched == tvs1
        Watchable watched = wl.lastWatched();

        wl.setName("name2");
        wl.addWatchable(mv2);
        wl.undo();
        wl.undo();
        wl.redo();
        String curname = wl.getName();
        // curname = "name2"
        // mv2 is not in the list
    }
}
