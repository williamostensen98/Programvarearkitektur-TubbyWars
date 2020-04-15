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
import com.mygdx.tubby_wars.model.ControllerLogic;


public class Bullet extends Sprite {

    private float x,y, stateTime;
    public Body b2Body;
    public World world;
    public boolean destroyed, setToDestroy;

    private Player player;

    public TextureRegion bulletRegion;

    public Bullet(float x, float y, World world, Player player) {
        this.x = x;
        this.y = y;
        this.world = world;
        this.player = player;
        destroyed = false;
        setToDestroy = false;

        defineBullet();
        Texture texture = new Texture("explosions.png");
        bulletRegion = new TextureRegion(texture, 0, 0, 32, 32);
        setBounds(0, 0, 0.5f, 0.5f);
        setRegion(bulletRegion);

        stateTime = 0;

    }

    /***
     * Checks if the bullet should be destroyed and removed from the world.
     * If not it updates its position to follow its body.
     * @param dt
     */
    public void update(float dt){
        stateTime += dt;
        if(setToDestroy && !destroyed){
            world.destroyBody(b2Body);
            destroyed = true;
            stateTime = 0;
            if(ControllerLogic.isPlayersTurn){ControllerLogic.isPlayersTurn = false;}
            else {ControllerLogic.isPlayersTurn = true;}


        }
        else if(!destroyed){
            setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        }

        if(hasStopped()){
            destroyBullet();
        }
    }

    public void destroyBullet(){
        setToDestroy = true;
    }
    public boolean isDestroyed(){ return destroyed;}

    /***
     * If ball is standing still after being shot it.
     * @return
     */
    public boolean hasStopped(){
        return (b2Body.getLinearVelocity().x == 0 && b2Body.getLinearVelocity().y == 0) && b2Body.getPosition().x != x; // also need to add to check that it is not in start state
}

    /***
     * Defines the bullets body and fixture and adds it to the world
     */
    public void defineBullet() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y + 2);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.1f);
        fdef.shape = shape;
        fdef.friction = 0.2f;
        fdef.restitution = 0.3f;
        /*fdef.filter.categoryBits = ControllerLogic.PLAYER_1 |
                ControllerLogic.BULLET_1 |
                ControllerLogic.BULLET_2;
        fdef.filter.maskBits = ControllerLogic.PLAYER_2 | ControllerLogic.GROUND_BIT;*/
        b2Body.createFixture(fdef).setUserData(this);
    }

    /**
     * Draws the bullet to the batch only if it has not been destroyed and the statetim is below 1.
     * @param batch
     */
    public void draw(Batch batch){
        if((!destroyed || stateTime < 1)){
            super.draw(batch);
        }
    }
}
