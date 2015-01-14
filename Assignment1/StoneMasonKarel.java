/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	// You fill in this part
	
	public void run() {
		while (frontIsClear()) {
			FillOneCol();
			MoveToNextCol();
		}
		FillOneCol();
		MoveToNextCol();
	}

	private void FillOneCol() {
		turnLeft();
		while (frontIsClear()) {
			if (!beepersPresent()) {
				putBeeper();
			}
			move();
		}
		if (!beepersPresent()) {
			putBeeper();
		}
	}
	
	private void MoveToNextCol() {
		turnRight();
		if (frontIsClear()) {
			move();
			move();
			move();
			move();
		}
		turnRight();
		while (frontIsClear()) {
			move();
		}
		turnLeft();
	}
}
