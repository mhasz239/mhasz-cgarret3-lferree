package edu.ycp.cs320.middle_earth.images;

import javax.swing.JFrame;

import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

public class DrawMap {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Map");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MapTile tile = new MapTile();
		tile.setConnection("north", 3);
		tile.setConnection("northeast", 4);
		tile.setConnection("east", 1);
		window.setContentPane(new MapPanel(tile));
		window.pack();
		window.setVisible(true);
		
	}
	
}
