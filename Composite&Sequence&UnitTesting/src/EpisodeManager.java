package comp303.assignment4;

import java.util.*;
import java.io.File;

/*
 * to create new episodes for a TVShow
 */
public class EpisodeManager {
    private final TVShow aTVShow;
    private Episode aPrototype;

    /**
     * Initialize the class
     * @param pTVShow
     * @pre pTVShow !=null
     */
    public EpisodeManager(TVShow pTVShow) {
        assert pTVShow != null;
        aTVShow = pTVShow;
    }

    /**
     * Set the prototype by indicating the sequential number
     * @param pSequentialNum the sequential number of the episode to clone
     */
    public void setEpisode (int pSequentialNum) {
        aPrototype = aTVShow.getEpisode(pSequentialNum).clone();
    }

    /**
     * Clone the episode
     * @param pPath the new path of the cloned episode
     * @return cloned episode
     */
    public Episode create(File pPath) {
        Episode product = aPrototype.clone(pPath);
        return product;
    }

}
