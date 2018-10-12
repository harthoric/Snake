package com.harthoric.snake.game;

import com.harthoric.snake.render.Map;

import javafx.scene.canvas.Canvas;

public class MainGameLoop implements Runnable {
	private final Map map;
	private final Canvas canvas;
	private final float INTERVAL = 50.0f;;

	private boolean running, paused;

	public MainGameLoop(final Map map, final Canvas canvas) {
		this.map = map;
		this.canvas = canvas;

		running = true;
		setPaused(false);
	}

	@Override
	public void run() {
		while (running && !isPaused()) {
			var time = System.currentTimeMillis();

			map.update();
			map.draw(map, canvas.getGraphicsContext2D());

			if (map.getSnake().overlapped()) {
				pauseGame();
				break;
			}

			time = System.currentTimeMillis() - time;

			if (time < INTERVAL) {
				try {
					Thread.sleep((long) (INTERVAL - time));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void pauseGame() {
		setPaused(true);
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

}
