package com.zwgames.pong.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.zwgames.pong.screens.GameScreen;
import com.zwgames.pong.util.Constants;

public class Paddle extends Entity {
	
	private GameScreen pong;
	private ShapeRenderer shapeRenderer;
	
	public Paddle(GameScreen pong, float x) {			
		super(x, pong.getCameraHeight()/2, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);
		this.pong = pong;
		this.shapeRenderer = new ShapeRenderer();
	}

	public void update() { 
		setBoundsPosition(); 
	}
	
	private void setBoundsPosition() {
		bounds.x = position.x;
		bounds.y = position.y;		
	}

	public void movePaddleUp() {
		if(!((position.y + bounds.height) > pong.getCameraHeight()))
			position.add(0, Constants.PADDLE_MOVE_VELOCITY);
	}
	
	public void movePaddleDown() {
		if(!((position.y) < 0))
			position.sub(0, Constants.PADDLE_MOVE_VELOCITY);
	}
	
	/**
	 * Calculates the intersection distance between the center of the paddle and where the ball hits (value between paddleHeight/2 and -paddleHeight/2)
	 * Normalizes the intersection value to be between 1 and -1
	 * Sets the angle of the ball depending of the max value of ball angle possible
	 * @param The ball that collides with the paddle
	 * @return The bounce angle of the ball in radians
	 * 
	 */
	public double getAngleWithBall(Ball ball) {
		double relativeIntersectY = (bounds.y+(bounds.height/2)) - (ball.bounds.y + (ball.bounds.height/2));	
		double normalizedRelativeIntersectionY = relativeIntersectY/(bounds.height/2);	
		double bounceAngle = normalizedRelativeIntersectionY * Constants.BALL_MAX_ANGLE;	
		
		return Math.toRadians(bounceAngle);
	}
	
	@Override
	public void render() {
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
		shapeRenderer.end();
	}
}
