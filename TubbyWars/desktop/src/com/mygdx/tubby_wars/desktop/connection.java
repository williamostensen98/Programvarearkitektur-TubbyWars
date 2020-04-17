package com.mygdx.tubby_wars.desktop;

import com.mygdx.tubby_wars.backend.IBackend;

public class connection implements IBackend {
    @Override
    public void Connect() {
        System.out.println("Connect() kjøres i desktop-mappa");
    }

    @Override
    public void printPlayers() {
        System.out.println("printPlayers() kjøres i desktop-mappa");
    }
}
