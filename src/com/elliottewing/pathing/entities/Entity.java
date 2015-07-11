package com.elliottewing.pathing.entities;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.elliottewing.pathing.world.Location;
import com.elliottewing.pathing.world.World;

public abstract class Entity {

	protected Location location;
	private ImageIcon texture;
	
	public abstract void update(float dtime);
	
	public Entity(World world) {
		this(new Location(0, 0, world));
	}
	
	public Entity(Location loc) {
		if(getIconPath() == null) {
			texture = null;
		} else {
			texture = new ImageIcon(getIconPath());
		}
		location = loc;
	}
	
	public void move(Location loc) {
		location = loc;
	}
	
	public Location getCellLocation() {
		return new Location((int) location.x, (int)location.y, location.getWorld());
	}
	
	public void render(Graphics g) {
		g.drawImage(texture.getImage(), (int) (10*location.x), (int) (10*location.y), null);
	}
	
	public Location getLocation() { return location; }
	
	@Override
	public String toString() {
		return location + " -- " + texture.hashCode();
	}
	
	/***** PROTECTED FUNCTIONS *****/
	protected void setTexture(ImageIcon newTex) {
		this.texture = newTex;
	}
	
	protected abstract String getIconPath();
	
}
