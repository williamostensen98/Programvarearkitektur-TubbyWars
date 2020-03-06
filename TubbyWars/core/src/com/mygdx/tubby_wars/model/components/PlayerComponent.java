package com.mygdx.tubby_wars.model.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class PlayerComponent implements Component {

    public String playerName;
    public int health;

    public int score;

    // skal vi ha en egen for cææsh?
    // public int coins;

    public boolean isYourTurn = false;

    public Vector3 aimArrow = new Vector3(0,0,0);

    public Vector3 position;

    public boolean hasFired = false;

    // x and y is start pos of the bullet, and we use z as a in y direction, aka gravity.
    public Vector3 bulletPos = new Vector3(100,75,0);

    // after using up all shots, switch to the other player. e.g. shotcounter == 3  => endturn
    public int shotCounter = 0;

}
