package com.zwgames.pong.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zwgames.pong.util.Constants;
import com.zwgames.pong.util.Fonts;

public class MenuScreen extends AbstractScreen {
    
    private Game game;
    private Fonts fonts;
    private SpriteBatch batch;
    
    private Vector2 positionTextPlay;
    private Vector2 positionTextSettings;
    private Vector2 positionTextAbout;
    private Vector2 positionTextQuit;
	
    private float cameraWidth;
    private float cameraHeight;
    
    private int menuSelected;
    private boolean keyPressed = false;
    
	public MenuScreen(Game game) {
		this.game = game;
		this.fonts = super.getFonts();
		this.batch = super.getBatch();
		this.cameraWidth = super.getCameraWidth();
		this.cameraHeight = super.getCameraHeight();
		menuSelected = 0;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        
        update();
        showFpsTitle();
        
        super.batchBegin();
        drawMenu();
        drawMenuSelected();
        super.batchEnd();
	}
	
	public void update() {
		menuMovementInput();
		menuSelectInput();
	}
	
	private void menuSelectInput() {
		if(Gdx.input.isKeyPressed(Keys.ENTER))	
			if(menuSelected == 0)
				game.setScreen(new GameScreen(game));
			else if(menuSelected == 1)
				game.setScreen(new SettingsScreen());
			else if(menuSelected == 2)
				game.setScreen(new AboutScreen());
			else if(menuSelected == 3)
				Gdx.app.exit();
	}

	@Override
	public void resize(int width, int height) {
        super.resize(width, height);

		positionTextPlay = new Vector2(cameraWidth/2 - fonts.getmyFont30Width(Constants.TEXT_PLAY)/2, cameraHeight/2 + fonts.getmyFont30Height(Constants.TEXT_PLAY)*4);
		positionTextSettings = new Vector2(cameraWidth/2 - fonts.getmyFont30Width(Constants.TEXT_SETTINGS)/2, cameraHeight/2 + fonts.getmyFont30Height(Constants.TEXT_SETTINGS)*2);
		positionTextAbout = new Vector2(cameraWidth/2 - fonts.getmyFont30Width(Constants.TEXT_ABOUT)/2, cameraHeight/2);
		positionTextQuit = new Vector2(cameraWidth/2 - fonts.getmyFont30Width(Constants.TEXT_QUIT)/2, cameraHeight/2 - fonts.getmyFont30Height(Constants.TEXT_QUIT)*2);
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

	private void drawMenuSelected() {
		if(menuSelected == 0)
			fonts.visitor30.draw(batch, ">", positionTextPlay.x - fonts.getmyFont30Width(">")*2, positionTextPlay.y);
		else if(menuSelected == 1)
			fonts.visitor30.draw(batch, ">", positionTextSettings.x - fonts.getmyFont30Width(">")*2, positionTextSettings.y);
		else if(menuSelected == 2)
			fonts.visitor30.draw(batch, ">", positionTextAbout.x - fonts.getmyFont30Width(">")*2, positionTextAbout.y);
		else if(menuSelected == 3)
			fonts.visitor30.draw(batch, ">", positionTextQuit.x - fonts.getmyFont30Width(">")*2, positionTextQuit.y);
	}

	private void drawMenu() {		
        fonts.visitor30.draw(batch, Constants.TEXT_PLAY, positionTextPlay.x, positionTextPlay.y);
        fonts.visitor30.draw(batch, Constants.TEXT_SETTINGS, positionTextSettings.x, positionTextSettings.y);
        fonts.visitor30.draw(batch, Constants.TEXT_ABOUT, positionTextAbout.x, positionTextAbout.y);
        fonts.visitor30.draw(batch, Constants.TEXT_QUIT, positionTextQuit.x, positionTextQuit.y);
	}

	private void menuMovementInput() {
		if(!Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.UP)) keyPressed = false;
		if(Gdx.input.isKeyPressed(Keys.UP) && !keyPressed) {
			menuSelected = (menuSelected - 1) % 4;
			if(menuSelected == -1) menuSelected = 3;
			keyPressed = true; 
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN) && !keyPressed) {
			menuSelected = (menuSelected + 1) % 4;
			keyPressed = true;
		}
	}
	
	private void showFpsTitle() {		
		String fps = "FPS : "+Integer.toString(Gdx.graphics.getFramesPerSecond());
		Gdx.graphics.setTitle("Pong - "+fps);
	}
}
