package com.elliottewing.pathing.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.elliottewing.pathing.AStarPathAlgorithm;
import com.elliottewing.pathing.PathingAlgorithm;
import com.elliottewing.pathing.world.Location;
import com.elliottewing.pathing.world.World;

public class EnemyCyclops extends Enemy {

	public EnemyCyclops(World world) {
		this(new Location(0, 0, world));
	}
	
	public EnemyCyclops(Location loc) {
		super(loc);
		PathingAlgorithm alg = new AStarPathAlgorithm();
		alg.setEntity(this);
		setPathingAlgorithm(alg);

		BufferedImage img = new BufferedImage(10, 10,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = img.createGraphics();
		g.setColor(Color.RED);
		g.fillRect(0, 0, 10, 10);
		setTexture(new ImageIcon(img));
	}

	
}
