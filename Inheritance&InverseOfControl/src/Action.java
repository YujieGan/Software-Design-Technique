package comp303.assignment6.robot;

import java.util.*;

public abstract class Action {
    private Type type;
    private Robot aRobot;
    public List<Action> allActions;
    private boolean recharge = false;

    public Action(Robot pRobot) {
        aRobot = pRobot;
    }

    public static enum Type {
        MOVE,
        TURN,
        GRAB,
        RELEASE,
        COMPACT,
        EMPTY,
        COMPLEX
    }

    public Type getType() {
        return this.type;
    }

    public int getCharge() {
        return aRobot.getBatteryCharge();
    }

    /**
     * Different types of actions define their own algorithm here
     */
    protected abstract void anAction();

    /**
     * set the action to be forced to recharge before execution
     */
    public void forceRecharge() {
        recharge = true;
    }

    /**
     * execute the action
     * check for the battery charge first
     * after the execution, update the charge
     * if the action is set to be forced to recharge, recharge anyway
     */
    public void excute(){
        if(type == Type.COMPLEX){
            anAction();
        } else {
            if (recharge){
                aRobot.rechargeBattery();
            } else {
                int charge = aRobot.getBatteryCharge();
                if (charge <= 5) {
                    aRobot.rechargeBattery();
                }
            }
            anAction();
            aRobot.updateBatteryLevel();
        }
    }

}


