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

import android.widget.Button;

public class ReactionTimer {
    /*
    *
    * The class handles functionality of a reaction timer.
    * (quote from eclass)
    * The game buzzer will waits between 10ms and 2000ms before
    * prompts the user to click. And it will show user briefly
    * on the latency before he clicks.
    * The class is used in ReactionTimerActivity.
    *
    */

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

    public void startTimer(MyCountdownTimer countDownTimer){
        countDownTimer.start();
        countDownTimer.setTimerStopped(false);
    }

    public int reactionTimeMeasure(MyCountdownTimer countDownTimer){
        if (!countDownTimer.isTimerStopped()) { // if player pressed too fast
            countDownTimer.cancel();
            countDownTimer.setTimerStopped(true);
            return -1;

        } else { // if player pressed button after the button shows "CLICK!"
            countDownTimer.cancel();
            countDownTimer.setTimerStopped(true);
            long tStart = countDownTimer.gettStart(); // get the start time
            long tElasped = System.currentTimeMillis() - tStart; // compute the reaction time
            return (int)tElasped;
        }
    }

}