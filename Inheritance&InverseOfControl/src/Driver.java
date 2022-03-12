package comp303.assignment6.robot;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {

        WallE walle = new WallE();

        // create basic actions;
        Action move = walle.moveAction(30.0);
        Action turnLeft = walle.turnAction(-1);
        Action turnRight = walle.turnAction(1);
        Action grab = walle.grabAction();
        Action release = walle.releaseAction();
        Action compact = walle.compactAction();
        Action empty = walle.emptyAction();

        // create a complex action: move backwards
        ArrayList<Action> list1 = new ArrayList<>();
        list1.add(turnLeft);
        list1.add(turnLeft);
        list1.add(move);
        Action complex1 = walle.complexAction(list1);

        // create a program
        ArrayList<Action> list2 = new ArrayList<>();
        list2.add(complex1);
        list2.add(grab);
        list2.add(compact);
        Program p1 = new Program(list2);

        p1.removeLast();
        p1.addLast(release);

        // compute the total number of objects that will be compacted
        Computation c1 = new Computation() {
            @Override
            public boolean compute(Action pAction) {
                return (pAction.getType() == Action.Type.COMPACT);
            }
        };
        p1.addLast(grab);
        p1.addLast(compact);
        p1.addLast(grab);
        p1.addLast(compact);
        List<Action> result = p1.toCompute(c1);
        int num_of_objects = result.size();

        // logging system
        LoggingSystem s1 = new LoggingSystem();
        p1.setSys(s1);
        Thread t1 = new Thread(p1);
        t1.start();
    }
}
