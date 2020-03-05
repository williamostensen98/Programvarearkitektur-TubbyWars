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



}
