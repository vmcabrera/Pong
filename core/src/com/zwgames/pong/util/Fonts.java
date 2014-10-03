package com.zwgames.pong.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {

	private static Fonts fonts = null;
	public BitmapFont visitor30;
	public FreeTypeFontGenerator myFont;

	private Fonts() {
		createTTFGenerators();
		createBitmapFonts();
		dispose();
	}

	public static Fonts getInstanceOf() {
		if(fonts == null) 
			fonts = new Fonts();
		return fonts;
	}
	
	private void createTTFGenerators() {
		myFont = new FreeTypeFontGenerator(Gdx.files.internal("fonts/visitor.ttf"));
	}
	
	@SuppressWarnings("deprecation")
	private void createBitmapFonts() {
		visitor30 = myFont.generateFont(30); // font size 30 pixels
		visitor30.setColor(Color.BLACK);
	}
	
	private void dispose() { 
		myFont.dispose(); 
	}
	
	public int getmyFont30Width(String text) { 
		return (int) fonts.visitor30.getBounds(text).width; 
	}
	
	public int getmyFont30Height(String text) {	
		return (int) fonts.visitor30.getBounds(text).height; 
	}
	
}
