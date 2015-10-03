package com.example.zhaorui.zhaorui_reflex.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.zhaorui.zhaorui_reflex.R;
import com.example.zhaorui.zhaorui_reflex.Model.GameBuzzer;
import com.example.zhaorui.zhaorui_reflex.Model.Player;

public class BuzzerActivity extends Activity implements View.OnClickListener {
    private GameBuzzer gameBuzzer;
    private Button[] btnArray;
    private Player[] playersArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create a new layout param for the buttons
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // obtain user input from the intent passed from buzzerSetter activity
        Intent intent = getIntent();
        String strNumPlayers = intent.getStringExtra("strNumPlayers");
        int numPlayers = Integer.parseInt(strNumPlayers);

        gameBuzzer = new GameBuzzer(BuzzerActivity.this, numPlayers);
        btnArray = new Button[numPlayers];

        switch (numPlayers){
            case 2:
                // create corresponding number of buttons for each player
                for (int i = 0; i < numPlayers; i++) {
                    btnArray[i] = new Button(this);
                    btnArray[i].setId(i);
                    btnArray[i].setOnClickListener(this);
                    String btnLabel = "Player " + String.valueOf(i + 1);
                    btnArray[i].setText(btnLabel);
                    btnArray[i].setTextSize(50);
                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp2.weight = 1;
                    btnArray[i].setLayoutParams(lp2);
                    linearLayout.addView(btnArray[i]);
                }
                break;
            case 3:
                // create corresponding number of buttons for each player
                for (int i = 0; i < numPlayers; i++) {
                    btnArray[i] = new Button(this);
                    btnArray[i].setId(i);
                    btnArray[i].setOnClickListener(this);
                    String btnLabel = "Player " + String.valueOf(i + 1);
                    btnArray[i].setText(btnLabel);
                    btnArray[i].setTextSize(50);
                    LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp3.weight = 1;
                    btnArray[i].setLayoutParams(lp3);
                    linearLayout.addView(btnArray[i]);
                }
                break;
            case 4:
                // create corresponding number of buttons for each player
                for (int i = 0; i < numPlayers; i++) {
                    btnArray[i] = new Button(this);
                    btnArray[i].setId(i);
                    btnArray[i].setOnClickListener(this);
                    String btnLabel = "Player " + String.valueOf(i + 1);
                    btnArray[i].setText(btnLabel);
                    btnArray[i].setTextSize(50);
                    LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp4.weight = 1;
                    btnArray[i].setLayoutParams(lp4);
                    linearLayout.addView(btnArray[i]);
                }
                break;
        }
        // apply the layout to the activity
        setContentView(linearLayout);

    }

    @Override
    protected void onStart() {
        super.onStart();
        playersArray = gameBuzzer.loadBuzzerFromFile();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case 0:
                playersArray[0].addWinningTimes();
                resultDialog("Player 1");
                break;
            case 1:
                playersArray[1].addWinningTimes();
                resultDialog("Player 2");
                break;
            case 2:
                playersArray[2].addWinningTimes();
                resultDialog("Player 3");
                break;
            case 3:
                playersArray[3].addWinningTimes();
                resultDialog("Player 4");
                break;
        }
    }

    //////////////////////////////try this function in GameBuzzer class
    private void resultDialog(String winner) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("The winner is...");
        // give info briefly to the user
        alert.setMessage(winner);
        alert.setPositiveButton("Try again", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameBuzzer.saveBuzzerInFile(playersArray);
                dialog.cancel();
            }
        });
        alert.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buzzer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
