/*
 * File: Fibonacci.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This program lists the terms in the Fibonacci sequence up to
 * a constant MAX_TERM_VALUE, which is the largest Fibonacci term
 * the program will display.
 */

import acm.program.*;

public class Fibonacci extends ConsoleProgram {
	
	public void run() {
		println("This program lists the Fibonacci sequence.");
		int t1 = 0;
		int t2 = 1;
		int t3 = t1 + t2;
		while (t1 <= MAX) {
			println(t1);
			t3 = t1 + t2;
			t1 = t2;
			t2 = t3;
		}
	}
	
	private static final int MAX = 10000;
}
