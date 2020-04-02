package com.mygdx.tubby_wars.model;
import com.mygdx.tubby_wars.TubbyWars;

public class MusicStateManager {
    private TubbyWars game;
    private boolean musicPlaying;

    public MusicStateManager(TubbyWars game) {
        this.game = game;
        musicPlaying =  true;
    }

    public void playMusic() {
        game.getMusic().play();
        musicPlaying = true;
    }

    public void stopMusic() {
        game.getMusic().stop();
        musicPlaying = false;
    }

    public boolean getMusicState() {
        return musicPlaying;
    }
}
