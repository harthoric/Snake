package com.harthoric.snake;

import com.harthoric.snake.game.MainGameLoop;
import com.harthoric.snake.render.Map;
import com.harthoric.snake.util.KeyHandler;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Core extends Application {

	private static final int WIDTH = 400, HEIGHT = 400;

	private Map map;

	private MainGameLoop loop;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		var root = new StackPane();
		var canvas = new Canvas(getWidth(), getHeight());

		map = new Map(getWidth(), getHeight());
		map.draw(map, canvas.getGraphicsContext2D());

		loop = new MainGameLoop(map, canvas);

		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(new KeyHandler(map));

		root.getChildren().add(canvas);

		var scene = new Scene(root);

		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake");
		primaryStage.setOnCloseRequest(e -> System.exit(0));
		primaryStage.setScene(scene);
		primaryStage.show();

		(new Thread(loop)).start();
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

}
