/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		entriesDisplayList = new ArrayList<NameSurferEntry>();
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
		entriesDisplayList.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		entriesDisplayList.add(entry);
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//	 You fill this in //
		removeAll();
		drawGraph();
		for (int i = 0; i < entriesDisplayList.size(); ++i) {
			drawEntry(entriesDisplayList.get(i), i);
		}
	}
	
	private void drawGraph() {
		drawVerticalLines();
		drawHorizontalLines();
		drawDecades();
	}
	
	private void drawVerticalLines() {
		for (int i = 0; i < NDECADES; ++i) {
			double x = i * (getWidth() / NDECADES);
			GLine line = new GLine(x, 0, x, getHeight());
			add(line);
		}
	}
	
	private void drawHorizontalLines() {
		double upperLineY = GRAPH_MARGIN_SIZE;
		double lowerLineY = getHeight() - GRAPH_MARGIN_SIZE;
		GLine upperLine = new GLine(0, upperLineY, getWidth(), upperLineY);
		GLine lowerLine = new GLine(0, lowerLineY, getWidth(), lowerLineY);
		
		add(upperLine);
		add(lowerLine);
	}
	
	private void drawDecades() {
		for (int i = 0; i < NDECADES; ++i) {
			int decade = START_DECADE + i * 10;
			double x = 2 + i * (getWidth() / NDECADES);
			double y = getHeight() - GRAPH_MARGIN_SIZE / 4;
			GLabel displayedDecade = new GLabel(Integer.toString(decade), x, y);
			add(displayedDecade);
		}
	}
	
	private void drawEntry(NameSurferEntry entry, int entryNum) {
		for (int i = 0; i < NDECADES - 1; ++i) {
			int currentDecadeRanking = entry.getRank(i);
			int nextDecadeRanking = entry.getRank(i + 1);
			double currentX = i * (getWidth() / NDECADES);
			double nextX = (i + 1) * (getWidth() / NDECADES);
			double currentY = 0;
			double nextY = 0;
			
			if (currentDecadeRanking != 0) {
				currentY = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * currentDecadeRanking / MAX_RANK;
			} else {
				currentY = getHeight() - GRAPH_MARGIN_SIZE;
			}
			
			if (nextDecadeRanking != 0) {
				nextY = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * nextDecadeRanking / MAX_RANK;
			} else {
				nextY = getHeight() - GRAPH_MARGIN_SIZE;
			}
			
			GLine line = new GLine(currentX, currentY, nextX, nextY);
			line.setColor(colorTable[entryNum % 4]);
			add(line);
		}
		
		for (int i = 0; i < NDECADES; ++i) {
			int rank = entry.getRank(i);
			String label = entry.getName() + " " + Integer.toString(rank);
			double x = i * (getWidth() / NDECADES) + 5;
			double y = GRAPH_MARGIN_SIZE + (getHeight() - 2 * GRAPH_MARGIN_SIZE) * rank / MAX_RANK - 5;
			
			if (rank == 0) {
				y = getHeight() - GRAPH_MARGIN_SIZE - 5;
				label = entry.getName() + " *";
			}
			
			GLabel nameLabel = new GLabel(label, x, y);
			nameLabel.setColor(colorTable[entryNum % 4]);
			add(nameLabel);
		}
		
	}
	
	private ArrayList<NameSurferEntry> entriesDisplayList;
	private Color[] colorTable = {Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA};
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
