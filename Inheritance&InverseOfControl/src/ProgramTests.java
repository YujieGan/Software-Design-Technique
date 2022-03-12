package comp303.assignment6.robot;

import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramTests {
    WallE walle = new WallE();
    Action move1 = walle.moveAction(30.0);
    Action turnLeft = walle.turnAction(-1);
    Action turnRight = walle.turnAction(1);
    Action grab = walle.grabAction();
    Action release = walle.releaseAction();
    Action compact = walle.compactAction();
    Action empty = walle.emptyAction();


    @Test
    public void editProgramTest() {
        ArrayList<Action> list1 = new ArrayList<>();
        list1.add(grab);
        list1.add(compact);
        Program p1 = new Program(list1);
        Thread t1 = new Thread(p1);
        t1.start();
        assertEquals(walle.getBatteryCharge(), 90);
        assertEquals(walle.getCompactorLevel(), 1);
        walle.rechargeBattery();
        p1.addLast(empty);
        t1 = new Thread(p1);
        t1.start();
        assertEquals(walle.getBatteryCharge(), 85);
        assertEquals(walle.getCompactorLevel(), 0);
    }

    @Test
    public void computationTest() {
        ArrayList<Action> list1 = new ArrayList<>();
        ArrayList<Action> list2 = new ArrayList<>();
        list1.add(grab);
        list1.add(compact);
        Action complex1 = walle.complexAction(list1);
        list2.add(complex1);
        list2.add(grab);
        list2.add(compact);
        Program p2 = new Program(list2);
        Thread t2 = new Thread(p2);
        t2.start();
        assertEquals(walle.getBatteryCharge(), 80);

        // compute the total number of objects that will be compacted
        Computation c1 = new Computation() {
            @Override
            public boolean compute(Action pAction) {
                return (pAction.getType() == Action.Type.COMPACT);
            }
        };
        List<Action> result = p2.toCompute(c1);
        assertEquals(result.size(), 2);
    }

    @Test
    public void loggingSystemTest() {
        ArrayList<Action> list1 = new ArrayList<>();
        list1.add(turnLeft);
        list1.add(move1);
        Program p1 = new Program(list1);
        LoggingSystem s1 = new LoggingSystem();
        p1.setSys(s1);
        Thread t1 = new Thread(p1);
        t1.start();

        assertEquals(s1.getType(), Action.Type.MOVE);
        assertEquals(s1.getBattery_charge(), 90);
    }
}
