package com.mygdx.tubby_wars.model.states;

import com.badlogic.gdx.Screen;
import com.mygdx.tubby_wars.TubbyWars;

public class GameOverState implements State {


    private GameStateManager gsm;
    private Screen currentScreen;
    private TubbyWars game = TubbyWars.getInstance();

    /***
     * This state handles when the game is over and when game is in this state the highscore screen is rendered.
     * When the menu or quit button is pressed the game goes back to Menu state.
     * @param gsm
     */
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
