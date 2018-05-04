package edu.ycp.cs320.middle_earth.images;

import javax.swing.JFrame;

public class DrawMap {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Map");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new MapPanel());
		window.pack();
		window.setVisible(true);
		
	}
	
}
