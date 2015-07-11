package com.elliottewing.pathing.world;

import java.awt.Graphics;
import java.util.ArrayList;

import com.elliottewing.pathing.entities.EnemyCyclops;
import com.elliottewing.pathing.entities.Entity;
import com.elliottewing.pathing.entities.Player;

public class World {
	
	private ArrayList<Entity> entities;
	private Player player;
	int width;
	int height;
	private Mesh mesh;
	
	public World() {
		entities = new ArrayList<Entity>();
		width = 100;
		height = 100;
		mesh = new Mesh(width, height);
		
		mesh.randomize();
		player = new Player(this.getNearestEmptySpace(new Location(70, 70, this)));
		EnemyCyclops ent = new EnemyCyclops(this.getNearestEmptySpace(new Location(2, 2, this)));
		
		EnemyCyclops ent2 = new EnemyCyclops(this.getNearestEmptySpace(new Location(20, 40, this)));
		ent.setTarget(player);
		ent2.setTarget(ent);
		//ent2.getPathingAlgorithm().auto = true;
		entities.add(ent);
		entities.add(ent2);
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public void render(Graphics g) {
		mesh.render(g);
		for(Entity ent : entities) {
			ent.render(g);
		}
		player.render(g);
	}
	
	public boolean isSolid(int x, int y) {
		return mesh.isSolid(x, y);
	}
	
	public boolean isSolid(float x, float y) {
		return isSolid((int) x, (int) y);
	}
	
	public Location getNearestEmptySpace(Location loc) {
		if(loc.getWorld() != this) {
			return null;
		}
		Location rtn = null;
		for(int rad=1; rad<6; ++rad) {
			for(int y=-rad; y<=rad; ++y) {
				
				int newY = (int) loc.y+y;
				if(newY<0 || newY>=width) continue;
				
				for( int x=-rad; x<=rad; ++x) {
					
					int newX = (int) loc.x+x;
					if((x==y && y==0) || newX<0 || newX>=width) continue;
					if(!mesh.isSolid(newX, newY)) {
						rtn = new Location(loc.x+x, loc.y+y, this);
						break;
					}
				}
				if(rtn != null) break;
			}
			if(rtn != null) break;
		}
		return rtn;
	}
	
	public void update(float dtime) {
		for(Entity ent : entities) {
			//System.out.println("Updating entity");
			ent.update(dtime);
		}
		//System.out.println("Updating player");
		player.update(dtime);
	}

}
