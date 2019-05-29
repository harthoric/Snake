package com.harthoric.snake.entity;

import com.harthoric.snake.util.Vector2D;

public class Entity {

	private Velocity velocity;

	private static final Vector2D UP = new Vector2D(0, -1), DOWN = new Vector2D(0, 1), LEFT = new Vector2D(-1, 0),
			RIGHT = new Vector2D(1, 0);

	public Entity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public void moveUp() {
		velocity = velocity != DOWN ? UP : velocity;
	}

	public void moveDown() {
		velocity = velocity != UP ? DOWN : velocity;
	}

	public void moveLeft() {
		velocity = velocity != RIGHT ? LEFT : velocity;
	}

	public void moveRight() {
		velocity = velocity != LEFT ? RIGHT : velocity;
	}

}
