package com.mygdx.tubby_wars.desktop;

import com.mygdx.tubby_wars.backend.IBackend;

import java.util.List;

public class connection implements IBackend {
    @Override
    public void connect() {
        System.out.println("Connect() kjøres i desktop-mappa");
    }

    @Override
    public List<String> getTopTen() {
        System.out.println("getTopTen() kjøres i desktop-mappa");
        return null;
    }

    @Override
    public void addResult(String name, int score) {
        System.out.println("addResult() kjøres i desktop-mappa");
    }
}
