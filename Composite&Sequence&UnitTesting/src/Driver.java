package comp303.assignment4;

import java.util.*;
import java.io.File;

public class Driver<path1, tvs1> {

    TVShow tvs1 = new TVShow("tvs1", Language.ENGLISH, "WarnerBrothers");
    TVShow tvs2 = new TVShow("tvs2", Language.FRENCH, "WarnerBrothers");
    TVShow tvs3 = new TVShow("tvs3", Language.ENGLISH, "Somethingelse");
    TVShow tvs4 = new TVShow("tvs4", Language.LATIN, "WarnerBrothers");

    Library lib = new Library();
    lib.addTVShow(tvs1);
    lib.addTVShow(tvs2);
    lib.addTVShow(tvs3);
    lib.addTVShow(tvs4);


    LanguageFilterStrategy Eng = new LanguageFilterStrategy(Language.ENGLISH);
    LanguageFilterStrategy Frn = new LanguageFilterStrategy(Language.FRENCH);
    List<WatchListFilter> langlist = Arrays.asList(Eng, Frn);
    DisconjunctionOfFilters EngOrFrn = new DisconjunctionOfFilters(langlist);
    PSFilterStrategy FilterWBStudio = new PSFilterStrategy("WarnerBrothers");
    FirstEpisodeFilter FirstEpisode = new FirstEpisodeFilter();
    List<WatchListFilter> allRequirements = Arrays.asList(EngOrFrn, FilterWBStudio, FirstEpisode);
    ConjunctionOfFilters filter0 = new ConjunctionOfFilters(allRequirements);

    WatchList watchlist = lib.generateWatchList("wl", filter0);


}
