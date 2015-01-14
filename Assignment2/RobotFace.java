/* File: RobotFace.java */
/* This program draws a robot face. */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {

	private static final int HEAD_WIDTH = 100;
	private static final int HEAD_HEIGHT = 100;
	private static final int EYE_RADIUS = 10;
	private static final int MOUTH_WIDTH = 60;
	private static final int MOUTH_HEIGHT = 20;
	
	public void run() {
		drawFace(getWidth() / 2, getHeight() / 2);
	}
	
	private void drawFace(double cx, double cy) {
		drawHead(cx, cy);
		drawEye(cx - HEAD_WIDTH / 4, cy - HEAD_HEIGHT / 4);
		drawEye(cx + HEAD_WIDTH / 4, cy - HEAD_HEIGHT / 4);
		drawMouse(cx, cy + HEAD_HEIGHT / 4);
	}
	
	private void drawHead(double cx, double cy) {
		double x = cx - HEAD_WIDTH / 2;
		double y = cy - HEAD_HEIGHT / 2;
		GRect baseFace = new GRect(x, y, HEAD_WIDTH, HEAD_HEIGHT);
		baseFace.setColor(Color.black);
		baseFace.setFilled(true);
		baseFace.setFillColor(Color.gray);
		add(baseFace);
	}
	
	private void drawEye(double cx, double cy) {
		double x = cx - EYE_RADIUS;
		double y = cy - EYE_RADIUS;
		GOval eye = new GOval(x, y, 2 * EYE_RADIUS, 2 * EYE_RADIUS);
		eye.setColor(Color.yellow);
		eye.setFilled(true);
		eye.setFillColor(Color.yellow);
		add(eye);
	}
	
	private void drawMouse(double cx, double cy) {
		double x = cx - MOUTH_WIDTH / 2;
		double y = cy - MOUTH_HEIGHT / 2;
		GRect mouse = new GRect(x, y, MOUTH_WIDTH, MOUTH_HEIGHT);
		mouse.setColor(Color.white);
		mouse.setFilled(true);
		mouse.setColor(Color.white);
		add(mouse);
	}
}
