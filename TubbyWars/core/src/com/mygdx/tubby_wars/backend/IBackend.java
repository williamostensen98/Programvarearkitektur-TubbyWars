package com.mygdx.tubby_wars.backend;

import java.util.List;

public interface IBackend {

    void connect();

    List<String> getTopTen();

    void addResult(String name, int score);

}

