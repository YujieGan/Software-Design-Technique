package A2;

/*
For bingeable objects: WatchLists, TVShows
Each bingeable object can generate an iterator to access all the elements.
 */
public interface Bingeable<T1, T2> extends Iterable<T2> {
}
