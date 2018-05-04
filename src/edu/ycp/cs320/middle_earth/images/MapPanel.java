package edu.ycp.cs320.middle_earth.images;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class MapPanel  extends JPanel implements Runnable {
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	
	private Thread thread;
	private boolean running;
	private String direction = "";
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS = 30;
	private int targetTime = 1000 / FPS;
	
	private TileMap tileMap;
	
	public MapPanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void run() {
		init();
		
		long startTime;
		long urdTime;
		long waitTime;
		
		render();
		draw();
		
		while (running) {
			startTime = System.nanoTime();
			if (direction != "") {
				update(direction);
				direction = "";
				render();
				draw();
			}
			
			urdTime = (System.nanoTime() - startTime) / 100000;
			waitTime = targetTime - urdTime; 
			
			try{
				Thread.sleep(waitTime);
			} catch (Exception e) {
				
			}
			
		}
	}
	
	private void init() {
		
		running = true;
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		File currentDirFile = new File(".");
		String path = currentDirFile.getAbsolutePath();
		path = path.substring(0, path.length()-1);
		tileMap = new TileMap((path + "src/edu/ycp/cs320/middle_earth/images/map.txt"), 20);
		
		
		
		
	}
	
	//////////////////////////////////////////////////////
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	private void update(String direction) {
		tileMap.update(direction);
	}
	
	private void render() {
		tileMap.draw(g);

	}
	
	private void draw() {
		Graphics g2 = getGraphics();
		g2.drawImage(image,  0, 0, null);
		try {
			File currentDirFile = new File(".");
			String path = currentDirFile.getAbsolutePath();
			path = path.substring(0, path.length()-1);
			ImageIO.write(image,"jpeg", new File (path+"src/edu/ycp/cs320/middle_earth/images/map.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.dispose();
	}
	
	public void save(String username, int gameID) {
		File currentDirFile = new File(".");
		String path = currentDirFile.getAbsolutePath();
		path = path.substring(0, path.length()-1);
		path = path+"src/edu/ycp/cs320/middle_earth/images/"+username+"/map"+gameID+".txt";
		tileMap.save(path);
	}
}
