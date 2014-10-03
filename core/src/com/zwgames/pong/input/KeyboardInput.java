package com.zwgames.pong.input;

import com.badlogic.gdx.Gdx;
import com.zwgames.pong.entities.Paddle;

public class KeyboardInput {
	
	private int keyUp;
	private int keyDown;
	private Paddle paddle;
	
	public KeyboardInput(int keyUp, int keyDown, Paddle paddle) {
		this.keyUp = keyUp;
		this.keyDown = keyDown;
		this.paddle = paddle;
	}
	
	public void update() {
		keyUpListener();
		keyDownListener();
	}

	private void keyUpListener() {
		if(Gdx.input.isKeyPressed(keyUp))
			paddle.movePaddleUp();
	}

	private void keyDownListener() {
		if(Gdx.input.isKeyPressed(keyDown))
			paddle.movePaddleDown();
	}
		
}
