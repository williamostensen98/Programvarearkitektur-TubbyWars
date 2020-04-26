package com.mygdx.tubby_wars.model.states;

import com.badlogic.gdx.Screen;

public class GameOverState implements State {


    GameStateManager gsm;
    private Screen currentScreen;


    GameOverState(GameStateManager gsm) {

        this.gsm = gsm;
        currentScreen = gsm.game.screenFactory.getScreen("HIGHSCORE");
    }

    @Override
    public boolean shouldChangeState(String type) {
        return type.equalsIgnoreCase("MENU");
    }

    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)){
            changeState(new MenuState(gsm));
        }
        currentScreen = gsm.game.screenFactory.getScreen(type);
        gsm.game.setScreen(currentScreen);
    }



    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }




}
