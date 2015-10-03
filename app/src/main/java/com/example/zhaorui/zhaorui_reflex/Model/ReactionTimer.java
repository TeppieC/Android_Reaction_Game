package com.example.zhaorui.zhaorui_reflex.Model;

import android.widget.Button;

/**
 * Created by zhaorui on 9/30/15.
 */
public class ReactionTimer {
    private Button buttonController;

    public ReactionTimer(Button buttonController) {
        this.buttonController = buttonController;
    }

    private long generateWaitSeconds(){
        // helper function to generate a random time period for the player to wait
        return (long)(10 + (int)(Math.random() * ((2000 - 10) + 1)));
    }

    public MyCountdownTimer generateNewTimer(){
        // helper function to generate a new reaction timer for this round
        MyCountdownTimer countdownTimer = new MyCountdownTimer(this.buttonController, this.generateWaitSeconds());
        return countdownTimer;
    }

}