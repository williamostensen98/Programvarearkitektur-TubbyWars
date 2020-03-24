package com.mygdx.tubby_wars.model.components.Weapons;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Gun extends ApplicationAdapter implements Component {

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private Vector3 pos;

    @Override
    public void create() {

        batch = new SpriteBatch();
        texture = new Texture("gun.png");
        sprite = new Sprite(texture);
        pos = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
    }

    @Override
    public void render(){
        batch.begin();
        batch.draw(sprite,
                pos.x - sprite.getWidth() / 2,
                pos.y - sprite.getHeight() / 2);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
