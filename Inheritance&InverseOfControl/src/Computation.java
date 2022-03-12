package comp303.assignment6.robot;

public interface Computation {
    /**
     * the custom define algorithm here to check if the action is involved in the computation
     * @param pAction the action to be checked
     * @return
     */
    public boolean compute(Action pAction);
}
