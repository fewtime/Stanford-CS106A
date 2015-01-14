/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	
	public void run() {
		FillInTheOddLine();
		while (frontIsClear()) {
			if (beepersPresent()) {
				FillInTheEvenLine();
			} else {
				move();
				FillInTheOddLine();
			}
		}
	}
	
	private void FillInTheOddLine() {
		turnLeft();
		BeeperAction();
		turnAround();
		MoveAround();
		turnLeft();	
	}
	
	private void FillInTheEvenLine() {
		move();
		turnLeft();
		move();
		turnRight();
		FillInTheOddLine();
	}
	
	private void BeeperAction() {
		while (frontIsClear()) {
			putBeeper();
			move();
			if (frontIsClear()) {
				move();
			}
		}
		
		turnAround();
		
		if (frontIsClear()) {
			move();
			if (noBeepersPresent()) {
				turnAround();
				move();
				putBeeper();
			} else {
				turnAround();
				move();
			}
		}
	}
	
	private void MoveAround() {
		while (frontIsClear()) {
			move();
		}
	}

}