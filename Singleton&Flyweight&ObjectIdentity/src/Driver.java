package comp303.assignment3;

import java.io.File;
public class Driver {
    static void main(String[] args){
        // create a lirary using getInstance()
        Library l = Library.getInstance("library1");
        // change name and emailID
        l.setName("library2");
        l.setEmailID("library.mail.com");
        // get emailID
        System.out.println(l.getEmailID());

        File mvpath1 = new File("path1");
        File mvpath2 = new File("path2");
        File mvpath3 = new File("path3");
        File tvspath = new File("path4");
        File epath = new File("path5");

        // create Movies using getMovie()
        // m1 and m2 would be the same object because they have the same title
        Movie m1 = Movie.getMovie(mvpath1, "m1" ,Language.ENGLISH, "ps1");
        Movie m2 = Movie.getMovie(mvpath2, "m1" ,Language.FRENCH, "ps2");
        boolean b1 = m1 == m2;

        Movie m3 = Movie.getMovie(mvpath3, "m3" ,Language.FRENCH, "ps3");

        // create a TVShow using get()
        TVShow tvs = TVShow.get("tvs1", Language.ENGLISH,"ps4");
        // create an episode using original constructor
        Episode e = new Episode(epath, tvs, "e1", 1);

        // create two watchlists with same watchable elements in the same order
        // b2 would return true
        WatchList w1 = new WatchList("watchlist1");
        WatchList w2 = new WatchList("watchlist2");
        w1.addWatchable(m1);
        w1.addWatchable(tvs);
        w1.addWatchable(m3);
        w1.addWatchable(e);
        w2.addWatchable(m1);
        w2.addWatchable(tvs);
        w2.addWatchable(m3);
        w2.addWatchable(e);
        boolean b2 = w1.equals(w2);
    }
}
