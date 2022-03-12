package comp303.assignment5;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an element that can have a prequel or sequel.
 */
public abstract class Sequenceable extends Watchable {

	private String aTitle;
	private Language aLanguage;
	private String aStudio;
	private Map<String, String> aInfo;

	private final File aPath;

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
	 */
	protected Sequenceable (int pEpisodeNumber, String pTitle, File pPath) {
		super(pEpisodeNumber, pTitle, pPath);
		aPath = pPath;
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
	protected Sequenceable (File pPath, String pTitle, Language pLanguage, String pStudio) {
		super(pPath, pTitle, pLanguage, pStudio);
		aPath = pPath;
	}

	
	/**
	 * Indicates if the element is preceded by another element (e.g., a previous episode or a prequel).
	 * 
	 * @return true if there is a previous element
	 */
	public boolean hasPrevious(){return false;};
	
	/**
	 * Indicates if the element is followed by another element (e.g., another episode or a sequel).
	 * 
	 * @return true if there is a following element
	 */
	public boolean hasNext(){return false;};
	
	/**
	 * Retrieves the previous element.
	 * 
	 * @return a reference to the previous element.
	 * @pre hasPrevious()
	 */
	public Sequenceable getPrevious(){return this;};
	
	/**
	 * Retrieves the next element.
	 * 
	 * @return a reference to the next element.
	 * @pre hasNext();
	 */
	public Sequenceable getNext(){return this;};
	
}
