package com.mygdx.tubby_wars.model.states;

import com.mygdx.tubby_wars.TubbyWars;

public class SoundStateManager {
    private TubbyWars game;
    private boolean mute;

    public SoundStateManager(TubbyWars game) {
        this.game = game;
        this.mute = false;
    }

    public void muteSound() {
        mute = true;
    }

    public void unmuteSound() {
        mute = false;
    }

    public Boolean getMuteSoundState() {
        return mute;
    }
}