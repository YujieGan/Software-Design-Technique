package comp303.assignment6.robot;

import java.util.*;

public class WallE implements Robot {
	
	private final Random random = new Random();
	private int charge = 100;
	private GripperState gripperState = GripperState.EMPTY;
	private ArmState armState = ArmState.RETRACTED;
	private int compactedItems = 0;
	
	@Override
	public void turnRobot(double pDegrees) {
		assert armState == ArmState.RETRACTED;
		System.out.println("Turn");
	}
	
	@Override
	public void moveRobot(double pDistance) {
		assert armState == ArmState.RETRACTED;
		System.out.println("Forward");
	}
	
	@Override
	public void openGripper() {
		assert gripperState != GripperState.OPEN && armState == ArmState.RETRACTED;
		System.out.println("Open gripper");
		gripperState = GripperState.OPEN;
	}
	
	@Override
	public void closeGripper() {
		assert gripperState == GripperState.OPEN;
		System.out.println("Close gripper");
		switch (armState) {
			case EXTENDED:
				gripperState = GripperState.HOLDING_OBJECT;
				break;
			case RETRACTED:
				gripperState = GripperState.EMPTY;
				break;
			default:
				assert false;
				break;
		}
	}
	
	@Override
	public GripperState getGripperState() {
		return gripperState;
	}
	
	@Override
	public void extendArm() {
		assert armState == ArmState.RETRACTED;
		System.out.println("Extend arm");
		armState = ArmState.EXTENDED;
	}
	
	@Override
	public void retractArm() {
		assert armState == ArmState.EXTENDED;
		System.out.println("Retract arm");
		armState = ArmState.RETRACTED;
	}
	
	@Override
	public ArmState getArmState() {
		return armState;
	}
	
	@Override
	public void compact() {
		assert compactedItems < 10 && gripperState == GripperState.HOLDING_OBJECT;
		System.out.println("Compact");
		compactedItems++;
		gripperState = GripperState.OPEN;
	}
	
	@Override
	public void emptyCompactor() {
		assert compactedItems > 0;
		System.out.println("Empty compactor");
		compactedItems = 0;
	}
	
	@Override
	public int getCompactorLevel() {
		return compactedItems;
	}
	
	@Override
	public void rechargeBattery() {
		System.out.println("Recharge");
		charge = 100;
	}
	
	@Override
	public void updateBatteryLevel() {
		charge -= random.nextInt(5) + 1;
	}
	
	@Override
	public int getBatteryCharge() {
		return charge;
	}


	/**
	 * create a move action
	 * @param pDistance distance to move
	 * @return the action
	 */
	public Action moveAction(double pDistance){
		return new Action(this){
			private final double aDistance = pDistance;
			private Type type = Type.MOVE;
			@Override
			protected void anAction(){
				moveRobot(pDistance);
			}
			public double getDistance(){
				return aDistance;
			}
		};
	}

	/**
	 * create a turn action
	 * @param direction  if direction < 0, turn anticlockwise, otherwise turn clockwise
	 * @return
	 */
	public Action turnAction(int direction){
		return new Action(this){
			private Type type = Type.TURN;
			@Override
			protected void anAction(){
				if (direction < 0){
					turnRobot(-90);
				} else {
					turnRobot(90);
				}
			}
		};
	}

	/**
	 * create a grab action
	 * @return
	 */
	public Action grabAction() {
		return new Action(this){
			private Type type = Type.GRAB;
			@Override
			protected void anAction(){
				extendArm();
				closeGripper();
				retractArm();
			}
		};
	}

	/**
	 * create an action to release an object
	 * @return
	 */
	public Action releaseAction() {
		return new Action(this){
			private Type type = Type.RELEASE;
			@Override
			protected void anAction(){
				openGripper();
			}
		};
	}

	/**
	 * create a compact action
	 * @return
	 */
	public Action compactAction() {
		return new Action(this){
			private Type type = Type.COMPACT;
			@Override
			protected void anAction(){
				compact();
			}
		};
	}

	/**
	 * create an action to empty the compactor
	 * @return
	 */
	public Action emptyAction() {
		return new Action(this){
			private Type type = Type.EMPTY;
			@Override
			protected void anAction(){
				emptyCompactor();
			}
		};
	}

	/**
	 * create a complex action
	 * @param pActions the list of a sequence of actions
	 * @return
	 */
	public Action complexAction(List<Action> pActions) {
		return new Action(this) {
			private Type type = Type.COMPLEX;
			public final List<Action> allActions = pActions;
			@Override
			protected void anAction() {
				for(Action a: pActions) {
					a.excute();
				}
			}
		};
	}


}
