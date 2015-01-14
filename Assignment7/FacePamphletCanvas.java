/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		GLabel message = new GLabel(msg);
		double x = getWidth() / 2 - message.getWidth() * 1 / 2;
		double y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		if (getElementAt(lastMsgX, lastMsgY) != null) {
			remove(getElementAt(lastMsgX, lastMsgY));
		}
		message.setFont(MESSAGE_FONT);
		add(message, x, y);
		lastMsgX = x;
		lastMsgY = y;
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		addName(profile.getName());
		addImage(profile.getImage());
		addStatus(profile.getStatus());
		addFriend(profile.getFriends());
	}
	
	private void addName(String name) {
		GLabel nameLabel = new GLabel(name);
		double x = LEFT_MARGIN;
		nameLabelHeight = nameLabel.getHeight();
		double y = TOP_MARGIN + nameLabelHeight;
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameLabel.setColor(Color.BLUE);
		add(nameLabel, x, y);
	}
	
	private void addImage(GImage img) {
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + nameLabelHeight + IMAGE_MARGIN;
		if (img != null) {
			img.setBounds(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(img);
		} else {
			GRect imgRect = new GRect(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
			GLabel noImg = new GLabel("No Image");
			noImg.setFont(PROFILE_IMAGE_FONT);
			double noImgX = x + IMAGE_WIDTH / 2 - noImg.getWidth() / 2;
			double noImgY = y + IMAGE_HEIGHT / 2;
			add(imgRect);
			add(noImg, noImgX, noImgY);
		}
	}
	
	private void addStatus(String status) {
		GLabel statusLabel = new GLabel(status);
		statusLabel.setFont(PROFILE_STATUS_FONT);
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + nameLabelHeight + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + statusLabel.getHeight();
		if (getElementAt(x, y) != null) {
			remove(getElementAt(x, y));
		}
		add(statusLabel, x, y);
	}
	
	private void addFriend(Iterator<String> friends) {
		GLabel friendsLabel = new GLabel("Friends:");
		friendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		double x = getWidth() / 2;
		double y = TOP_MARGIN + nameLabelHeight;
		add(friendsLabel, x, y);
		
		for (int i = 1; friends.hasNext(); ++i) {
			GLabel friendName = new GLabel(friends.next());
			friendName.setFont(PROFILE_FRIEND_FONT);
			double height = y + friendsLabel.getHeight() * i;
			add(friendName, x, height);
		}
	}

	double nameLabelHeight = 0;
	double lastMsgX = 0;
	double lastMsgY = 0;
	
}
