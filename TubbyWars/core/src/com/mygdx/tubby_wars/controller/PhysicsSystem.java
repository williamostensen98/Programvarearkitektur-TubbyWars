package com.mygdx.tubby_wars.controller;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.tubby_wars.model.ControllerLogic;
import com.mygdx.tubby_wars.model.PlayerModel;
import com.mygdx.tubby_wars.model.components.PhysicsComponent;
import com.mygdx.tubby_wars.model.components.PlayerComponent;

public class PhysicsSystem extends IteratingSystem {

    private static final Family family = Family.all(PhysicsComponent.class).get();
    private ComponentMapper<PhysicsComponent> pm;


    public PhysicsSystem() {
        super(family);
        pm = ComponentMapper.getFor(PhysicsComponent.class);

    }

    /***
     * sets currentPlayer, startpos and bullet in physicsComponents
     * @param physicsEntity
     * @param player
     */
    public void setPlayer(Entity physicsEntity,PlayerModel player){
        pm.get(physicsEntity).currentPlayer = player;
        pm.get(physicsEntity).startpoint = pm.get(physicsEntity).currentPlayer.getStartPoint().scl(100);
        pm.get(physicsEntity).bullet = pm.get(physicsEntity).currentPlayer.getBullet();
    }

    /***
     * Given the startVelocity and the startpoint of the player this methos calculates the points to the trajectory of the players weapon.
     * It returns a vector with the nth point in the trajectory
     * @param physicsEntity
     * @param n
     * @param startVelocity
     * @return
     */
    public Vector2 getTrajectoryPoint(Entity physicsEntity, float n, Vector2 startVelocity){
        float t = 1 / 60f;
        float tt = t * t;
        float stepVelocityX = t * -startVelocity.x;
        float stepVelocityY = t * -startVelocity.y;
        float stepGravityX = tt * 0;
        float stepGravityY = tt * (-9.81f);

        float tpx = (Gdx.graphics.getWidth() / 2f) + n * stepVelocityX + 0.5f * (n * n + n) * stepGravityX;
        float tpy = pm.get(physicsEntity).startpoint.y + n * stepVelocityY + 0.5f * (n * n + n) * stepGravityY;

        pm.get(physicsEntity).trajectoryPoints = new Vector2(tpx,tpy);

        return pm.get(physicsEntity).trajectoryPoints;
    }

    /***
     * Draws a circle around the current player and checks iff the touchDown was within the range of that circle
     * If so it sets the flag to true
     * @param physicsEntity
     * @param screenX
     * @param screenY
     */
    void setPressed(Entity physicsEntity, int screenX, int screenY){
        pm.get(physicsEntity).pressedPosition.set(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
        pm.get(physicsEntity).wasPressed = true;
    }

    /***
     * IF the player has been pressed and is currently beeing dragged its sets the currentposition
     * to the position of the finger at the screen.
     * It then calculates the vector between the pressed position (where the player is) and where the last dragged position is.
     * The distance and angle is so calculated.
     * @param physicsEntity
     * @param screenX
     * @param screenY
     */
    void dragged(Entity physicsEntity, int screenX, int screenY){
        if(pm.get(physicsEntity).wasPressed){
            ControllerLogic.charging = true;
            pm.get(physicsEntity).currentPosition.set(screenX, Gdx.graphics.getHeight() - screenY);
            pm.get(physicsEntity).velocityVector.set(pm.get(physicsEntity).currentPosition).sub(pm.get(physicsEntity).pressedPosition);
            pm.get(physicsEntity).distance = pm.get(physicsEntity).velocityVector.len()/22;
            pm.get(physicsEntity).angle = MathUtils.atan2(pm.get(physicsEntity).velocityVector.y,pm.get(physicsEntity).velocityVector.x);
            pm.get(physicsEntity).angle %= 2 * MathUtils.PI;
        }
    }

    /***
     * When the touch is let go of this method will calculate the velocities in the different directions and will
     * set the players bullet to this so it shoots.
     * @param physicsEntity
     */
    void unPressed(Entity physicsEntity){
        if(ControllerLogic.charging) {
            float velX = (2.25f * -MathUtils.cos(pm.get(physicsEntity).angle) * pm.get(physicsEntity).distance);
            float velY = (2.25f * -MathUtils.sin(pm.get(physicsEntity).angle) * pm.get(physicsEntity).distance);
            Vector2 velvec = new Vector2(velX, velY);
            ControllerLogic.charging = false;
            pm.get(physicsEntity).wasPressed = false;
            pm.get(physicsEntity).currentPlayer.showBullet();
            pm.get(physicsEntity).bullet.b2Body.setLinearVelocity(velvec);
        }
    }

    /***
     * returns the velocityvector in physicscomponent
     * @param physicsEntity
     * @return
     */
    public Vector2 getVelocityVector(Entity physicsEntity) {
        return pm.get(physicsEntity).velocityVector;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // add Items to queue
        // bodiesQueue.add(entity);
    }

}
