package com.harthoric.snake.entity;

import com.harthoric.snake.util.Velocity;

public class Entity {

	private Velocity velocity;

	private static final Velocity UP = new Velocity(0, -1), DOWN = new Velocity(0, 1), LEFT = new Velocity(-1, 0),
			RIGHT = new Velocity(1, 0);

	public Entity(Velocity velocity) {
		this.velocity = velocity;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
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
