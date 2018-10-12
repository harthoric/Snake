package com.harthoric.snake.entity;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

import com.harthoric.snake.render.Map;
import com.harthoric.snake.util.Point;
import com.harthoric.snake.util.Velocity;

public class Snake extends Entity {
	public static final Color DEAD = Color.RED;

	private int length;
	private boolean overlap, allowWrap;
	private List<Point> points;
	private Point head;
	private Map map;

	public Snake(Map map, Point head, Velocity velocity, boolean allowWrap) {
		super(velocity);
		this.map = map;
		this.head = head;
		this.allowWrap = allowWrap;
		setLength(1);
		points = new LinkedList<>();
		points.add(head);
		overlap = false;
	}

	private void updatePoint(Point point) {
		overlap = points.contains(point);
		
		if (allowWrap)
			point = map.touchedSide(point, true);
		else if (!overlap)
			overlap = map.touchedSide(point);
		
		points.add(point);
		head = point;
	}

	public void grow() {
		if (!paused()) {
			setLength(getLength() + 1);
			updatePoint(head.translate(this.getVelocity().getX(), this.getVelocity().getY()));
		}
	}

	public List<Point> getBody() {
		return points;
	}

	public Point getHead() {
		return head;
	}

	public boolean overlapped() {
		return overlap && getLength() != 1;
	}

	public boolean paused() {
		return this.getVelocity().getX() == 0 && this.getVelocity().getY() == 0;
	}

	public void move() {
		if (!paused()) {
			updatePoint(head.translate(this.getVelocity().getX(), this.getVelocity().getY()));
			points.remove(0);
		}
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}