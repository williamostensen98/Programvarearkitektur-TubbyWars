package com.mygdx.tubby_wars.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.tubby_wars.TubbyWars;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height =576;
        config.width = 1280;
		new LwjglApplication(TubbyWars.getInstance(new connection()), config);
	}
}