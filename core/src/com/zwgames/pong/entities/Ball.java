package com.zwgames.pong.entities;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zwgames.pong.screens.GameScreen;
import com.zwgames.pong.util.Constants;

public class Ball extends Entity {
	
	public enum BallState {
		BALL_IDLE, BALL_MOVING, BALL_SCORE, BALL_RESET
	}
	
	public BallState ballState;
	
	private ShapeRenderer shapeRenderer;
	private Random random;
	private GameScreen pong;
	private Paddle paddleLeft;
	private Paddle paddleRight;
	
	private Sound soundScore;
	private Sound soundCollision;
	
	private boolean collidableRight = true;
	private boolean collidableLeft = true;
	private float timeSinceRestart = 0;
	
	public Ball(GameScreen pong, float x, float y) {
		super(x, y, Constants.BALL_WIDTH, Constants.BALL_HEIGHT);
		this.pong = pong;
		this.shapeRenderer = new ShapeRenderer();
		this.random = new Random();
		
		this.ballState = BallState.BALL_IDLE;
		this.paddleLeft = pong.getPlayer1().getPaddle();
		this.paddleRight = pong.getPlayer2().getPaddle();
		
		this.soundScore = Gdx.audio.newSound(Gdx.files.internal("sounds/score.ogg"));
		this.soundCollision = Gdx.audio.newSound(Gdx.files.internal("sounds/collision.ogg"));
	}

	public void update() {	
		if(ballState.equals(BallState.BALL_IDLE))
			initBall();
		else if(ballState.equals(BallState.BALL_MOVING) && canCollide())
			collidesWith();	
		if((bounds.x < 0 || bounds.x > pong.getCameraWidth()) && ballState.equals(BallState.BALL_MOVING))
			score();

		position.add(velocity.x, velocity.y);		
		bounds.x = position.x;
		bounds.y = position.y;
	}

	private void score() {
		if(bounds.x > pong.getCameraWidth())
			pong.getPlayer1().addScore();
		else if(bounds.x < 0)
			pong.getPlayer2().addScore();
		
		soundScore.play();
		pong.updateScore();
		restart();
	}

	private void initBall() {
		timeSinceRestart += Gdx.graphics.getDeltaTime();
		if(timeSinceRestart > 0.5f) {
			ballState = BallState.BALL_MOVING;
			if(random.nextBoolean())
				velocity.set(Constants.BALL_MOVE_VELOCITY, 0);
			else
				velocity.set(-Constants.BALL_MOVE_VELOCITY, 0);	
		}
	}

	/**
	 * Checks if the ball already collided with the paddle, to prevent the ball to stay stuck inside the paddle
	 * @return If the ball can collide with the paddle
	 */
	private boolean canCollide() {
		if((position.x < paddleLeft.bounds.x || position.x > (paddleRight.bounds.x + bounds.x/2))) return false;
		if((position.y < 0 - bounds.height || position.y > pong.getCameraHeight() + bounds.height)) return false;
		return true;
	}

	/**
	 * Checks collisions with both paddles and walls
	 */
	private void collidesWith() {
		Rectangle intersection = new Rectangle();
		
		if(Intersector.intersectRectangles(this.bounds, paddleRight.bounds, intersection) && collidableRight) {	
			updateVelocityAfterCollision(paddleRight);
			velocity.x = -velocity.x;
			collidableRight = false;
			collidableLeft = true;
		}	
		else if(Intersector.intersectRectangles(this.bounds, paddleLeft.bounds, intersection) && collidableLeft) {
			velocity.x = -velocity.x;
			updateVelocityAfterCollision(paddleLeft);
			collidableRight = true;
			collidableLeft = false;
		}
		else if(position.y + Constants.BALL_RADIUS > pong.getCameraHeight() || position.y - Constants.BALL_RADIUS < 0) {
			velocity.y = -velocity.y;
			velocity.x++;
			soundCollision.play();
		}		
	}

	public void updateVelocityAfterCollision(Paddle paddle) {
		velocity.x = Constants.BALL_MOVE_VELOCITY * (float) Math.cos(paddle.getAngleWithBall(this));
		if(velocity.y == 0) 
			velocity.y = 10 * (float) -Math.sin(paddle.getAngleWithBall(this));
		else
			velocity.y = Constants.BALL_MOVE_VELOCITY * (float) -Math.sin(paddle.getAngleWithBall(this));
		
		soundCollision.play();
	}

	public void restart() {		
		ballState = BallState.BALL_IDLE;
		collidableRight = true;
		collidableLeft = true;
		timeSinceRestart = 0;
		
		position = new Vector2(pong.getCameraWidth()/2 - Constants.BALL_WIDTH/2, pong.getCameraHeight()/2 - Constants.BALL_HEIGHT/2);
		bounds = new Rectangle(pong.getCameraWidth()/2 - Constants.BALL_WIDTH/2, pong.getCameraHeight()/2 - Constants.BALL_HEIGHT/2, Constants.BALL_WIDTH, Constants.BALL_HEIGHT);
		velocity = new Vector2();
		acceleration = new Vector2();
	}

	@Override
	public void render() {
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.circle(position.x, position.y, Constants.BALL_RADIUS);
		shapeRenderer.end();
	}	
}
