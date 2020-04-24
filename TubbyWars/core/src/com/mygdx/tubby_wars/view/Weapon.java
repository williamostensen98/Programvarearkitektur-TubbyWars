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

    public Weapon(Body body, float posX, float posY, TextureRegion weaponTexture) {
        this.body = body;
        this.posX = posX;
        this.posY = posY;

        region = weaponTexture;

        //texture = new Texture("GunsSpriteSheet.png");
        // TODO KANSKJE BARE BRUKE GUNSSPRITEPNG FOR VÅPEN, FIKSE DET SENERE
        // Texture texture = weaponTexture;







        //region = new TextureRegion(weaponTexture, 0, 0, weaponTexture.getWidth(), weaponTexture.getHeight());
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
