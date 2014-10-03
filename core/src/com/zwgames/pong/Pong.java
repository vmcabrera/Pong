package com.zwgames.pong;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.zwgames.pong.screens.MenuScreen;

public class Pong extends Game {

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		setScreen(new MenuScreen(this));
	}
}
