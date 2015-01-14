/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final double RECT_WIDTH = 150;
	private static final double RECT_HEIGHT = 60;
	
	public void run() {
		/* You fill this in. */
		drawUpperBox();
		drawLeftBox();
		drawMiddleBox();
		drawRightBox();
		drawAllLine();
	}
	
	private void drawUpperBox() {
		double x = getWidth() / 2 - RECT_WIDTH / 2;
		double y = getHeight() / 2 - RECT_HEIGHT;
		drawRect(x, y);
		drawText("Program", x, y);
	}
	
	private void drawLeftBox() {
		double x = getWidth() / 2 - RECT_WIDTH * 2;
		double y = getHeight() / 2 + RECT_HEIGHT;
		drawRect(x, y);
		drawText("GraphicsProgram", x, y);
	}
	
	private void drawMiddleBox() {
		double x = getWidth() / 2 - RECT_WIDTH / 2;
		double y = getHeight() / 2 + RECT_HEIGHT;
		drawRect(x, y);
		drawText("ConsoleProgram", x, y);
	}
	
	private void drawRightBox() {
		double x = getWidth () / 2 + RECT_WIDTH;
		double y = getHeight() / 2 + RECT_HEIGHT;
		drawRect(x, y);
		drawText("DialogProgram", x, y);
	}
	
	private void drawAllLine() {
		// Middle Line
		drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2 + RECT_HEIGHT);
		// Left Line
		drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2 - RECT_WIDTH * 1.5, getHeight() / 2 + RECT_HEIGHT);
		// Right Line
		drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2 + RECT_WIDTH * 1.5, getHeight() / 2 + RECT_HEIGHT);
	}
	
	private void drawRect(double x, double y) {
		GRect rect = new GRect(x, y, RECT_WIDTH, RECT_HEIGHT);
		add(rect);
	}
	
	private void drawText(String str, double x, double y) {
		GLabel text = new GLabel(str, x, y);
		text.move(RECT_WIDTH / 2 - text.getWidth() / 2, RECT_HEIGHT / 2 + text.getHeight() / 2);
		add(text);
	}
	
	private void drawLine(double x1, double y1, double x2, double y2) {
		GLine line = new GLine(x1, y1, x2, y2);
		add(line);
	}
	
}

