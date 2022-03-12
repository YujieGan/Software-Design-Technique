package comp303.assignment4;

import java.util.*;

/*
 * Strategy to create an enhanced Watchlist filter that is the conjunction of existing filters.
 */

public class DisconjunctionOfFilters implements WatchListFilter {
    private final List<WatchListFilter> aElements;
    public DisconjunctionOfFilters(List<WatchListFilter> pFilters) {
        aElements = new ArrayList<>(pFilters);
    }
    /**
     * Indicates whether a Watchable elements should be included in the watchlist.
     *
     * @param pMovie
     *            a Watchable to potentially include in the Watchlist
     * @pre pMovie != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filter(Movie pMovie){
        assert pMovie != null;
        Boolean result = false;
        for(WatchListFilter wfilter : aElements){
            result = result || wfilter.filter(pMovie);
        }
        return result;
    };

    /**
     * Indicates whether a Watchable elements should be included in the watchlist.
     *
     * @param pTVShow
     *            a Watchable to potentially include in the Watchlist
     * @pre pTVShow != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filter(TVShow pTVShow) {
        assert pTVShow != null;
        Boolean result = false;
        for(WatchListFilter wfilter : aElements){
            result = result || wfilter.filter(pTVShow);
        }
        return result;
    }

    /**
     * Indicates whether a Watchable elements should be included in the watchlist.
     *
     * @param pEpisode
     *            a Watchable to potentially include in the Watchlist
     * @pre pEpisode != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filter(Episode pEpisode) {
        assert pEpisode != null;
        Boolean result = false;
        for(WatchListFilter wfilter : aElements){
            result = result || wfilter.filter(pEpisode);
        }
        return result;
    }
}
