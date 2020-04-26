package com.mygdx.tubby_wars.view.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;

public class MenuState implements State {

    private GameStateManager gsm;
    private Screen currentScreen;
    private TubbyWars game = TubbyWars.getInstance();

    MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        currentScreen = game.screenFactory.getScreen("MENU");
        game.setScreen(currentScreen);
    }

    @Override
    public boolean shouldChangeState(String type) {
        return type.equalsIgnoreCase("PLAY");
    }

    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)){
            changeState(new PlayState(gsm));
        }
        currentScreen = game.screenFactory.getScreen(type);
        game.setScreen(currentScreen);
    }

    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }


}
