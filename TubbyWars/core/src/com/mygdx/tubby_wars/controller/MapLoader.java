package com.mygdx.tubby_wars.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapLoader {

    private TmxMapLoader mapLoader;

    public MapLoader(TmxMapLoader mapLoader) {
        this.mapLoader = mapLoader;
    }

    public TiledMap getMap(int i) {
        switch (i) {
            case 1:
                return mapLoader.load("tubbymap3.tmx");
            case 2:
                return mapLoader.load("tubbymap2.tmx");
            case 3:
                return mapLoader.load("tubbymap1.tmx");
            case 4:
                return mapLoader.load("tubbymap5.tmx");
            case 5:
                return mapLoader.load("tubbymap4.tmx");
            default:
                return null;
        }
    }
}

