package com.mygdx.tubby_wars.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.tubby_wars.model.Assets;

public class MapLoader {

    private TmxMapLoader mapLoader;


    public MapLoader(TmxMapLoader mapLoader) {
        this.mapLoader = mapLoader;
    }

    /***
     * Loads and returns a map based on the number given into the method
     * @param i : map number wanted
     * @return: loaded TiledMap map
     */
    public TiledMap getMap(int i) {
        switch (i) {
            case 1:
                return mapLoader.load(Assets.map1);
            case 2:
                return mapLoader.load(Assets.map2);
            case 3:
                return mapLoader.load(Assets.map3);
            case 4:
                return mapLoader.load(Assets.map4);
            case 5:
                return mapLoader.load(Assets.map5);
            default:
                return null;
        }
    }
}

