package com.elliottewing.pathing.world;

public class Location {

	public float x;
	public float y;
	
	private World world;
	//public int z;
	
	public Location(Location cpy) {
		this.x = cpy.x;
		this.y = cpy.y;
		this.world = cpy.getWorld();
	}
	
	public Location(float x, float y, World world) {
		this.x = x;
		this.y = y;
		this.world = world;
		//this.z = z;
	}
	
	public World getWorld() {
		return world;
	}
	
	public float getMagnitudeSq() {
		return x*x+y*y;
	}
	
	public float getMagnitude() {
		return (float) Math.sqrt(getMagnitudeSq());
	}
	
	public Location subtract(Location loc) {
		if(loc.world != world) {
			System.out.println("Cannot subtract locations in other realities");
			return null;
		}
		return new Location(x-loc.x, y-loc.y, world);
	}
	
	public Location add(Location loc) {
		if(loc.world != world) {
			System.out.println("Cannot add locations in other realities");
			return null;
		}
		return new Location(x+loc.x, y+loc.y, world);
	}
	
	public double dist(Location loc) {
		Location diff = loc.subtract(this);
		return diff.getMagnitude();
	}
	
	@Override
	public String toString() {
		return world + ": (" + x + ", " + y + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Location)) return false;
		Location l = (Location) obj;
		return x == l.x && y == l.y;
	}
	
}
