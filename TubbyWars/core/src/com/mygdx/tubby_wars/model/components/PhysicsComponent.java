package com.mygdx.tubby_wars.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.tubby_wars.model.PlayerModel;
import com.mygdx.tubby_wars.view.Bullet;

public class PhysicsComponent implements Component {

    // FROM SET PLAYER
    public PlayerModel currentPlayer;
    public Vector2 startpoint;
    public Bullet bullet;

    // FROM GET TRAJECTORYPOINT
    public Vector2 trajectoryPoints;

    // FROM SET PRESSED
    public Vector2 pressedPosition = new Vector2();
    public boolean wasPressed = false;

    // FROM DRAGGED
    public Vector2 currentPosition = new Vector2();
    public Vector2 velocityVector = new Vector2();
    public float distance, angle;






}