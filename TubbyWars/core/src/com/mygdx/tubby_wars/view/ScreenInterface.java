package com.mygdx.tubby_wars.view;

// This is a manually made interface with the necessary functions. When creating a new view, use this!
// If we find that we need more in the future, add them here.

// this is using the factory method pattern

public interface ScreenInterface {

    // Used for one-time startup operations
    void create();

    // Used to update data that is going to be rendered
    void update(float dt);

    // Used to actually draw the elements to the screen
    void draw();

    // Should contain methods to handle input from user
    void handleinput();


    // OBS: render and dispose must be added manually (via "Generate" in IntelliJ)
}
