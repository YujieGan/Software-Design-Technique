package comp303.assignment5;

import java.util.*;

/**
 * interface for command design pattern
 */
public interface Command {
    Optional<Watchable> execute();
    void undoCmd();
}
