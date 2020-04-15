package com.mygdx.tubby_wars.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.model.ControllerLogic;


public class Player extends Sprite {

    private World world;
    public Body b2Body;
    public TextureRegion region;

    public Weapon weapon;

    public TubbyWars game;
    private Array<Bullet> bullets;

    public boolean showBullet = false;

    private float posX, posY;

    public boolean whichplayer;

    /**
     * Creates an uninitialized sprite. The sprite will need a texture region and bounds set before it can be drawn.
     */
    public Player(World world, TubbyWars game, float posX, float posY, boolean whichplayer) {
        this.world = world;
        this.game = game;
        this.posX = posX;
        this.posY = posY;
        this.whichplayer = whichplayer;

        bullets = new Array<>();

        definePlayer();

        weapon = new Weapon(this,0.3f, 0.3f);
        Texture texture = new Texture("lala.png");
        //region = new TextureRegion(texture, 0,0,200,400);
        region = new TextureRegion(PlayScreen.atlas.findRegion("little_mario"), 0, 0, 16, 16);
        setBounds(0, 0, 0.5f, 0.7f);
        setRegion(region);
        setFlip(whichplayer, false);
    }


    /***
     * Draws the Player with the superclass draw method
     * as well as drawing the bullet(s).
     * @param batch
     */
    @Override
    public void draw(Batch batch) {
        super.draw(batch);

        for(Bullet bullet: bullets){
            if(showBullet){
                bullet.draw(game.batch);
            }
        }
        weapon.draw(game.batch);

    }

    public void update(float dt){

        if(bullets.isEmpty() && isPlayersTurn()){
            addBullet();
        }
        for(Bullet b: bullets){
            b.update(dt);
            if(b.isDestroyed()){
                bullets.removeValue(b, true);

            }
        }
        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
        weapon.update(dt);
    }

    /**
     * adds new bullet to the bullet list and initializes that bullet
     * */
    public void addBullet(){
        Bullet bullet = new Bullet(b2Body.getPosition().x, b2Body.getPosition().y, world, this);
        bullets.add(bullet);
        hideBullet();
    }


    public void showBullet(){
        showBullet = true;
    }
    public void hideBullet(){
        showBullet = false;
    }

    public boolean isPlayersTurn(){
        return (this.whichplayer == ControllerLogic.isPlayersTurn);
    }

    public Bullet getBullet(){
        if(bullets.isEmpty()){
            return null;
        }
        return bullets.get(0);
    }

    public Vector2 getStartPoint(){
        return b2Body.getPosition();
    }

    /**
     * Creates the body definition and body to the player and adds it to the world
     * */
    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(posX, posY);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.25f);
        fdef.shape = shape;
        b2Body.createFixture(fdef).setUserData(this);

    }


}
