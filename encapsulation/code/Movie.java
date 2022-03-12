package A1;

import java.io.File;
import java.util.*;

public class Movie {
    final private String path;
    final private String format;
    final private String title;
    final private String language;
    final private String publishing_studio;
    final private ArrayList<String> allformats = new ArrayList<String> (Arrays.asList("MP4", "MOV", "WMV", "AVI", "FLV", "MKV"));
    private Map<String, String> custominfo = new HashMap<String, String>();
    private boolean validity;


    public Movie (String mvpath, String t, String l, String ps) {
        File mvfile = new File(mvpath);
        String extension;
        if (mvfile.exists()) {
            int last = mvpath.length();
            extension = (mvpath.substring(last-3, last)).toUpperCase();
            if (! allformats.contains(extension)) {
                throw new IllegalArgumentException("Wrong format");
            }
        } else {
            throw new IllegalArgumentException ("The file doesn't exist.");
        }
        this.format = extension;
        this.path = mvpath;
        this.title = t;
        this.language = l;
        this.publishing_studio = ps;
        this.validity = true;
    }

    public String getFormat() {
        return this.format;
    }
    public String getTitle() {
        return this.title;
    }
    public String getLanguage() {
        return this.language;
    }
    public String getPS() {
        return this.publishing_studio;
    }

    public void setcustom (String key, String value) {
        this.custominfo.put(key, value);
    }

    public void deletecustom (String key, String value) {
        if (this.custominfo.containsKey(key)) {
            if (this.custominfo.get(key).equals(value)) {
                this.custominfo.remove(key);
            } else {
                throw new IllegalArgumentException("no such information found.");
            }
        } else {
            throw new IllegalArgumentException("no such information found.");
        }
    }

    public boolean isValid() {
        File mvfile = new File(this.path);
        return mvfile.exists();
    }

}
