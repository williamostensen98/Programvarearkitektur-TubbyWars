package com.mygdx.tubby_wars.controller;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;
import com.mygdx.tubby_wars.view.CharacterCreationScreen;
import com.mygdx.tubby_wars.view.HighscoreScreen;
import com.mygdx.tubby_wars.view.LoadingScreen;
import com.mygdx.tubby_wars.view.MenuScreen;
import com.mygdx.tubby_wars.view.PlayScreen;
import com.mygdx.tubby_wars.view.SettingScreen;
import com.mygdx.tubby_wars.view.ShopScreen;

public class ScreenFactory {

    public static Screen getScreen(String screenType, TubbyWars game, Engine engine){
        switch (screenType){
            case "LOADING":
                return new LoadingScreen(game, engine);


            case "SETTINGS":
                return new SettingScreen(game, engine);


            case "HIGHSCORE":
                return new HighscoreScreen(game, engine);


            case "PLAY":
                return new PlayScreen(game, engine);


            case "SHOP":
                return new ShopScreen(game, engine);


            case "MENU":
                return new MenuScreen(game, engine);

            case "CREATE":
                return new CharacterCreationScreen(game);
            default:
                return null;
        }
    }
}
