package com.mygdx.tubby_wars.model.states;

import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;

public class PlayState implements State {


    private GameStateManager gsm;
    private Screen currentScreen;
    private TubbyWars game = TubbyWars.getInstance();

    /***
     * All methods in this class is descirbed in the State interface
     * This state class takes care of rendering the playscreen and logic.
     * It also renders the Setting screen that can be reached from the play screen withput changing the state of the game.
     * @param gsm: Game state manager.
     */
    PlayState(GameStateManager gsm) {
        this.gsm = gsm;
        currentScreen = game.screenFactory.getScreen("PLAY");
    }

    @Override
    public boolean shouldChangeState(String type) {
        return type.equalsIgnoreCase("HIGHSCORE") ||
                type.equalsIgnoreCase("MENU") ;
    }

    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)){
            State state = type.equalsIgnoreCase("HIGHSCORE") ? new GameOverState(gsm): new MenuState(gsm);
            changeState(state);
        }
        currentScreen = game.screenFactory.getScreen(type);
        game.setScreen(currentScreen);
    }

    @Override
    public void changeState(State state) {
        gsm.changeState(state);
    }


}
