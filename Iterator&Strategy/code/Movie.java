package A2;

import java.io.File;
import java.util.*;

public class Movie implements Comparable <Movie>, Watchable {

    /*
    from assignment1
     */

    private final File aPath;

    private String aTitle;
    private String aLanguage;
    private String aStudio;

    private Map<String, String> aTags = new HashMap<>();

    public Movie(File pPath, String pTitle, String pLanguage, String pStudio) {
        if (pPath.exists() && !pPath.isFile()) {
            throw new IllegalArgumentException("The path should point to a file.");
        }
        aPath = pPath;
        aTitle = pTitle;
        aLanguage = pLanguage;
        aStudio = pStudio;
    }

    public boolean isValid() {
        return aPath.isFile();
    }

    public String getTitle() {
        return aTitle;
    }

    public String getLanguage() {
        return aLanguage;
    }

    public String getStudio() {
        return aStudio;
    }


    public void setTag(String pKey, String pValue) {
        if (pValue == null) {
            aTags.remove(pKey);
        }
        else {
            aTags.put(pKey, pValue);
        }
    }

    public String getTag(String pKey) {
        return aTags.get(pKey);
    }

    /*
    end of the part of assignment1
     */

    /*
    Override the comparesTo method:
    assume a movie has sequels and we can compare them by their titles.
     */
    public int comparesTo (Movie m) {
        return this.aTitle.compareTo(m.getTitle());
    }
}
