package com.zwgames.pong.entities;

import com.badlogic.gdx.Input.Keys;
import com.zwgames.pong.input.KeyboardInput;
import com.zwgames.pong.screens.GameScreen;

public class Player {

	private GameScreen pong;
	private Paddle paddle;
	private KeyboardInput keyboardInput;
	
	private String side;
	private int score;

	public Player(GameScreen gameScreen, String side) {
		this.pong = gameScreen;
		this.side = side;
		this.score = 0;
		setPaddle();
		setKeyboardInput();
	}

	private void setPaddle() {
		if(side.equals("Left"))
			paddle = new Paddle(pong, 20);
		else if(side.equals("Right"))
			paddle = new Paddle(pong, pong.getCameraWidth() - 20);
	}
	
	private void setKeyboardInput() {
		if(side.equals("Left"))
			keyboardInput = new KeyboardInput(Keys.W, Keys.S, paddle);
		else if(side.equals("Right"))
			keyboardInput = new KeyboardInput(Keys.UP, Keys.DOWN, paddle);
	}
	
	public void update() {
		keyboardInput.update();
		paddle.update();
	}

	public void render() { 
		paddle.render(); 
	}
	
	public void addScore() { 
		score++;
	}
	
	public Paddle getPaddle() {	
		return paddle; 
	}

	public int getScore() {		
		return score; 
	}
}
