package com.mygdx.tubby_wars.model.states;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;
    public TubbyWars game;

    /***
     * This class manages all of the states and keeps track of current state in the stack.
     * state top of the stack gets rendered.
     */
    public GameStateManager(TubbyWars game) {
        states  = new Stack<>();
        this.game = game;
    }

    public void push(State state){
        states.push(state);
    }

    private void pop(){
        states.pop();
    }

    void changeState(State state){
        pop();
        states.push(state);
    }

    public void changeScreen(String type){
        states.peek().changeScreen(type);
    }
}
