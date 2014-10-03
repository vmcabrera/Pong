package com.zwgames.pong.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zwgames.pong.Pong;

public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Pong";
		cfg.useGL30 = false;
		cfg.width = 960;
		cfg.height = 640;
		cfg.vSyncEnabled = true;
		cfg.addIcon("images/ic_launcher.png", Files.FileType.Internal);
		
		new LwjglApplication(new Pong(), cfg);		
	}
}
