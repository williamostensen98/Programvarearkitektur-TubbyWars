package com.mygdx.tubby_wars.view;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.controller.PlayerSystem;
import com.mygdx.tubby_wars.model.Assets;
import com.mygdx.tubby_wars.model.ControllerLogic;
import com.mygdx.tubby_wars.model.PlayerModel;
import com.mygdx.tubby_wars.model.components.PlayerComponent;


public class PlayerTwo extends PlayerModel {

    public Weapon weapon;
    private Healthbar healthbar;
    private boolean timeToRedefine;

    // ASHLEY
    private Entity playerEntity;
    private ComponentMapper<PlayerComponent> pm;
    private PlayerSystem ps;

    private Sound hitSound;

    //Creates an uninitialized sprite. The sprite will need a texture region and bounds set before it can be drawn.
    public PlayerTwo(World world, float posX, float posY, Entity playerEntity, Engine engine) {
        super(world, posX, posY, playerEntity, engine);
        definePlayer();
        ps = engine.getSystem(PlayerSystem.class);
        timeToRedefine = false;
        this.playerEntity = playerEntity;

        ps.initializeNewBullets(playerEntity);
        weapon = new Weapon(b2Body,1.1f, 0.15f, ps.getWeaponTexture(playerEntity));
        weapon.flip(true, false);

        ps.createTextureRegion(playerEntity);
        setBounds(0, 0, 1f, 1.4f);
        setRegion(ps.getTextureRegion(playerEntity));
        setFlip(true, false);
        flip(true,false);

        hitSound = Assets.getSound(Assets.hitSound);
        healthbar = new Healthbar(b2Body, playerEntity);
    }

    //Draws the Player with the superclass draw method as well as drawing the bullet(s).
    @Override
    public void draw(Batch batch) {
        super.draw(batch);

        for(Bullet bullet: ps.getBullets(playerEntity)){
            if(showBullet){
                bullet.draw(game.batch);
            }
        }
        weapon.draw(game.batch);
        healthbar.draw(game.batch);
    }

    @Override
    public void update(float dt) {
        if(ps.getBullets(playerEntity).size > 1){
            getBullet().destroyBullet();
        }

        if(timeToRedefine){
            redefinePlayer();
        }

        if(ps.getBullets(playerEntity).isEmpty() && super.isPlayersTurn()){
            addBullet();
        }

        for(Bullet b: ps.getBullets(playerEntity)){
            b.update(dt);
            if(b.isDestroyed()){
                ps.getBullets(playerEntity).removeValue(b, true);
            }
        }

        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        weapon.update(dt);
        healthbar.update(dt);
        if (healthbar.getHealthDecrease()) {
            game.playSound(hitSound);
            healthbar.setHealthDecrease();
        }
    }

    @Override
    public void redefinePlayer() {
        world.destroyBody(b2Body);
        definePlayer();
        timeToRedefine = false;
        addBullet();
    }

    @Override
    public void setRedefine() {
        timeToRedefine = true;
    }

    @Override
    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(this.posX, this.posY);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = this.world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.7f);
        fdef.shape = shape;
        fdef.friction = 0.8f;
        fdef.filter.categoryBits = ControllerLogic.BULLET_2 | ControllerLogic.PLAYER_2;
        fdef.filter.maskBits = ControllerLogic.BULLET_1 | ControllerLogic.GROUND_BIT;
        b2Body.createFixture(fdef).setUserData(this);
    }

    @Override
    public Bullet getBullet() {
        if(ps.getBullets(playerEntity).isEmpty()){
            return null;
        }
        return ps.getBullets(playerEntity).get(0);
    }

    @Override
    public void addBullet() {
        Bullet bullet = new Bullet(b2Body.getPosition().x, b2Body.getPosition().y, world, true);
        ps.getBullets(playerEntity).add(bullet);
        hideBullet();
    }

}
