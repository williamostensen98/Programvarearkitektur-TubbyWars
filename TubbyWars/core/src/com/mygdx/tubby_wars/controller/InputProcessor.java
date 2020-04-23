package com.mygdx.tubby_wars.controller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter {

    private PhysicsSystem physicsSystem;
    private Entity physicsEntity;


    public InputProcessor(PhysicsSystem physicsSystem) {
        this.physicsSystem = physicsSystem;
        physicsEntity = physicsSystem.getEntities().get(0);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        physicsSystem.setPressed(physicsEntity, screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        physicsSystem.unPressed(physicsEntity);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        physicsSystem.dragged(physicsEntity, screenX, screenY);
        return false;
    }
}
