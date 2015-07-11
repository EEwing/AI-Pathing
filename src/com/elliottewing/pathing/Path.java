package com.elliottewing.pathing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import com.elliottewing.pathing.world.Location;

public class Path implements Iterable<Location> {

	private ArrayList<Location> path;

	public Path() {
		path = new ArrayList<Location>();
	}

	public Location getNextLocation() {
		if (path.isEmpty()) {
			return null;
		} else {
			return path.get(0);
		}
	}

	public int length() {
		return path.size();
	}

	public Location getLastLocation() {
		if (path.isEmpty()) {
			return null;
		}
		return path.get(path.size() - 1);
	}

	public Location pop() {
		if (path.isEmpty())
			return null;
		Location loc = path.get(0);
		synchronized (path) {
			path.remove(0);
		}
		return loc;
	}

	public void push(Location loc) {
		synchronized (path) {
			path.add(0, loc);
		}
	}

	@SuppressWarnings("unchecked")
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		ArrayList<Location> p;
		synchronized (path) {
			p = (ArrayList<Location>) path.clone();
		}
		for (Location loc : p) {
			g.fillRect((int) (10 * loc.x + 1), (int) (10 * loc.y + 1), 8, 8);
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (Location l : path) {
			s += "(" + l + ")\n";
		}
		return s;
	}

	@Override
	public Iterator<Location> iterator() {
		Iterator<Location> iter = new Iterator<Location>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < path.size() && path.get(index) != null;
			}

			@Override
			public Location next() {
				return path.get(index++);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return iter;
	}

	public boolean hasNext() {
		return iterator().hasNext();
	}
}
