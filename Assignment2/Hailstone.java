/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		
		int number = readInt("Enter a number: ");
		int cnt = 0;
		
		while (number != 1) {
			if (number % 2 == 1) {
				println(number + " is odd, so I make 3n + 1: " + (number * 3 + 1));
				number = 3 * number + 1;
			} else {
				println(number + " is even so I take half: " + number / 2);
				number /= 2;
			}
			cnt++;
		}
		
		println("The process took " + cnt + " to reach 1");
	}
}

