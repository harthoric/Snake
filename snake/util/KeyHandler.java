package com.harthoric.snake.util;

import com.harthoric.snake.render.Map;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {

	private Map map;

	public KeyHandler(Map map) {
		this.map = map;
	}

	@Override
	public void handle(KeyEvent e) {
		var snake = map.getSnake();
		switch (e.getCode()) {
		case W:
			snake.moveUp();
			break;
		case S:
			snake.moveDown();
			break;
		case A:
			snake.moveLeft();
			break;
		case D:
			snake.moveRight();
			break;
		default:
			break;
		}
	}

}
