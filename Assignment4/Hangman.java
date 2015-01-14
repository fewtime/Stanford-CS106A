/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {
		/* You fill this in */
    	initGame();
    	playGame();
	}

    private void initGame() {
    	lifeTime = 8;
    	choosenWord = pickWord();
    	hiddenWord = pickLineOfHiddenWord();
    	trackWrongCharacter = "";
    	
    	canvas.reset();
    	canvas.displayWord(hiddenWord);
    	
    	println("Welcome to Hangman!");
    	println("The word now looks like this: " + hiddenWord);
    	println("You have " + lifeTime + " guesses left.");
    }
    
    private void playGame() {
    	while (lifeTime > 0) {
    		String getChar = readLine("Your guess: ");
    		if (getChar.length() > 1) {
    			println("You can only guess one letter at a time. Try again.");
    			continue;
    		}
    		char ch = getChar.charAt(0);
    		if (Character.isLowerCase(ch)) {
    			ch = Character.toUpperCase(ch);
    		}
    		checkLetter(ch);
    		if (hiddenWord.equals(choosenWord)) {
    			println("You guessed the word: " + choosenWord);
    			println("You win.");
    			break;
    		}
    		println("The word now looks like this: " + hiddenWord);
    		println("You have " + lifeTime + " guesses left.");
    	}
    	
    	if (lifeTime == 0) {
    		println("You're completely hung.");
    		println("The word was: " + choosenWord);
    		println("You lose.");
    	}
    }
    
    private String pickWord() {
    	hangmanWordList = new HangmanLexicon();
    	int randomNum = rgen.nextInt(0, hangmanWordList.getWordCount() - 1);
    	return hangmanWordList.getWord(randomNum);
    }
    
    private String pickLineOfHiddenWord() {
    	String tmp = "";
    	for (int i = 0; i < choosenWord.length(); ++i) {
    		tmp += "-";
    	}
    	return tmp;
    }
    
    private void checkLetter(char ch) {
    	if (choosenWord.indexOf(ch) == -1) {
    		println("There are no " + ch + "'s in the word.");
    		lifeTime--;
    		trackWrongCharacter += ch;
    		canvas.noteIncorrectGuess(trackWrongCharacter);
    	} else {
    		println("That guess is correct.");
    		for (int i = 0; i < choosenWord.length(); ++i) {
    			if (choosenWord.charAt(i) == ch) {
    				if (i == 0) {
    					hiddenWord = ch + hiddenWord.substring(1);
    				} else {
        				hiddenWord = hiddenWord.substring(0, i) + ch + hiddenWord.substring(i + 1);
    				}
    			}
    		}
    		canvas.displayWord(hiddenWord);
    	}
    }
    
    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    
    private HangmanLexicon hangmanWordList;
    private HangmanCanvas canvas;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    
    private String hiddenWord;
    private String choosenWord;
    private String trackWrongCharacter;
    private int lifeTime;
}
