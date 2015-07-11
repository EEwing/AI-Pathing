package com.elliottewing.pathing;

import java.awt.Graphics;
import java.util.ArrayList;

import com.elliottewing.pathing.entities.Entity;
import com.elliottewing.pathing.world.Location;

public abstract class PathingAlgorithm {

	private Entity target = null;
	private Entity source = null;
	protected Location targetCell;
	protected Location srcCell;

	protected ArrayList<Node> evaluated;
	protected ArrayList<Node> toEvaluate;

	protected Path recentPath = null;

	protected abstract void calcPath();

	public void render(Graphics g) {
		if (recentPath != null) {
			recentPath.render(g);
		}
	}

	public void update() {
		if (target == null || source == null) {
			return;
		}
		if (shouldUpdate()) {
			targetCell = target.getCellLocation();
			srcCell = source.getCellLocation();
			
			// Heavy function, only call when path might have changed
			calcPath();
		}
	}

	public Path getResult() {
		return recentPath;
	}

	public void setTarget(Entity ent) {
		this.target = ent;
	}

	public Entity getTarget() {
		return target;
	}

	public void setEntity(Entity ent) {
		this.source = ent;
	}

	public Entity getEntity() {
		return source;
	}

	public boolean shouldUpdate() {
		return targetCell != target.getCellLocation() || srcCell != source.getCellLocation();
	}
}
