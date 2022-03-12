package comp303.assignment5;

import java.io.File;
import java.util.*;

/**
 * Represents a video object that can be watched
 */
public abstract class Watchable {

	private String aTitle;
	private Language aLanguage;
	private String aStudio;
	private Map<String, String> aInfo = new HashMap<>();

	private ArrayList<WatchList> aObservers = new ArrayList<>();


	/**
	 * Creates a TVShow with required metadata about the show.
	 *
	 * @param pTitle
	 *            official title of the TVShow
	 * @param pLanguage
	 *            language of the TVShow, in full text (e.g., "English")
	 * @param pStudio
	 *            studio which originally published the movie
	 * @param pCast
	 *            standard cast of the episodes in TVShow in <CharacterName, ActorName> pairs
	 * @pre pTitle!=null && pLanguage!=null && pStudio!=null
	 */
	protected Watchable (String pTitle, Language pLanguage, String pStudio, Map<String, String> pCast) {
		assert pTitle != null && pLanguage != null && pStudio != null;
		aLanguage = pLanguage;
		aStudio = pStudio;
		aTitle = pTitle;
	}

	/**
	 * Creates an episode from the file path. This method should not be called by a client. Use
	 * TVShow.createAndAddEpisode instead.
	 *
	 * @param pTitle
	 *            the title of this episode
	 * @param pPath
	 *            the path of this episode
	 * @param pEpisodeNumber
	 *            the episode number that identifies the episode
	 * @pre pTitle!=null && pPath!=null
	 */
	protected Watchable (int pEpisodeNumber, String pTitle, File pPath) {
		assert pTitle!=null && pPath!=null;
		aTitle = pTitle;
	}

	/**
	 * Creates a movie from the file path. Callers must also provide required metadata about the movie.
	 *
	 * @param pPath
	 *            location of the movie on the file system.
	 * @param pTitle
	 *            official title of the movie in its original language
	 * @param pLanguage
	 *            language of the movie
	 * @param pStudio
	 *            studio which originally published the movie
	 * @pre pPath!=null && pTitle!=null && pLanguage!=null && pStudio!=null
	 * @throws IllegalArgumentException
	 *             if the path doesn't point to a file (e.g., it denotes a directory)
	 */
	protected Watchable(File pPath, String pTitle, Language pLanguage, String pStudio) {
		assert pPath != null && pTitle != null && pLanguage != null && pStudio != null;
		if (pPath.exists() && !pPath.isFile()) {
			throw new IllegalArgumentException("The path should point to a file.");
		}
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
	}
	
	/**
	 * Plays the video to the user
	 * @pre isValid()
	 */
	public void watch(){
		for(WatchList wl : aObservers) {
			wl.setLastWatched(this);
		}
	};

	/**
	 * Indicates whether the Watchable element is ready to be played.
	 * 
	 * @return true if the file path points to an existing location that can be read and is not a directory
	 */
	public boolean isValid(){return false;};

	public String getTitle() {
		return aTitle;
	}

	public Language getLanguage() {
		return aLanguage;
	}

	public String getStudio() {
		return aStudio;
	}

	public String setInfo(String pKey, String pValue) {
		assert pKey != null && !pKey.isBlank();
		if (pValue == null) {
			return aInfo.remove(pKey);
		}
		else {
			return aInfo.put(pKey, pValue);
		}
	}

	public boolean hasInfo(String pKey) {
		assert pKey != null && !pKey.isBlank();
		return aInfo.containsKey(pKey);
	}

	public String getInfo(String pKey) {
		assert hasInfo(pKey);
		return aInfo.get(pKey);
	}

	/**
	 * add the watchlist that contains this object
	 * @param pWL the watchlist
	 * @pre pWL != null;
	 */
	public void addWatchList (WatchList pWL)
	{
		assert pWL != null;
		aObservers.add(pWL);
	}

	/**
	 * Remove the watchlist that has removed this object
	 * @param pWL the watchlist
	 * @pre pWL != null;
	 */
	public void removeWatchList (WatchList pWL)
	{
		assert pWL != null;
		aObservers.remove(pWL);
	}
}