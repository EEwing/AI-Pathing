package com.elliottewing.pathing.engine;

import com.elliottewing.pathing.Game;

public abstract class Engine extends Thread {

	private boolean shutdown = false;

	protected int nsPerUpdate = -1;
	protected Game game;

	public Engine(Game instance) {
		game = instance;
	}

	@Override
	public void run() {
		init();
		long lastRun = System.nanoTime();
		while (!shouldShutDown()) {
			if (nsPerUpdate == -1) {
				EngineLoop();
			} else {
				while (System.nanoTime() - lastRun > nsPerUpdate) {
					EngineLoop();
					lastRun += nsPerUpdate;
				}
			}
		}
		cleanUp();
	}

	public synchronized void shutdown() {
		shutdown = true;
	}

	protected synchronized boolean shouldShutDown() {
		return shutdown;
	}

	protected abstract void EngineLoop();

	protected abstract String getEngineName();

	protected void init() {
		System.out.println("Initializing Engine: " + getEngineName());
	}

	protected void cleanUp() {
		System.out.println("Shutting Down Engine: " + getEngineName());
	}

}
