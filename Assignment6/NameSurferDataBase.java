/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.*;
import java.util.*;
import acm.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = buffReader.readLine();
				if (line == null) break;
				NameSurferEntry nameEntry = new NameSurferEntry(line);
				namesDB.put(nameEntry.getName(), nameEntry);
			}
			buffReader.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		String otherLetters = name.substring(1);
		otherLetters = otherLetters.toLowerCase();
		name = Character.toUpperCase(name.charAt(0)) + otherLetters;
		if (namesDB.containsKey(name)) {
			return namesDB.get(name);
		} else {
			return null;
		}
	}
	
	private HashMap<String, NameSurferEntry> namesDB = new HashMap<String, NameSurferEntry>();
}

