/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		fillAndCollectTheLine();
		averageTheBeepers();
		putTheAverageOfBeepersOnTheSecondLine();
		putTheAverageOfBeepersOnTheThirdLine();
		putTheBeeperOnTheAverageLocation();
	}
	
	private void fillAndCollectTheLine() {
		fillTheLine();
		collectTheBeepers();
	}
	
	private void fillTheLine() {
		while (frontIsClear()){ 
			putBeeper();
			move();
		}
		putBeeper();
	}
	
	private void collectTheBeepers() {
		turnAround();
		move();
		while (frontIsClear()) {
			if (beepersPresent()) {
				pickBeeper();
				turnAround();
				while (frontIsClear())
					move();	
			}
			if (frontIsBlocked()) {
				putBeeper();
				turnAround();
				move();
			} else {
				move();
			}
		}
		pickBeeper();
		turnAround();
		while (frontIsClear())
			move();
		if (frontIsBlocked())
			putBeeper();
	}
	
	private void averageTheBeepers() {
		turnLeft();
		while (beepersPresent()) {
			pickBeeper();
			move();
			putBeeper();
			turnAround();
			move();
			turnAround();
			if (beepersPresent()) {
				pickBeeper();
				move();
				move();
				putBeeper();
				turnAround();
				move();
				move();
				turnAround();
			}
		}
	}

	private void putTheAverageOfBeepersOnTheSecondLine() {
		move();
		turnLeft();
		while (frontIsClear()) {
			if (beepersPresent())
				pickBeeper();
			move();
		}
		if (frontIsBlocked()) {
			putBeeper();
			turnAround();
			while(frontIsClear())
				move();
			if (frontIsBlocked())
				turnAround();
		}
		
		while (beepersPresent()) {
			pickBeeper();
			move();
			while (noBeepersPresent())
				move();
			if (beepersPresent()) {
				turnAround();
				move();
				putBeeper();
				while (frontIsClear())
					move();
				if (frontIsBlocked())
					turnAround();
			}
		}
	}

	private void putTheAverageOfBeepersOnTheThirdLine() {
		turnRight();
		putTheAverageOfBeepersOnTheSecondLine();
	}

	private void putTheBeeperOnTheAverageLocation() {
		turnLeft();
		move();
		turnRight();
		while (noBeepersPresent())
			move();
		if (beepersPresent()) {
			turnRight();
			move();
		}
		if (noBeepersPresent()) {
			turnAround();
			move();
			pickBeeper();
			move();
			putBeeper();
			turnAround();
			move();
			turnLeft();
			while (frontIsClear()) {
				if (beepersPresent())
					pickBeeper();
				move();
			}
			if (frontIsBlocked()) {
				pickBeeper();
				turnRight();
				move();
				turnRight();
			}
			while (frontIsClear()) {
				if (beepersPresent())
						pickBeeper();
				move();
			}
			if (frontIsBlocked()) {
				turnRight();
				move();
				move();
				turnRight();
			}
			while (noBeepersPresent())
				move();
		} else if (beepersPresent()) {
			turnAround();
			pickBeeper();
			move();
			pickBeeper();
			move();
			putBeeper();
			turnLeft();
			move();
			putBeeper();
			turnAround();
			move();
			turnRight();
			move();
			turnLeft();
			while (frontIsClear()) {
				if (beepersPresent())
					pickBeeper();
				move();
			}
			if (frontIsBlocked()) {
				pickBeeper();
				turnRight();
				move();
				turnRight();
			}
			while (frontIsClear()) {
				if (beepersPresent())
					pickBeeper();
				move();
			}
			if (frontIsBlocked()) {
				turnRight();
				move();
				move();
				turnRight();
			}
			while (noBeepersPresent())
				move();
		}
	}
}