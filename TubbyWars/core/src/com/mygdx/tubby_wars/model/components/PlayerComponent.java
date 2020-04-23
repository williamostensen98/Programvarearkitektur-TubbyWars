package com.mygdx.tubby_wars.model.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.tubby_wars.view.Bullet;

public class PlayerComponent implements Component{

    public String playerName;
    public int health;
    public int score;
    public Texture characterBody;

    public Entity weapon;

    public float weaponDamage = (float)1.4;
    public Texture weaponTexture;

    public TextureRegion region;

    public Array<Bullet> bullets;

}
