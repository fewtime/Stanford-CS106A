/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 5;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
/** Amount Y velocity is increased each cycle as a result of gravity */
	private static final double GRAVITY = 3.0;
	
/** Amount Y Velocity is incremed when it bounces */
	private static final double BOUNCE_INCREMENT = 1.01;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Animation delay or paust time between ball moves */
	private static final int DELAY = 10;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		for (int i = 0; i < NTURNS; ++i) {
			initGame();
			playGame();
			
			if (brickCounter == 0) {
				ball.setVisible(false);
				// printWinner();
				break;
			}
		}
		
		if (brickCounter > 0) {
			// printGameOver();
		}
	}
	
	private void initGame() {
		addMouseListeners();
		removePaddle();
		drawBrick(getWidth() / 2, BRICK_Y_OFFSET);
		createPaddle();
		drawBall();
	}
	
	private void playGame() {
		waitForClick();
		getBallVelocity();
		while (true) {
			moveBall();
			if (ball.getY() >= getHeight()) {
				break;
			}
			if (brickCounter == 0) {
				break;
			}
		}
	}
	
	private void drawBrick(double cx, double cy) {
		Color[] colorSet = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};
		brickCounter = NBRICKS_PER_ROW * NBRICK_ROWS;
		
		for (int row = 0; row < NBRICK_ROWS; ++row) {
			for (int col = 0; col < NBRICKS_PER_ROW; ++col) {
				double x = cx - (NBRICKS_PER_ROW * BRICK_WIDTH) / 2 - ((NBRICKS_PER_ROW - 1) * BRICK_SEP) / 2 +
					(BRICK_WIDTH + BRICK_SEP) * col;
				double y = cy + (BRICK_HEIGHT + BRICK_SEP) * row;
				
				if (getElementAt(x, y) != null) {
					continue;
				}
				
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setColor(colorSet[row / 2]);
				brick.setFilled(true);
				brick.setFillColor(colorSet[row / 2]);
				
				add(brick);
			}
		}
	}
	
	private void createPaddle() {
		double x = (getWidth() - PADDLE_WIDTH) / 2;
		double y = (getHeight() - PADDLE_HEIGHT) / 2;
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		add(paddle);
	}
	
	private void removePaddle() {
		if (paddle != null) {
			remove(paddle);
		}
	}
	
	private void drawBall() {
		double x = getWidth() / 2 - BALL_RADIUS;
		double y = getHeight() / 2 - BALL_RADIUS;
		ball = new GOval(x, y, BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		ball.setFillColor(Color.BLACK);
		add(ball);
	}
	
	public void mouseMoved(MouseEvent e) {
		if ( (e.getX() < getWidth() - PADDLE_WIDTH / 2) && (e.getX() > PADDLE_WIDTH / 2) ) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		}
	}
	
	private void getBallVelocity() {
		ballVy = GRAVITY;
		ballVx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {
			ballVx = -ballVx;
		}
	}
	
	private void moveBall() {
		ball.move(ballVx, ballVy);
		
		if ( (ball.getX() - ballVx <= 0 && ballVx < 0) ||
			 (ball.getX() + ballVx >= (getWidth() - BALL_RADIUS * 2) && ballVx > 0) ) {
			ballVx = -ballVx;
		}
		if (ball.getY() - ballVy <= 0 && ballVy < 0) {
			ballVy = -ballVy;
		}
		
		GObject collider = getCollidingObject();
		
		if (collider == paddle) {
			if (ball.getY() >= getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS * 2 &&
					ball.getY() < getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT - BALL_RADIUS + 4) {
				ballVy = -ballVy * BOUNCE_INCREMENT;
			}
		} else if (collider != null) {
			remove(collider);
			brickCounter--;
			ballVy = -ballVy;
		}
		pause(DELAY);
	}
	
	private GObject getCollidingObject() {
		if ( ( getElementAt(ball.getX(), ball.getY()) ) != null) {
			return getElementAt(ball.getX(), ball.getY());
		} else if ( ( getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS)) != null) {
			return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		} else if ( ( getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) ) != null) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		} else if ( ( getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) ) != null) {
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		} else {
			return null;
		}
	}
	
	private GRect paddle;
	private GOval ball;
	private double ballVx, ballVy;
	
	private int brickCounter = 100;
	private RandomGenerator rgen = RandomGenerator.getInstance();

}
