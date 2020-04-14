package com.mygdx.tubby_wars.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.Physics;
import com.mygdx.tubby_wars.model.ControllerLogic;

public class TrajectoryActor extends Actor {

    private TubbyWars game;
    private Physics physics;

    private Texture trajectoryTexture;
    private float timeSeparation = 1f;

    public TrajectoryActor(TubbyWars game, Physics physics) {
        this.game = game;
        this.physics = physics;
        // INITIALIZES SPRITES AND TEXTURES
        trajectoryTexture = new Texture("white-circle.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        //  TODO DRAW WEAPON SPRITE HERE
        /***
         * Checks if the weapon is actually charging(i.e. being dragged).
         */
        if(!ControllerLogic.charging){
            return;
        }

        float t = 0f;
        float timeSeparation = this.timeSeparation;
        game.batch.begin();
        for (int i = 0; i < 800; i+=20) {
            Vector2 vec = physics.getVelocityVector();
            Vector2 trajectoryPoint = physics.getTrajectoryPoint(i, vec);
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
