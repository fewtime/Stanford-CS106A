/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		graph = new NameSurferGraph();
		nameLabel = new JLabel("Name ");
		nameField = new JTextField(20);
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		
		nameField.addActionListener(this);
		
		add(graph);
		add(nameLabel , SOUTH);
		add(nameField, SOUTH);
		add(graphButton, SOUTH);
		add(clearButton, SOUTH);
		
		addActionListeners();
		
		db = new NameSurferDataBase(NAMES_DATA_FILE);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
		} else {
			NameSurferEntry rankings = db.findEntry(nameField.getText());
			if (rankings != null) {
				graph.addEntry(rankings);
			}
		}
		graph.update();
	}
	
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton graphButton;
	private JButton clearButton;
	
	private NameSurferDataBase db;
	private NameSurferGraph graph;
}
