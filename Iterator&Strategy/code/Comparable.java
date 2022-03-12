package A2;

/*
    Some movies and episodes are comparable.
    The episodes from the same TVShow are comparable by their sequential numbers.
    A movie and its sequels are comparable by their titles.
 */

public interface Comparable <T> {
    int comparesTo(T to_compare);
}
