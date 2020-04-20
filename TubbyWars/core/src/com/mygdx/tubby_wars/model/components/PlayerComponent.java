package com.mygdx.tubby_wars.model.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class PlayerComponent implements Component{

    public String playerName;
    public int health;
    public int score;
    public Texture characterBody;

    public Entity weapon;

    // hvis vi skal ha egen weapon component, setter vi selvsagt denne der.
    public float weaponDamage = (float)1.4;


    // NBNB: FORSLAG TIL HVORDAN VI KAN FÅ INN LITT MER MVC FUNKSJONALITET
    public int posX;
    public int posY;
    public boolean whichturn; // litt rart navnvalg kanskje?

    // også lager vi funksjoner som gjør endringer på disse i playerSystem.
    // i playScreen når vi oppretter en player, bør vi daa gå via world, som oppretter playerSystem og component
    // deretter blir Player opprettet derifra, eller der det passer.
    // En slik metode vil jeg tro vil være mer korrekt siden vi bruker MVC





    // skal vi ha en egen for cææsh?
    // public int coins;


}
