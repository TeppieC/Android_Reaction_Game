package com.example.zhaorui.zhaorui_reflex.Model;

/**
 * Created by zhaorui on 9/30/15.
 */
public class Player {
    private int id;
    private int winningTimes = 0;

    public Player(int id) {
        this.id = id;
        this.winningTimes = 0;
    }

    public int getId() {
        return id;
    }

    public int getWinningTimes() {
        return winningTimes;
    }

    public void addWinningTimes() {
        this.winningTimes +=1;
    }

}
