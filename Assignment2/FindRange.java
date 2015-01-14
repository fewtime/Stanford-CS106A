/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		
		println("This program finds the largest and smallest numbers.");
		
		int input = 0xffffffff;
		int smallest = 0xffffffff;
		int largest = 0;
		
		while (input != 0) {
			input = readInt("? ");
			
			if (smallest > input) {
				smallest = input;
			}
			if (largest < input) {
				largest = input;
			}
		}
		
		println("smallest: " + smallest);
		println("largest: " + largest);
		
	}
}

