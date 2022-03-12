package comp303.assignment6.robot;

import java.util.*;

public class Program implements Runnable {
    private List<Action> actions;

    private LoggingSystem loggingSystem;

    public Program (List<Action> pActions) {
        actions = pActions;
    }

    /**
     * add an action at last
     * @param pAction
     * @pre pAction != null;
     */
    public void addLast(Action pAction) {
        assert pAction != null;
        actions.add(pAction);
    }

    /**
     * remove the last action
     * @return
     * @pre ! actions.isEmpty();
     */
    public Action removeLast() {
        assert ! actions.isEmpty();
        return actions.remove(actions.size()-1);
    }

    /**
     * set the logging system of the this program
     * @param pSystem
     * @pre pSystem != null;
     */
    public void setSys(LoggingSystem pSystem) {
        assert pSystem != null;
        this.loggingSystem = pSystem;
    }

    /**
     * when an action is executed, notify the logging system
     * @param t the type of executed action
     * @param charge the updated battery charge
     */
    private void notify(Action.Type t, int charge) {
        loggingSystem.statement(t, charge);
    }

    /**
     * execute the program
     */
    @Override
    public void run() {
        for (Action a : actions) {
            a.excute();
            notify(a.getType(), a.getCharge());
        }
    }

    /**
     * return all the actions that are invloved in the computation
     * @param c the algorithm to check each action
     * @return
     * @pre c != null;
     */
    public List<Action> toCompute(Computation c) {
        assert c != null;
        ArrayList<Action> list = new ArrayList<>();
        for (Action actionA : actions) {
            if (actionA.getType() == Action.Type.COMPLEX) {
                for (Action actionB : actionA.allActions) {
                    if (c.compute(actionB)) {
                        list.add(actionB);
                    }
                }
            } else {
                if (c.compute(actionA)) {
                    list.add(actionA);
                }
            }
        }
        return list;
    }
}
