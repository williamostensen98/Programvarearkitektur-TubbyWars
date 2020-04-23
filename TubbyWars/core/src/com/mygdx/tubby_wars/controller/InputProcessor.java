package com.mygdx.tubby_wars.controller;

import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter {


    public Physics physics;

    public InputProcessor(Physics physics) {
        this.physics = physics;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        physics.pressed(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        physics.unPressed();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        physics.dragged(screenX, screenY);
        return false;
    }
}
