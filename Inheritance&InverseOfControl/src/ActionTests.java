package comp303.assignment6.robot;

import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActionTests {
    WallE walle = new WallE();
    Action move = walle.moveAction(30.0);
    Action turnLeft = walle.turnAction(-1);
    Action turnRight = walle.turnAction(1);
    Action grab = walle.grabAction();
    Action release = walle.releaseAction();
    Action compact = walle.compactAction();
    Action empty = walle.emptyAction();

    @Test
    void basicActionsTest () {
        grab.excute();
        assertEquals(walle.getArmState(), Robot.ArmState.RETRACTED);
        assertEquals(walle.getGripperState(), Robot.GripperState.HOLDING_OBJECT);
        release.excute();
        assertEquals(walle.getGripperState(), Robot.GripperState.OPEN);
        grab.excute();
        compact.excute();
        assertEquals(walle.getCompactorLevel(), 1);
        empty.excute();
        assertEquals(walle.getCompactorLevel(), 0);
        assertEquals(walle.getBatteryCharge(), 75);

        turnLeft.forceRecharge();
        turnLeft.excute();
        assertEquals(walle.getBatteryCharge(), 95);
    }

    @Test
    public void ComplexActionsTest() {
        ArrayList<Action> list1 = new ArrayList<>();
        ArrayList<Action> list2 = new ArrayList<>();
        list1.add(grab);
        list1.add(compact);
        list2.add(grab);
        list2.add(compact);
        list2.add(empty);
        Action complex1 = walle.complexAction(list1);
        Action complex2 = walle.complexAction(list2);

        complex2.excute();
        assertEquals(walle.getCompactorLevel(), 0);
        assertEquals(walle.getBatteryCharge(), 85);

        walle.rechargeBattery();

        complex1.excute();
        assertEquals(walle.getCompactorLevel(), 1);
        assertEquals(walle.getBatteryCharge(), 90);
    }


}
