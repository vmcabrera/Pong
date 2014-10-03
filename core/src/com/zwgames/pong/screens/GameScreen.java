package com.zwgames.pong.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.zwgames.pong.entities.Ball;
import com.zwgames.pong.entities.Player;

public class GameScreen extends AbstractScreen {

	private Game game;
	private Ball ball;
	private Player player1;
	private Player player2;

	private String textScore;

	public GameScreen(Game game) {
		this.game = game;
		player1 = new Player(this, "Left");
		player2 = new Player(this, "Right");
		ball = new Ball(this, super.getCameraWidth() / 2, super.getCameraHeight() / 2);
		textScore = player1.getScore() + " : " + player2.getScore();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		ball.render();
		player1.render();
		player2.render();

		super.batchBegin();
		drawGUI();
		super.batchEnd();
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))	
				game.setScreen(new MenuScreen(game));
	}

	public void update() {
		player1.update();
		player2.update();
		ball.update();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
	}

	public void updateScore() {
		textScore = player1.getScore() + " : " + player2.getScore();
	}

	private void drawGUI() {
		String fps = "FPS : "+ Integer.toString(Gdx.graphics.getFramesPerSecond());
		super.getFonts().visitor30.draw(super.getBatch(),textScore, super.getCameraWidth()/ 2 - (int) super.getFonts().visitor30.getBounds(textScore).width / 2, 600);
		Gdx.graphics.setTitle("Pong - " + fps);
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
}
