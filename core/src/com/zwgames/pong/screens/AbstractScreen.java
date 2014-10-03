package com.zwgames.pong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zwgames.pong.util.Fonts;

/**
 * AbstractScreen.java 
 * Author : artykk 
 * Date : 10/05/2014
 */
public class AbstractScreen implements Screen {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Fonts fonts;

	private float cameraWidth;
	private float cameraHeight;

	public AbstractScreen() {
		cameraWidth = Gdx.graphics.getWidth();
		cameraHeight = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		fonts = Fonts.getInstanceOf();
	}

	@Override
	public void render(float delta) {
		update();
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	}

	public void update() {
		camera.update();
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, cameraWidth, cameraHeight);
		camera.position.set(0, 0, 0);
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
	public void dispose() {}

	public void batchBegin() {
		batch.begin();
	}

	public void batchEnd() {
		batch.end();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public float getCameraWidth() {
		return cameraWidth;
	}

	public float getCameraHeight() {
		return cameraHeight;
	}

	public Fonts getFonts() {
		return fonts;
	}
}
