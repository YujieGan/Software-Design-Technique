package A1;

import java.util.*;

public class Watchlist {
    private String name;
    private LinkedList<Movie> list = new LinkedList<Movie>();

    public Watchlist(String n) {
        this.name = n;
    }
    public void setName(String n) {
        this.name = n;
    }
    public String getName() {
        return this.name;
    }
    public void addMovie(Movie m) {
        this.list.add(m);
    }
    public void removeFirst() {
        if (this.list.isEmpty()) {
            throw new IllegalCallerException("empty watch list.");
        } else {
            this.list.removeFirst();
        }
    }
    public Movie getMovie(int index) {
        return list.get(index);
    }
    public int validmv() {
        int num = 0;
        Iterator<Movie> i = list.listIterator();
        while(i.hasNext()) {
            if (i.next().isValid()) {
                num ++;
            }
        }
        return num;
    }
    public ArrayList<String> getAllPS() {
        ArrayList<String> pslist = new ArrayList<String>();
        Iterator<Movie> i = list.listIterator();
        while(i.hasNext()) {
            String ps = i.next().getPS();
            if (! pslist.contains(ps)) {
                pslist.add(ps);
            }
        }
        return pslist;
    }
    public ArrayList<String> getAllLanguages() {
        ArrayList<String> llist = new ArrayList<String>();
        Iterator<Movie> i = list.listIterator();
        while(i.hasNext()) {
            String lan = i.next().getLanguage();
            if (! llist.contains(lan)) {
                llist.add(lan);
            }
        }
        return llist;
    }
}
