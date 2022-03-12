package comp303.assignment6.robot;

public class LoggingSystem {

    private Action.Type executed;
    private int battery_charge;

    /**
     * When an action is executed, the system receives type and new battery charge
     * @param t the type of executed action
     * @param charge the updated battery charge
     */
    public void statement(Action.Type t, int charge) {
        executed = t;
        battery_charge = charge;
        System.out.println(t.toString() + "action performed, battery level" + charge);
    }

    public Action.Type getType() {
        return this.executed;
    }

    public  int getBattery_charge() {
        return  this.battery_charge;
    }
}
