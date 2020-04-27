package com.mygdx.tubby_wars.model.states;

import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;


public class LoadingState implements State{


    private GameStateManager gsm;
    private Screen currentScreen;
    private TubbyWars game = TubbyWars.getInstance();

    /***
     * All methods in this class is descirbed in the State interface
     * This state renderes the loading screen and changes when all assets has been fully loaded.
     * @param gsm: Game state manager
     */
    public LoadingState(GameStateManager gsm) {
        this.gsm = gsm;
        currentScreen = game.screenFactory.getScreen("LOADING");
        game.setScreen(currentScreen);

    }

    @Override
    public void changeState(State state) {
        gsm.changeState(state);
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
}
