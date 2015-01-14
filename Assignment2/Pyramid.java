/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		/* You fill this in. */
		for (int row = BRICKS_IN_BASE; row >= 0; --row) {
			for (int col = 0; col < row; ++col) {
				double x = 0;
				if (col < row / 2) {
					x = getWidth() / 2 - (row / 2 - col) * BRICK_WIDTH;
				} else {
					x = getWidth() / 2 + (col - row / 2) * BRICK_WIDTH;
				}
				if (row % 2 == 1) {
					x -= 0.5 * BRICK_WIDTH;
				}
				double y = getHeight() - (BRICKS_IN_BASE + 1 - row) * BRICK_HEIGHT;
				drawBrick(x, y);
			}
		}
	}
	
	private void drawBrick(double x, double y) {
		GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
		add(brick);
	}
}

