package com.mygdx.tubby_wars.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;

public class Bullet extends Sprite {

    private float x,y, stateTime;
    public Body b2Body;
    private World world;
    private boolean destroyed, setToDestroy, filter;

    public Bullet(float x, float y, World world, boolean filter) {
        this.x = x;
        this.y = y;
        this.filter = filter;
        this.world = world;
        destroyed = false;
        setToDestroy = false;
        stateTime = 0;

        defineBullet();

        Texture texture = Assets.getTexture(Assets.bullet);
        TextureRegion bulletRegion = new TextureRegion(texture, 0, 0, 32, 32);
        setBounds(0, 0, 0.5f, 0.5f);
        setRegion(bulletRegion);
    }

    //Checks if the bullet should be destroyed and removed from the world. If not it updates its position to follow its body.
    public void update(float dt){
        stateTime += dt;
        if(setToDestroy && !destroyed){
            world.destroyBody(b2Body);
            destroyed = true;
            stateTime = 0;
        }
        else if(!destroyed){
            setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        }

        if(hasStopped()){
            destroyBullet();
        }
    }

    void destroyBullet(){
        setToDestroy = true;
    }
    boolean isDestroyed(){ return destroyed;}

    //If ball is standing still after being shot it.
    private boolean hasStopped(){
        return (b2Body.getLinearVelocity().x == 0 && b2Body.getLinearVelocity().y == 0) && b2Body.getPosition().x != x; // also need to add to check that it is not in start state
}

    //Defines the bullets body and fixture and adds it to the world
    private void defineBullet() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.1f);
        fdef.shape = shape;
        fdef.friction = 0.2f;
        fdef.restitution = 0.3f;
        if(filter){
            fdef.filter.categoryBits = ControllerLogic.PLAYER_2 | ControllerLogic.BULLET_1 | ControllerLogic.BULLET_2;
            fdef.filter.maskBits = ControllerLogic.PLAYER_1 | ControllerLogic.GROUND_BIT;  ;
        }
        else
        {
            fdef.filter.categoryBits = ControllerLogic.PLAYER_1 | ControllerLogic.BULLET_1 | ControllerLogic.BULLET_2;    ;
            fdef.filter.maskBits = ControllerLogic.PLAYER_2 | ControllerLogic.GROUND_BIT;  ;
        }
        b2Body.createFixture(fdef).setUserData(this);
    }

    //Draws the bullet to the batch only if it has not been destroyed and the statetim is below 1.
    public void draw(Batch batch){
        if((!destroyed || stateTime < 1)){
            super.draw(batch);
        }
    }
}
