package com.zwgames.pong.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	public Vector2 position;
	public Vector2 velocity;
	public Vector2 acceleration;
	public Rectangle bounds;
	
	public Entity(float x, float y, float width, float height) {
		this.position = new Vector2(x - width/2, y - height/2);
		this.bounds = new Rectangle(x - width/2, y - height/2, width, height);
		this.velocity = new Vector2();
		this.acceleration = new Vector2();	
	}
	
	public abstract void render();
}
