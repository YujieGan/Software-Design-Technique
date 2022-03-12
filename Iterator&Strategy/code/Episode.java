package A2;

import java.io.File;
import java.util.*;

public class Episode implements Comparable<Episode>, Watchable {
    private final File epath;
    private final int sequential_num;
    private String TVShow_name;
    private Map<String, String> custominfo = new HashMap<>();

    /*
    Use a path and a sequential number to create an Episode object
     */
    public Episode(File path, int num) {
        if (path.exists() && !path.isFile()) {
            throw new IllegalArgumentException("The input file path is invalid");
        }
        epath = path;
        this.sequential_num = num;
    }

    public void setTVSname (String n) {
        this.TVShow_name = n;
    }
    public String getTVshowname () {
        return this.TVShow_name;
    }

    public int getNum() {
        return this.sequential_num;
    }

    public void setCust(String ekey, String evalue) {
        if (evalue == null) {
            custominfo.remove(ekey);
        }
        else {
            custominfo.put(ekey, evalue);
        }
    }

    public String getInfo(String ekey) {return custominfo.get(ekey);}

    /*
    If two episodes are from the same TVShow, they are comparable.
     */
    public int comparesTo (Episode e) {
        if (this.TVShow_name.compareTo(e.getTVshowname()) == 0) {
            if (this.sequential_num < e.getNum()) {
                return 1;
            } else {
                return -1;
            }
        }
        throw new IllegalArgumentException("not from same TV show");
    }


}
