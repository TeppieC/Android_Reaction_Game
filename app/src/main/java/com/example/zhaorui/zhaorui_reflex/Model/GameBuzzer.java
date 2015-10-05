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

import android.content.Context;

public class GameBuzzer {
    /*
   *
   * The class handles functionality of a gameshow buzzer.
   * (quote from eclass)
   * The game buzzer is to aid friends playing  a game that needs to
   * determine who has the answer first.
   * When a question is asked of the friends, the first one to press
   * their button should be notified that they were indeed first.
   * And the class is used in BuzzerActivity.
   *
   */

    private Context context;
    private int numPlayers;

    private Player[] playersArray;
    private String FILENAME;
    private BuzzerRecordManager buzzerRecordManager;

    public GameBuzzer(Context context, int numPlayers) {
        this.numPlayers = numPlayers;
        switch (numPlayers) {
            case 2:
                // initialize FILENAME based on number of players
                this.FILENAME = "buz2p.sav";
                // initialize RecordManager based on number of players
                this.buzzerRecordManager =
                        new BuzzerRecordManager(context, this.FILENAME, 2);
                break;
            case 3:
                this.FILENAME = "buz3p.sav";
                this.buzzerRecordManager =
                        new BuzzerRecordManager(context, this.FILENAME, 3);
                break;
            case 4:
                this.FILENAME = "buz4p.sav";
                this.buzzerRecordManager =
                        new BuzzerRecordManager(context, this.FILENAME, 4);
                break;
        }
        // initialize playerArray based on the number of players
        playersArray = new Player[numPlayers];
    }

    public void addPlayersWinningTimes(int playerIndex){
        playersArray[playerIndex].addWinningTimes();
    }

    public int getPlayersWinningTimes(int playerIndex){
        return playersArray[playerIndex].getWinningTimes();
    }

    public Player[] loadBuzzerFromFile(){
        return buzzerRecordManager.loadBuzzerFromFile();
    }

    public void saveBuzzerInFile(Player[] playersArray){
        buzzerRecordManager.saveBuzzerInFile(playersArray);
    }
}
