/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		canvas = new FacePamphletCanvas();
		nameField = new JTextField(TEXT_FIELD_SIZE);
		statusField = new JTextField(TEXT_FIELD_SIZE);
		picField = new JTextField(TEXT_FIELD_SIZE);
		friendField = new JTextField(TEXT_FIELD_SIZE);

		add(canvas);
		
		add(new JLabel("Name "), NORTH);
		add(nameField, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		add(statusField, WEST);
		add(new JButton("Change Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(picField, WEST);
		add(new JButton("Change Picture"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friendField, WEST);
		add(new JButton("Add Friend"), WEST);
		
		addActionListeners();
		statusField.addActionListener(this);
		picField.addActionListener(this);
		friendField.addActionListener(this);
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	String givenName = nameField.getText();
    	if (e.getActionCommand().equals("Add") && !nameField.getText().equals("")) {
    		canvas.removeAll();
    		if (db.containsProfile(givenName) == true) {
    			canvas.displayProfile(db.getProfile(givenName));
    			canvas.showMessage("A profile with name " + givenName + " already exists.");
    			currentProfile = db.getProfile(givenName);
    		} else {
    			FacePamphletProfile newProfile = new FacePamphletProfile(givenName);
    			db.addProfile(newProfile);
    			canvas.displayProfile(newProfile);
    			canvas.showMessage("New profile created");
    			currentProfile = newProfile;
    		}
    	} else if (e.getActionCommand().equals("Delete") && !nameField.getText().equals("")) {
    		canvas.removeAll();
    		currentProfile = null;
    		if (db.containsProfile(givenName) == true) {
    			db.deleteProfile(givenName);
    			canvas.showMessage("Profile of " + givenName + " deleted");
    		} else {
    			canvas.showMessage("A profile with name " + givenName + " does not exist.");
    		}
    	} else if (e.getActionCommand().equals("Lookup") && !nameField.getText().equals("")) {
    		canvas.removeAll();
    		if (db.containsProfile(givenName) == true) {
    			canvas.displayProfile(db.getProfile(givenName));
    			canvas.showMessage("Displaying " + givenName);
    			currentProfile = db.getProfile(givenName);
    		} else {
    			canvas.showMessage("A profile with name " + givenName + " does not exist.");
    			currentProfile = null;
    		}
    	} else if (e.getActionCommand().equals("Change Status") || e.getSource() == statusField &&
    			!statusField.getText().equals("")) {
    		String statusStr = statusField.getText();
    		if (currentProfile != null) {
    			currentProfile.setStatus(currentProfile.getName() + " is " + statusStr);
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Status updated to " + statusStr);
    		} else {
    			canvas.showMessage("Please select a profile to change status");
    		}
    	} else if (e.getActionCommand().equals("Change Picture") || e.getSource() == picField &&
    			!picField.getText().equals("")) {
    		String imgFileName = picField.getText();
    		if (currentProfile != null) {
    			GImage img = null;
    			try {
    				img = new GImage(imgFileName);
    				currentProfile.setImage(img);
    			} catch (ErrorException ex) {
    				img = null;
    			}
    			canvas.displayProfile(currentProfile);
    			if (img == null) {
    				canvas.showMessage("Unable to open image file: " + imgFileName);
    			} else {
    				canvas.showMessage("Picture updated");
    			}
    		} else {
    			canvas.showMessage("Please select a profile to change picture");
    		}
    	} else if (e.getActionCommand().equals("Add Friend") || e.getSource() == friendField &&
    			!friendField.getText().equals("")	 ) {
    		String friendName = friendField.getText();
    		if (currentProfile != null) {
    			if (currentProfile.getName().equals(friendName)) {
    				canvas.showMessage("You cannot friend yourself");
    			} else if (db.containsProfile(friendName)) {
    				FacePamphletProfile friendProfile = db.getProfile(friendName);
    				
    				if (currentProfile.addFriend(friendName) == true) {
    					friendProfile.addFriend(givenName);
    					canvas.displayProfile(currentProfile);
    					canvas.showMessage(friendName + " added as a friend.");
    				} else {
    					canvas.showMessage(currentProfile.getName() + " already has " + friendName + " as a friend."); 
    				}
    			} else {
    				canvas.showMessage(friendName + " does not exist.");
    			}
    		} else {
    			canvas.showMessage("Please select a profile to add friend.");
    		}
    	}
    	// println("--> Current profile: " + currentProfile.toString());
	}
    
    private JTextField nameField;
    private JTextField statusField;
    private JTextField picField;
    private JTextField friendField;
    
    private FacePamphletDatabase db = new FacePamphletDatabase();
    private FacePamphletProfile currentProfile = null;
    private FacePamphletCanvas canvas;
}
