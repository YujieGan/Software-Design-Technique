package comp303.assignment5;

import java.util.*;

/**
 * Represents a sequence of watchables to watch in FIFO order.
 */
public class WatchList implements Bingeable<Watchable> {
	
	private final List<Watchable> aList = new LinkedList<>();
	private String aName;
	private int aNext;

	private Optional<Watchable> aLastWatched;

	// check if the last operation is undo();
	private boolean lastIsUndo = false;
	// store all the executed commands
	private ArrayList<Command> aCommands = new ArrayList<>();
	// store the commands redone
	private ArrayList<Command> aUndos = new ArrayList<>();
	
	/**
	 * Creates a new empty watchlist.
	 * 
	 * @param pName
	 *            the name of the list
	 * @pre pName!=null;
	 */
	public WatchList(String pName) {
		assert pName != null;
		aName = pName;
		aNext = 0;
		aLastWatched = Optional.empty();
	}
	
	public String getName() {
		return aName;
	}
	
	/**
	 * Changes the name of this watchlist.
	 * 
	 * @param pName
	 *            the new name
	 * @pre pName!=null;
	 */
	public void setName(String pName) {
		assert pName != null;
		Command cmd = setNameCmd(pName);
		aCommands.add(cmd);
		lastIsUndo = false;
		cmd.execute();
	}
	
	/**
	 * Adds a watchable at the end of this watchlist.
	 * 
	 * @param pWatchable
	 *            the watchable to add
	 * @pre pWatchable!=null;
	 */
	public void addWatchable(Watchable pWatchable) {
		assert pWatchable != null;
		Command cmd = addWatchableCmd(pWatchable);
		aCommands.add(cmd);
		lastIsUndo = false;
		cmd.execute();
	}
	
	/**
	 * Retrieves and removes the Watchable at the specified index.
	 * 
	 * @param pIndex
	 *            the position of the Watchable to remove
	 * @pre pIndex < getTotalCount() && pIndex >= 0
	 */
	public Watchable removeWatchable(int pIndex) {
		assert pIndex < aList.size() && pIndex >= 0;
		Command cmd = removeWatchableCmd(pIndex);
		aCommands.add(cmd);
		lastIsUndo = false;
		Watchable removed = cmd.execute().get();
		return removed;
	}
	
	/**
	 * @return the total number of valid watchable elements
	 */
	public int getValidCount() {
		int count = 0;
		for (Watchable item : aList) {
			if (item.isValid()) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public int getTotalCount() {
		return aList.size();
	}
	
	@Override
	public int getRemainingCount() {
		return aList.size() - aNext;
	}
	
	@Override
	public Watchable next() {
		assert getRemainingCount() > 0;
		Command cmd = nextCmd();
		aCommands.add(cmd);
		lastIsUndo = false;
		Watchable next = cmd.execute().get();
		return next;
	}
	
	@Override
	public void reset() {
		Command cmd = resetCmd();
		aCommands.add(cmd);
		lastIsUndo = false;
		cmd.execute();
	}
	
	@Override
	public Iterator<Watchable> iterator() {
		return Collections.unmodifiableList(aList).iterator();
	}

	/**
	 * set the last watched object
	 * @param pWatched last watched object
	 */
	public void setLastWatched(Watchable pWatched) {
		aLastWatched = Optional.ofNullable(pWatched);
	}

	/**
	 * @return the last watched object of the watchlist
	 * @pre aLastWatched.isPresent();
	 */
	public Watchable lastWatched(){
		assert aLastWatched.isPresent();
		return aLastWatched.get();
	}

	/**
	 * create a command object for setName()
	 * @param pName
	 * @return
	 */
	private Command setNameCmd(String pName){
		return new Command() {
			private final String preName = aName;;
			private final String curName = pName;
			@Override
			public Optional<Watchable> execute() {
				aName = curName;
				return Optional.empty();
			}

			@Override
			public void undoCmd() {
				aName = preName;
			}

		};
	}

	/**
	 * create a command object for addWatchable()
	 * @param pWatchable
	 * @return
	 */
	private Command addWatchableCmd(Watchable pWatchable) {
		return new Command() {
			@Override
			public Optional<Watchable> execute() {
				aList.add(pWatchable);
				pWatchable.addWatchList(WatchList.this);
				return Optional.empty();
			}

			@Override
			public void undoCmd() {
				aList.remove(pWatchable);
				pWatchable.removeWatchList(WatchList.this);
			}

		};
	}

	/**
	 * create a command object for removeWatchable()
	 * @param pIndex
	 * @return
	 */
	private Command removeWatchableCmd(int pIndex){
		return new Command() {
			private final Watchable toRemove = aList.get(pIndex);
			@Override
			public Optional<Watchable> execute() {
				if (aNext > pIndex) {
					aNext--;
				}
				toRemove.removeWatchList(WatchList.this);
				return Optional.of(aList.remove(pIndex));
			}

			@Override
			public void undoCmd() {
				if (aNext > pIndex) {
					aNext++;
				}
				toRemove.addWatchList(WatchList.this);
				aList.set(pIndex, toRemove);
			}

		};
	}

	/**
	 * create a command object for next()
	 * @return
	 */
	private Command nextCmd(){
		return new Command() {
			@Override
			public Optional<Watchable> execute() {
				Watchable next = aList.get(aNext);
				aNext++;
				if (aNext >= aList.size()) {
					aNext = 0;
				}
				return Optional.of(next);
			}

			@Override
			public void undoCmd() {
				if (aNext == 0) {
					aNext = aList.size();
				} else {
					aNext --;
				}
			}
		};
	}

	/**
	 * create a command object for reset()
	 * @return
	 */
	private Command resetCmd(){
		return new Command() {
			private final int preNext = aNext;
			@Override
			public Optional<Watchable> execute() {
				aNext = 0;
				return Optional.empty();
			}

			@Override
			public void undoCmd() {
				aNext = preNext;
			}
		};
	}

	public void undo(){
		int index = aCommands.size()-1;
		if (index != -1) {
			Command cmd = aCommands.get(index);
			if(!lastIsUndo) {
				aUndos.clear();
			}
			aUndos.add(cmd);
			lastIsUndo = true;
			cmd.undoCmd();
		}
	}

	public void redo(){
		if (!lastIsUndo){
			Command cmd = aCommands.get(aCommands.size()-1);
			aCommands.add(cmd);
			cmd.execute();
		} else {
			int index = aUndos.size()-1;
			if (index != -1) {
				Command cmd = aUndos.get(index);
				aUndos.remove(index);
				cmd.undoCmd();
			}
		}
	}
}
