package com.example.zhaorui.zhaorui_reflex.Model;

import android.content.Context;

/**
 * Created by zhaorui on 9/30/15.
 */
public class GameBuzzer {
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
                break;//////////////////////////////////////////////////////////////////////////
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
