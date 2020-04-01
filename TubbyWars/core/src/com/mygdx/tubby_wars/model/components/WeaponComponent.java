package com.mygdx.tubby_wars.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class WeaponComponent implements Component {

    public Texture weaponTexture;
    public String weaponName;
    public int weaponPower;

    // tror ikke vi trenger å ha posisjon her, det kan heller bli satt for hver spiller i playerComponent

}
