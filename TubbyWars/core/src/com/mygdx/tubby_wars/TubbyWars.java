package com.mygdx.tubby_wars;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.tubby_wars.backend.IBackend;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.MusicStateManager;
import com.mygdx.tubby_wars.view.LoadingScreen;
import com.badlogic.gdx.audio.Music;
import com.mygdx.tubby_wars.view.MenuScreen;
import com.mygdx.tubby_wars.view.SettingScreen;
import com.mygdx.tubby_wars.view.ShopScreen;

public class TubbyWars extends Game {

	public final static int HEIGHT = 375;
	public final static int WIDTH = 812;

	SpriteBatch batch;
	Texture img;

	private Engine engine;
	private Assets assets;
	public IBackend IBack;
	public MusicStateManager musicStateManager;


	public TubbyWars(IBackend IBack){
			this.assets = new Assets();
			this.engine = new Engine();
			this.IBack=IBack;


			this.IBack.Connect();
			this.IBack.printPlayers();


	}

	@Override
	public void create () {

		Gdx.graphics.setWindowedMode(WIDTH, HEIGHT);

		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);

		this.setScreen(new LoadingScreen(this, engine));
		this.musicStateManager = new MusicStateManager(this);
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

	public Music getMusic() {
		return Assets.getMusic(Assets.backgroundMusic);
	}

	public void playMusic(Music mus){
		Music music = getMusic();
		if(musicStateManager.getMusicState() && !music.isPlaying()){
			music.play();
			music.setLooping(true);
		}
	}


}
