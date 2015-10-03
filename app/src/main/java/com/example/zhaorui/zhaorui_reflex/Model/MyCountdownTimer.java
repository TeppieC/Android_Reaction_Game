package com.example.zhaorui.zhaorui_reflex.Model;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by zhaorui on 10/3/15.
 */
public class MyCountdownTimer extends CountDownTimer{
    private boolean timerStopped;
    private long tStart;
    private Button button;

    public MyCountdownTimer(Button button, long waitSeconds)
    {
        super(waitSeconds, waitSeconds);
        this.button = button;
    }

    public long gettStart() {
        return tStart;
    }

    public boolean isTimerStopped() {
        return timerStopped;
    }

    public void setTimerStopped(boolean status) {
        this.timerStopped = status;
    }

    @Override
    public void onFinish()
    {
        this.timerStopped = true;
        this.tStart = System.currentTimeMillis();
        this.button.setText("CLICK!");
        this.button.setTextColor(Color.RED);
    }

    @Override
    public void onTick(long millisUntilFinished)
    {
    }
}

