package edu.ycp.cs320.middle_earth.images;

import java.io.*;
import java.awt.*;

public class TileMap {
	private int x;
	private int y;
	
	private int tileSize;
	private int[][] map;
	private int mapWidth;
	private int mapHeight;
	
	private int player_x;
	private int player_y;
	
	public TileMap(String s, int tileSize) {
		this.tileSize = tileSize;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(s));
			player_y = Integer.parseInt(br.readLine());
			player_x = Integer.parseInt(br.readLine());
			mapWidth = Integer.parseInt(br.readLine());
			mapHeight = Integer.parseInt(br.readLine());
			map = new int[mapHeight][mapWidth];
			
			String delimiters = " ";
			for (int row = 0; row < mapHeight; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for (int col = 0; col < mapWidth; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
			br.close();
			
		} catch (Exception e) {}
		
		
		}
		
	public void update(String direction) {
		map[player_y][player_x] = 1;
		if (direction == "north") {
			player_y--;
		} else if (direction == "south") {
			player_y++;
		} else if (direction == "east") {
			player_x++;
		} else if (direction == "west") {
			player_x--;
		} else if (direction == "northeast") {
			player_y--;
			player_x++;
		} else if (direction == "northwest") {
			player_y--;
			player_x--;
		} else if (direction == "southeast") {
			player_y++;
			player_x++;
		} else if (direction == "southwest") {
			player_y++;
			player_x--;
		}
		map[player_y][player_x] = 2;
			
	}

	public void draw(Graphics2D g) {
		for (int row = 0; row < mapHeight; row++){
			for (int col = 0; col < mapWidth; col++) {
				int rc = map[row][col];
				
				if (rc == 0) {
					g.setColor(Color.BLACK);
				}
				if (rc == 1) {
					g.setColor(Color.WHITE);
				}
				if (rc == 2 ) {
					g.setColor(Color.RED);
				}
				
				g.fillRect(x + col * tileSize,  y + row * tileSize,  tileSize,  tileSize);
			}
		}
	}
	
	public void save(String fileLocation){
		try {
			PrintWriter write = new PrintWriter(fileLocation);
			write.println(player_y);
			write.println(player_x);
			write.println(mapWidth);
			write.println(mapHeight);
			for (int row = 0; row < mapHeight; row++){
				for (int col = 0; col < mapWidth; col++) {
					if (col == mapWidth - 1) {
						write.print(map[row][col]);
					} else {
						write.print(map[row][col] + " ");
					}
				}
				if (row == mapHeight - 1) {}
				else {
					write.println();
				}
			}
			write.close();
		} catch (FileNotFoundException e) {
			String newFolder = fileLocation.substring(0, fileLocation.length()-12);
			Boolean success = new File(newFolder).mkdir();
			if (success) {
				save(fileLocation);
			}
		}
		
	}
}
