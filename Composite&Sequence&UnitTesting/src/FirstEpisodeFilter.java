package comp303.assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
 * Strategy to filter the first episodes of a TV show
 */

public class FirstEpisodeFilter implements WatchListFilter {

    public FirstEpisodeFilter(){}

    /**
     * Indicates whether an episode is the first episode
     *
     * @param pEpisode
     *            an Episode to potentially include in the Watchlist
     * @pre pEpisode != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filter(Episode pEpisode) {
        assert pEpisode != null;
        return pEpisode.getEpisodeNumber() == 1;
    }

    /**
     * Since the parameter is of type Movie, return false anyway
     * @param pMovie
     *            a Watchable to potentially include in the Watchlist
     * @return false
     */
    @Override
    public boolean filter(Movie pMovie) {
        return false;
    }

    /**
     * Since the parameter is of type TVShow, return false anyway
     * @param pTVShow
     *            a Watchable to potentially include in the Watchlist
     * @return false
     */
    @Override
    public boolean filter(TVShow pTVShow) {
        return false;
    }
}
