/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	private static final double RADIUS = 72.0;
	
	public void run() {
		/* You fill this in. */
		
		drawCircle(RADIUS, Color.red);
		drawCircle(RADIUS * 0.65, Color.white);
		drawCircle(RADIUS * 0.3, Color.red);
		
	}
	
	private void drawCircle(double radius, Color color) {
		
		GOval circle = new GOval(getWidth() / 2 - radius, getHeight() / 2 - radius, 2 * radius,
				2 * radius);
		circle.setColor(color);
		circle.setFilled(true);
		circle.setFillColor(color);
		add(circle);
	}
	
	
}
