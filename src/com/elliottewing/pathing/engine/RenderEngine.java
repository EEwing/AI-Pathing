package com.elliottewing.pathing.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.elliottewing.pathing.Game;

public class RenderEngine extends Engine {
	
	private JFrame window;
	private Canvas canvas;
	private BufferStrategy bs;
	private Graphics g;
	private int lastFPS;
	
	private long lastRender;
	private long lastFPSUpdate;

	public RenderEngine(Game instance) {
		super(instance);
	}

	@Override
	protected void init() {
		super.init();
		initWindow();
		lastRender = System.nanoTime();
	}
	
	@Override
	protected void cleanUp() {
		super.cleanUp();
		window.dispose();
	}
	
	@Override
	protected void EngineLoop() {
		long curRender = System.nanoTime();
		long dTime = curRender - lastRender;
		lastRender = curRender;
		int fps = (int) (1e9 / dTime);
		if (curRender - lastFPSUpdate > 5e8) {
			lastFPSUpdate = curRender;
			lastFPS = fps;
		}
		g = bs.getDrawGraphics();
		
		game.getWorld().render(g);
		
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.drawString("" + lastFPS, 0, 14);
		bs.show();
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	@Override
	protected String getEngineName() {
		return "RenderEngine";
	}
	
	private void initWindow() {
		window = new JFrame("Window!");
		canvas = new Canvas();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setPreferredSize(new Dimension(1920, 1080));
		window.getContentPane().add(canvas);
		window.pack();
		window.setVisible(true);
		window.setLocation(1920 / 2 - window.getWidth() / 2,
				1080 / 2 - window.getHeight() / 2);
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
	}
	
	
	
}
