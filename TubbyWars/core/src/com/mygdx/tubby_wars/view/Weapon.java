package com.mygdx.tubby_wars.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Weapon extends Sprite {

    public TextureRegion region;
    public Texture texture;
    public float posX, posY;
    private Body body;

    public Weapon(Body body, float posX, float posY) {
        this.body = body;
        this.posX = posX;
        this.posY = posY;

        texture = new Texture("GunsSpriteSheet.png");
        region = new TextureRegion(texture, 0, 0, 128, 128);
        setBounds(body.getPosition().x - posX, body.getPosition().y - posY,1f, 0.5f);
        setRegion(region);
    }

    public void update(float dt){
        setPosition(body.getPosition().x - posX, body.getPosition().y - posY);
    }


}
