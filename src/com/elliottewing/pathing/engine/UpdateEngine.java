package com.elliottewing.pathing.engine;

import com.elliottewing.pathing.Game;

public class UpdateEngine extends Engine {
	
	private long lastUpdate;

	public UpdateEngine(Game instance) {
		super(instance);
	}
	
	@Override
	protected void init() {
		super.init();
		nsPerUpdate = 1000000000/30;
		lastUpdate = System.nanoTime();
	}

	@Override
	protected void EngineLoop() {
		long curRender = System.nanoTime();
		long dTime = curRender - lastUpdate;
		lastUpdate = curRender;
		System.out.println("Updating " + 1e9f/dTime + " times per second.");
		game.getWorld().update(nsPerUpdate/1e9f);
	}

	@Override
	protected String getEngineName() {
		return "UpdateEngine";
	}


}
