package com.elliottewing.pathing.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.elliottewing.pathing.world.Location;
import com.elliottewing.pathing.world.World;

public class Player extends Entity {

	public Player(World world) {
		this(new Location(0, 0, world));
	}
	
	public Player(Location loc) {
		super(loc);
		BufferedImage img = new BufferedImage(10, 10,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = img.createGraphics();
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 10, 10);
		setTexture(new ImageIcon(img));
		
	}

	@Override
	protected String getIconPath() {
		return null;
	}

	@Override
	public void update(float dtime) {
		
	}

}
