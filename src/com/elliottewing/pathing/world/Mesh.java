package com.elliottewing.pathing.world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Mesh {

	private int width;
	private int height;

	boolean[][] mesh;

	public Mesh(int w, int h) {
		width = w;
		height = h;
		mesh = new boolean[w][h];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void randomize() {
		Random rand = new Random();
		for (int j = 0; j < height; ++j) {
			for (int i = 0; i < width; ++i) {
				if(rand.nextInt(1000) < 300) {
					mesh[i][j] = true;
				} else {
					mesh[i][j] = false;
				}
			}
		}
	}
	
	public void setSolid(int i, int j) {
		mesh[i][j] = true;
	}
	
	public void setSolid(float x, float y) {
		setSolid((int) x, (int) y);
	}
	
	public void setSolid(Location loc) {
		setSolid(loc.x, loc.y);
	}

	public boolean isSolid(int i, int j) {
		return mesh[i][j];
	}
	
	public boolean isSolid(float x, float y) {
		return isSolid((int) x, (int) y);
	}
	
	public boolean isSolid(Location loc) {
		return isSolid(loc.x, loc.y);
	}

	public void setAir(int i, int j) {
		mesh[i][j] = false;
	}
	
	public void setAir(float x, float y) {
		setAir((int) x, (int) y);
	}
	
	public void setAir(Location l) {
		setAir(l.x, l.y);
	}

	public void render(Graphics g) {
		for (int j = 0; j < height; ++j) {
			for (int i = 0; i < width; ++i) {
				if (mesh[i][j]) {
					g.setColor(Color.black);
				} else {
					g.setColor(Color.white);
				}
				g.fillRect(10 * i, 10 * j, 10, 10);
			}
		}
	}

}
