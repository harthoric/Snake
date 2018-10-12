package com.harthoric.snake.render;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Random;

import com.harthoric.snake.entity.Snake;
import com.harthoric.snake.util.Point;
import com.harthoric.snake.util.Velocity;

public class Map {
	public static final int SIZE = 10;

	private Random random = new Random();

	private final int columns, rows;

	private Snake snake;
	private Point apple;

	public Map(final int width, final int height) {
		rows = width / SIZE;
		columns = height / SIZE;
		snake = new Snake(this, new Point(rows / 10 / 2, columns / 10 / 2), new Velocity(0, 0), false);
		RandomPoint();
	}

	public boolean touchedSide(Point point) {
		int x = point.getX(), y = point.getY();
		if (x >= rows || x < 0 || y >= columns || y < 0)
			return true;
		return false;
	}

	public Point touchedSide(Point point, boolean wrap) {
		int x = point.getX(), y = point.getY();
		if (x >= rows)
			x = 0;
		if (y >= columns)
			y = 0;
		if (x < 0)
			x = rows - 1;
		if (y < 0)
			y = columns - 1;
		return new Point(x, y);
	}

	private void RandomPoint() {
		do
			apple = new Point(random.nextInt(rows), random.nextInt(columns));
		while (apple.equals(snake.getHead()));
	}

	public void update() {
		if (apple.equals(snake.getHead())) {
			snake.grow();
			RandomPoint();
		} else {
			snake.move();
		}
	}

	public Snake getSnake() {
		return snake;
	}

	public Point getApple() {
		return apple;
	}

	public void draw(Map map, GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(0, 0, rows * SIZE, columns * SIZE);

		graphicsContext.setFill(Color.INDIANRED);
		drawPoint(map.getApple(), graphicsContext);

		graphicsContext.setFill(Color.GREENYELLOW);
		graphicsContext.fillRect(map.getApple().getX() * SIZE - 1, map.getApple().getY() * SIZE - 1, 5, 5);

		Snake snake = map.getSnake();
		graphicsContext.setFill(Color.GREENYELLOW);
		snake.getBody().forEach(point -> {
			drawPoint(point, graphicsContext);
		});

		graphicsContext.setFill(Color.BLACK);
		graphicsContext.fillRect(snake.getHead().getX() * SIZE + 2, snake.getHead().getY() * SIZE + 2, 3, 3);

		if (snake.overlapped()) {
			graphicsContext.setFill(Snake.DEAD);
			drawPoint(snake.getHead(), graphicsContext);
		}

		graphicsContext.setFill(Color.BEIGE);
		graphicsContext.setTextAlign(TextAlignment.CENTER);
		graphicsContext.setTextBaseline(VPos.CENTER);
		graphicsContext.setFont(new Font(("Verdana"), 20));
		if (!snake.overlapped())
			graphicsContext.fillText(Integer.toString(100 * snake.getBody().size()), rows * SIZE / 2, 20);
		else
			graphicsContext.fillText("DEAD", rows * SIZE / 2, 20);
	}

	private void drawPoint(Point point, GraphicsContext graphicsContext) {
		graphicsContext.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
	}

}
