package com.mygdx.tubby_wars;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.view.LoadingScreen;
import com.mygdx.tubby_wars.view.SettingScreen;


public class TubbyWars extends Game {

	public final static int HEIGHT = 375;
	public final static int WIDTH = 812;

	private Engine engine;

	SpriteBatch batch;
	Texture img;

	private Assets assets;

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(WIDTH, HEIGHT);

		assets = new Assets();
		engine = new Engine();

		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);

		this.setScreen(new SettingScreen(this, engine));
	}

	@Override
	public void render () {
		GL20 gl = Gdx.gl;
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
	// added comment to test closing issue
}
