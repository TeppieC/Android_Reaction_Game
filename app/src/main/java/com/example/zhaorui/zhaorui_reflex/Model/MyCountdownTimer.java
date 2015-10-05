/**Copyright 2015 Zhaorui Chen

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 **/

package com.example.zhaorui.zhaorui_reflex.Model;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;

public class MyCountdownTimer extends CountDownTimer{
    /*
   *
   * This class inherits from android.os.CountDownTimer class
   * to implement a reation timer, Via overriding some of its
   * method. This class is initialized in ReactionTimer class.
   *
   */

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

