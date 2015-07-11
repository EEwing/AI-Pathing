package com.elliottewing.pathing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.elliottewing.pathing.world.Location;
import com.elliottewing.pathing.world.World;

public class AStarPathAlgorithm extends PathingAlgorithm {

	@Override
	protected void calcPath() {
		if(targetCell.getWorld() != srcCell.getWorld()) {
			return;
		}
		//System.out.println("Finding path from " + srcCell + " to " + targetCell);
		final World world = targetCell.getWorld();
		evaluated = new ArrayList<Node>(); // closed set
		toEvaluate = new ArrayList<Node>(); // open set

		toEvaluate.add(new Node(srcCell, null, heuristic(srcCell)));
		
		FComparator compare = new FComparator();
		while (!toEvaluate.isEmpty()) {

			// Sort Open set by F-values (smallest first)
			Collections.sort(toEvaluate, compare);

			Node curNode = toEvaluate.remove(0);
			
			// Check if we found the destination and rebuild path
			//   if we did.
			if(curNode.getLocation().equals(targetCell)) {
				Node topNode = curNode;
				Path path = new Path();
				while (topNode.getParent() != null) {
					path.push(topNode.getLocation());
					topNode = topNode.getParent();
				}
				recentPath = path;
				break;
			}
			
			// Find valid neighbors we can move to
			ArrayList<Node> neighbors = new ArrayList<Node>();
			for (int j = -1; j <= 1; ++j) {
				for (int i = -1; i <= 1; ++i) {
					if (i == j && i == 0)
						continue;
					Location loc = curNode.getLocation();
					int newX = (int) loc.x + i;
					int newY = (int) loc.y + j;
					
					// out of bounds
					if (newX < 0 || newY < 0
							|| newX >= world.getWidth()
							|| newY >= world.getHeight())
						continue;
					
					// solid block
					if (world.isSolid(newX, newY)) {
						continue;
					}
					
					if(i != 0 && j != 0) {
						// Can't move to (i, j) if it is through a corner
						if(world.isSolid(loc.x, newY) || world.isSolid(newX, loc.y)) continue;
					}

					Location newLoc = new Location(newX, newY, world);
					neighbors.add(new Node(newLoc, curNode, heuristic(newLoc)));
				}
			}
			
			// Add neighbors to the Open Set
			for (Node n : neighbors) {
				if (evaluated.contains(n)) {
					continue;
				}
				
				if (toEvaluate.contains(n)) {
					Node eval = toEvaluate.get(toEvaluate.indexOf(n));
					if(eval.getF() > n.getF()) {
						toEvaluate.remove(n);
						toEvaluate.add(n);
					}
				} else {
					toEvaluate.add(n);
				}
			}
			
			// Add current node to the Closed set
			evaluated.add(curNode);
		}
		//isUpdated = true;
	}

	private double heuristic(Location l) {
		// Return Pythagorean distance between the node and destination
		return l.dist(targetCell);
	}
}

class FComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		return (int) Math.round(1e6 * (o1.getF() - o2.getF()));
	}

}
