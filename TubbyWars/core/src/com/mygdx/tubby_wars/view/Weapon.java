package com.mygdx.tubby_wars.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Weapon extends Sprite {

    private TextureRegion region;
    public Texture texture;
    private float posX, posY;
    private Body body;

    Weapon(Body body, float posX, float posY, TextureRegion weaponTexture) {
        this.body = body;
        this.posX = posX;
        this.posY = posY;
        region = weaponTexture;

        if(weaponTexture.getTexture().getWidth() == 512){
            setScale(1.5f,1.5f);
        }
        setBounds(body.getPosition().x - posX, body.getPosition().y - posY,0.7f, 0.4f);
        setRegion(weaponTexture);
    }

    public void update(float dt){
        setPosition(body.getPosition().x - posX, body.getPosition().y - posY);
    }


}
