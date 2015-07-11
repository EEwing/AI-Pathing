package com.elliottewing.pathing;

import com.elliottewing.pathing.world.Location;

public class Node {
	private Node parent;
	private Location loc;
	private double g;
	private double h;

	public Node(Location loc, Node par, double h) {
		this.loc = loc;
		setParent(par);
		this.h = h;
	}
	
	private void update() {
		if (parent == null) {
			g = 0;
		} else {
			g = parent.getG() + parent.getLocation().dist(this.getLocation());
		}

	}

	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node par) {
		this.parent = par;
		update();
	}
	
	public Location getLocation() {
		return loc;
	}

	public double getG() {
		return g;
	}

	public double getH() {
		return h;
	}

	public double getF() {
		return g + h;
	}

	@Override
	public String toString() {
		return loc + "(" + parent.getLocation() + ")";
	}

	@Override
	public boolean equals(Object n) {
		if (!(n instanceof Node))
			return false;
		else
			return ((Node) n).getLocation().equals(loc);
	}

}
