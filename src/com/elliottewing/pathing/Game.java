package com.elliottewing.pathing;

import com.elliottewing.pathing.engine.RenderEngine;
import com.elliottewing.pathing.engine.UpdateEngine;
import com.elliottewing.pathing.world.World;

public class Game {

	public static Game instance;


	// FPS info

	private boolean isRunning = true;
	private World world;
	private RenderEngine renderEngine;
	private UpdateEngine updateEngine;

	public static void main(String args[]) {
		instance = new Game();
	}

	public static Game getInstance() {
		return instance;
	}

	public Game() {

		renderEngine = new RenderEngine(this);
		updateEngine = new UpdateEngine(this);

		world = new World();

		renderEngine.start();
		updateEngine.start();
		while (isRunning) {
			Thread.yield();
		}
		System.out.println("Shutting down");
	}

	public World getWorld() {
		return world;
	}
	
}
