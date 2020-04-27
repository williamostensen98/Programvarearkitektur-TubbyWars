package com.mygdx.tubby_wars;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.tubby_wars.backend.IBackend;
import com.mygdx.tubby_wars.controller.ScreenFactory;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.states.MusicStateManager;
import com.mygdx.tubby_wars.model.states.SoundStateManager;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.tubby_wars.model.states.GameStateManager;
import com.mygdx.tubby_wars.model.states.LoadingState;

public class TubbyWars extends Game {

    public final static int HEIGHT = 375;
    public final static int WIDTH = 812;

	public SpriteBatch batch;
	public GameStateManager gsm;
	private static TubbyWars INSTANCE;

    public final static float V_WIDTH = 12.8f;
    public final static float V_HEIGHT = 5.76f;

	private Engine engine;
	private Assets assets;
	public IBackend backendConn;
	public MusicStateManager musicStateManager;
    public SoundStateManager soundStateManager;
    public ScreenFactory screenFactory;

    /**
     * SINGLETON IMPLEMENTATION OF THE GAME APPLICATION
     * ALLOWS FOR ANDROID LAUNCHER TO INSTANTIATE THE TUBBYWARS CLASS
     * @param backendConn: CONNECTION TO HIGHSCORE LIST BACKEND
     * @return TUBBYWARS INSTANCE
     */
    public static TubbyWars getInstance(IBackend backendConn) {
        if (INSTANCE == null) {
            INSTANCE = new TubbyWars(backendConn);
        }
        return INSTANCE;
    }

    /**
     * SINGLETON IMPLEMENTATION WITHOUT BACKEND CONNECTION
     * ALLOWS ALL CLASSES A GLOBAL ACCESS POINT TO THE TUBBYWARS INSTANCE
     * @return TUBBYWARS INSTANCE
     */
    public static TubbyWars getInstance() {
        return INSTANCE;
    }
    private TubbyWars(IBackend backendConn){
			this.assets = new Assets();
			this.engine = new Engine();
			this.backendConn = backendConn;
            this.backendConn.connect();
	}

    /**
     * CREATES THE GAMES SPRITEBATCH AS WELL AS THE GAME STATE MANAGER AND THE SCREEN FACTORY
     */
	@Override
	public void create () {
		assets = new Assets();
		engine = new Engine();
		batch = new SpriteBatch();
        screenFactory = new ScreenFactory();
        gsm = new GameStateManager(this);
        gsm.push(new LoadingState(gsm));

		this.musicStateManager = new MusicStateManager(this);
        this.soundStateManager = new SoundStateManager(this);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

    public SpriteBatch getBatch() { return batch; }

    //Adding music sounds TODO: Flytte alt med musikk?
    public void playMusic(Music music) {
        if (!musicStateManager.getMuteMusicState() && !music.isPlaying()) {
            music.setLooping(true);
            music.play();
        }
    }

    public void muteMusic(Music music) {
        if (music.isPlaying()) {
            music.pause();
            musicStateManager.muteMusic();
        }
    }

    public void unmuteMusic(Music music) {
        musicStateManager.unmuteMusic();
        if (!music.isPlaying()) {
            music.play();
            music.setVolume(0.3f);
            music.isLooping();
        }
    }

    public void playSound(Sound sound) {
        if (!soundStateManager.getMuteSoundState()) {
            sound.play();
            sound.setVolume(1,0.3f);
        }
    }
}