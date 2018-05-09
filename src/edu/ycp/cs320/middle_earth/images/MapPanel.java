package edu.ycp.cs320.middle_earth.images;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class MapPanel  extends JPanel implements Runnable {
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	
	private String username;
	private int gameID;
	
	private Thread thread;
	private boolean running;
	private String direction = "";
	private MapTile tile = new MapTile();
	
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
	
	public MapPanel(MapTile tile) {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		this.tile = tile;
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
			if (this.direction != "") {
				update(direction, tile);
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
		System.out.println(username+gameID);
		if (this.username == "new") {
			tileMap = new TileMap((path + "src/edu/ycp/cs320/middle_earth/images/map5-5.txt"), 80, tile);
		} else {
			tileMap = new TileMap((path + "static/map/"+this.username+this.gameID+".txt"), 80, tile);
		}
		
		
		
	}
	
	//////////////////////////////////////////////////////
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void setMapTile(MapTile tile){
		this.tile = tile;
	}
	
	private void update(String direction, MapTile tile) {
		tileMap.update(direction, tile);
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
			path = path+"static/map/map.jpeg";
			ImageIO.write(image,"jpeg", new File (path));
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
		path = path+"static/map/"+username+gameID+".txt";
		tileMap.save(path);
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public int getgameID() {
		return gameID;
	}

	public void setgameID(int gameID) {
		this.gameID = gameID;
	}
}
