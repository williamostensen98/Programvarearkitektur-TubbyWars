package com.mygdx.tubby_wars;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.MusicStateManager;
import com.mygdx.tubby_wars.model.SoundStateManager;
import com.mygdx.tubby_wars.view.LoadingScreen;
import com.badlogic.gdx.audio.Music;
import com.mygdx.tubby_wars.view.MenuScreen;
import com.mygdx.tubby_wars.view.SettingScreen;
import com.mygdx.tubby_wars.view.ShopScreen;
import com.badlogic.gdx.audio.Sound;

public class TubbyWars extends Game {

    public final static int HEIGHT = 375;
    public final static int WIDTH = 812;

    private Assets assets;
    private Engine engine;
    public SpriteBatch batch;

	public final static float V_WIDTH = 12.8f;
	public final static float V_HEIGHT = 5.76f;

  public MusicStateManager musicStateManager;
  public SoundStateManager soundStateManager;

	@Override
	public void create () {
		//Gdx.graphics.setWindowedMode(WIDTH, HEIGHT);

		assets = new Assets();
		engine = new Engine();
		batch = new SpriteBatch();

		LoadingScreen loadingScreen = new LoadingScreen(this, engine);
		setScreen(loadingScreen);
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

    //Adding music sounds
    public Music getBackgroundMusic() {return Assets.getMusic(Assets.backgroundMusic); }

    public Sound getJumpSound() {return Assets.getSound(Assets.jumpingSound); }

    public Sound getClickSound() {return Assets.getSound(Assets.clickSound); }

    public Sound getShootSound() {return Assets.getSound(Assets.hitSound); }

    public Sound getHitSound() {return Assets.getSound(Assets.shootingSound); }

    public void playMusic(Music music) {
        if (!musicStateManager.getMuteMusicState() && !music.isPlaying()) {
            music.setLooping(true);
            music.play();
        }
    }

    public void stopMusic(Music music) {
        if (music.isPlaying()) {
            music.stop();
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