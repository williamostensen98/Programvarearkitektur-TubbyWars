package com.mygdx.tubby_wars.view.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class MenuState implements State {

    private GameStateManager gsm;
    private Screen currentScreen;

    MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        currentScreen = gsm.game.screenFactory.getScreen("MENU");
        gsm.game.setScreen(currentScreen);

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
        currentScreen = gsm.game.screenFactory.getScreen(type);
        gsm.game.setScreen(currentScreen);
    }



    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }


}
