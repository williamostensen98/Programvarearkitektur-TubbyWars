package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.PhysicsSystem;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;

public class TrajectoryActor extends Actor {

    private TubbyWars game;
    private Texture trajectoryTexture;
    private PhysicsSystem physicsSystem;
    private Entity physicsEntity;

    TrajectoryActor(TubbyWars game, Engine engine) {
        this.game = game;
        physicsSystem = engine.getSystem(PhysicsSystem.class);
        physicsEntity = physicsSystem.getEntities().get(0);
        trajectoryTexture = Assets.getTexture(Assets.trajectory);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(!ControllerLogic.charging){
            return;
        }
        float t = 0f;
        float timeSeparation = 1f;
        game.batch.begin();
        for (int i = 0; i < 800; i+=20) {
            Vector2 vec = physicsSystem.getVelocityVector(physicsEntity);
            Vector2 trajectoryPoint = physicsSystem.getTrajectoryPoint(physicsEntity, i, vec);
            float x = trajectoryPoint.x;
            float y = trajectoryPoint.y;
            t += timeSeparation;
            game.batch.draw(trajectoryTexture, x, y, trajectoryTexture.getWidth(), trajectoryTexture.getHeight());
        }
        game.batch.end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
