package com.elliottewing.pathing.entities;

import java.awt.Graphics;

import com.elliottewing.pathing.PathingAlgorithm;
import com.elliottewing.pathing.world.Location;

public abstract class Enemy extends Entity {

	private PathingAlgorithm pathAlg = null;
	protected Entity target = null;

	public Enemy(Location loc) {
		super(loc);
	}

	public void setTarget(Entity ent) {
		this.target = ent;
		if (pathAlg != null) {
			pathAlg.setTarget(target);
			// pathAlg.isUpdated = false;
		}
	}

	protected void setPathingAlgorithm(PathingAlgorithm alg) {
		pathAlg = alg;
		// pathAlg.isUpdated = false;
		pathAlg.setTarget(target);
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		if (pathAlg != null) {
			pathAlg.render(g);
			// if (pathAlg.getResult() != null) {
			// pathAlg.getResult().render(g);
			// }
		}
	}

	@Override
	public void update(float dtime) {
		if (target != null) {
			if (pathAlg != null && pathAlg.shouldUpdate()) {
				//System.out.println("Pathing from " + getLocation() + " to " + target.getLocation());
				pathAlg.update();
				if (pathAlg.getResult() != null) {
					if (pathAlg.getResult().hasNext()) {
						// Move to next location
						
						Location targetLocation = pathAlg.getResult().getNextLocation();
						Location dLoc = targetLocation.subtract(getLocation());
						
						float dx = (dLoc.x / dLoc.getMagnitude()) * dtime * 5;
						float dy = (dLoc.y / dLoc.getMagnitude()) * dtime * 5;
						
						Location newLoc = new Location(location.x+dx, location.y+dy, location.getWorld());
						if(newLoc.dist(getLocation()) > targetLocation.dist(getLocation())) {
							System.out.println("Moving past waypoint");
							move(targetLocation);
						} else {
							move(newLoc);
						}
					}
				} else {
					System.out.println("No Path Found");
				}
			}
		}
	}

	@Override
	protected String getIconPath() {
		return "res//entity//enemy.png";
	}

}
