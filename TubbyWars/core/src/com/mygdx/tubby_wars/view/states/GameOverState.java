package com.mygdx.tubby_wars.view.states;

import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;

public class GameOverState implements State {


    private GameStateManager gsm;
    private Screen currentScreen;
    private TubbyWars game = TubbyWars.getInstance();

    GameOverState(GameStateManager gsm) {

        this.gsm = gsm;
        currentScreen = game.screenFactory.getScreen("HIGHSCORE");
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
        currentScreen = game.screenFactory.getScreen(type);
        game.setScreen(currentScreen);
    }



    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }




}
