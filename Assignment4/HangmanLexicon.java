/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {
	
	public HangmanLexicon() {
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String line = buffReader.readLine();
				if (line == null) break;
				wordList.add(line);
			}
			buffReader.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	}
	
	private ArrayList<String> wordList = new ArrayList<String>();
}
