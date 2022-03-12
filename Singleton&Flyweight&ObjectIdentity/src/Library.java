package comp303.assignment3;

import java.util.*;

/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library {

	private static Library instance = new Library();
	private String aName;
	private Optional<String> anEmailID;

	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	private Set<Episode> aEpisodes = new HashSet<>();
	private Set<TVShow> aTVShows = new HashSet<>();

	private Library(){
		anEmailID = Optional.empty();
	}

	/**
	 * Initialize a libarary
	 * @pre name != null;
	 */
	public static Library getInstance(String name) {
		assert name != null;
		instance.aName = name;
		return instance;
	}

	/**
	 * Set the name of the library
	 * @pre name != null;
	 */
	public void setName (String name) {
		assert name != null;
		instance.aName = name;
	}

	public String getName () {
		return instance.aName;
	}

	public void setEmailID (String id) {
		instance.anEmailID = Optional.ofNullable(id);
	}
	/**
	 * @pre instance.anEmailID.isPresent();
	 */
	public String getEmailID () {
		assert instance.anEmailID.isPresent();
		return instance.anEmailID.get();
	}

	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 * 
	 * @param pMovie
	 *            the movie to add
	 * @pre pMovie!=null
	 */
	public void addMovie(Movie pMovie) {
		assert pMovie != null;
		aMovies.add(pMovie);
	}
	
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 * 
	 * @param pList
	 *            the watchlist to add
	 * @pre pList!=null;
	 */
	public void addWatchList(WatchList pList) {
		assert pList != null;
		aWatchLists.add(pList);
		for (Watchable movie : pList) {
			addMovie((Movie) movie);
		}
	}
	
	/**
	 * Adds a TVShow to the library. All Episodes from the list are also added as individual episodes to the library.
	 *
	 * @param pTVShow
	 *            the TVShow to add
	 * @pre pTVShow!=null;
	 */
	public void addTVShow(TVShow pTVShow) {
		assert pTVShow != null;
		aTVShows.add(pTVShow);
		for (Episode episode : pTVShow) {
			aEpisodes.add(episode);
		}
	}
	
	/**
	 * Method to generate a new watchlist based on some filtering mechanism
	 * 
	 * @param pName
	 *            the name of the watchlist to create
	 * @param pGenerationParameters
	 *            the generation parameters
	 * @pre pName!=null && pFilter!=null;
	 */
	public WatchList generateWatchList(String pName, WatchListGenerationInfo pGenerationParameters) {
		assert (pName != null) && (pGenerationParameters != null);
		List<Watchable> items = new ArrayList<>();
		for (TVShow show : aTVShows) {
			if (pGenerationParameters.filter(show)) {
				for (Episode episode : show) {
					if (pGenerationParameters.filter(episode)) {
						items.add(episode);
					}
				}
			}
		}
		for (Movie movie : aMovies) {
			if (pGenerationParameters.filter(movie)) {
				items.add(movie);
			}
		}
		Collections.sort(items, pGenerationParameters);
		WatchList watchlist = new WatchList(pName);
		for (Watchable item : items) {
			watchlist.addWatchable(item);
		}
		return watchlist;
	}
}
