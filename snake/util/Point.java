package com.harthoric.snake.util;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point translate(int dx, int dy) {
		return new Point(x + dx, y + dy);
	}

	public boolean equals(Object newPoint) {
		if (!(newPoint instanceof Point))
			return false;
		Point point = (Point) newPoint;
		return x == point.x && y == point.y;
	}

}
