package com.example.zhaorui.zhaorui_reflex.UI;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.zhaorui.zhaorui_reflex.R;
import com.example.zhaorui.zhaorui_reflex.Model.MyCountdownTimer;
import com.example.zhaorui.zhaorui_reflex.Model.RTimerRecordManager;
import com.example.zhaorui.zhaorui_reflex.Model.ReactionTimer;

import java.util.List;

public class ReactionTimerActivity extends ActionBarActivity {

    private String FILENAME = "rtStats.sav";
    private Button button;
    private RTimerRecordManager rtRecord = new RTimerRecordManager(this, FILENAME);
    private ReactionTimer reactionTimer;
    private MyCountdownTimer countDownTimer;
    private long tStart = System.currentTimeMillis();
    private List<Integer> reactionTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);
        button = (Button)findViewById(R.id.buttonTimerClick);
        // initialize a new ReactionTimer module in charge of this activity
        reactionTimer = new ReactionTimer(button);

        instrucionDialog(); // pops up the instruction dialog
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Load from file of all previous reaction times
        reactionTimes = rtRecord.loadRtFromFile();
    }

    private void instrucionDialog() {
        final AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Try to");
        alert.setMessage("Click the screen when \"CLICK\" pops up");
        alert.setNegativeButton("Sure~", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                wholeProcess(); // start a new round
            }
        });
        alert.create();
        alert.show();
    }

    private void wholeProcess(){
        //https://androidcookbook.com/Recipe.seam;jsessionid=DF53064E03C7505C4EBF727E56E0728E?recipeId=1205
        countDownTimer = reactionTimer.generateNewTimer(); // generate a new timer for this round
        countDownTimer.start();
        countDownTimer.setTimerStopped(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!countDownTimer.isTimerStopped()) { // if player pressed too fast
                    countDownTimer.cancel();
                    countDownTimer.setTimerStopped(true);
                    complainDialog(); // pops up the complain dialog

                } else { // if player pressed button after the button shows "CLICK!"
                    countDownTimer.cancel();
                    countDownTimer.setTimerStopped(true);
                    tStart = countDownTimer.gettStart(); // get the start time
                    long tElasped = System.currentTimeMillis() - tStart; // compute the reaction time
                    reactionTimes.add((int)tElasped);
                    rtRecord.saveRTInFile(reactionTimes);
                    resultDialog((int)tElasped); // pops up the result dialog to show the result to player
                }
            }
        });
    }

    private void resultDialog(final int reactionTime) {
        final AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Your reaction time is...");
        // show result briefly to the player
        alert.setMessage(String.format("%d milliseconds!", reactionTime));
        alert.setNegativeButton("Try again", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                button.setText("WAIT");
                button.setTextColor(Color.BLACK);
                wholeProcess();
            }
        });
        alert.create();
        alert.show();
    }

    private void complainDialog() {
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Sorry");
        alertDialogBuilder.setMessage("You clicked too early");
        alertDialogBuilder.setNegativeButton("Try again", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                button.setText("WAIT");
                button.setTextColor(Color.BLACK);
                wholeProcess();
            }
        });
        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_timer, menu);
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
