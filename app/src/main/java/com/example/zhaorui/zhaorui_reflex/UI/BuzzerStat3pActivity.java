package com.example.zhaorui.zhaorui_reflex.UI;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhaorui.zhaorui_reflex.R;
import com.example.zhaorui.zhaorui_reflex.Model.BuzzerRecordManager;
import com.example.zhaorui.zhaorui_reflex.Model.Player;

public class BuzzerStat3pActivity extends ActionBarActivity {
    private TextView[] textViews;
    private Button btnClear;
    private Button btnSend;
    private BuzzerRecordManager buzzerRecordManager;
    private Player[] playersArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer_stat3p);

        buzzerRecordManager = new BuzzerRecordManager(BuzzerStat3pActivity.this, "buz3p.sav",3);
        playersArray = buzzerRecordManager.loadBuzzerFromFile();

        textViews = new TextView[3];
        textViews[0] = (TextView)findViewById(R.id.textView3pP1);
        textViews[1] = (TextView)findViewById(R.id.textView3pP2);
        textViews[2] = (TextView)findViewById(R.id.textView3pP3);

        btnClear = (Button)findViewById(R.id.button3pClear);
        btnSend = (Button)findViewById(R.id.button3pSend);

        //set a listener for user pressing the clear button
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buzzerRecordManager.clearData(3);
                playersArray = buzzerRecordManager.loadBuzzerFromFile();
                textViews[0].setText("Player1 Buzzes: " + playersArray[0].getWinningTimes());
                textViews[1].setText("Player2 Buzzes: " + playersArray[1].getWinningTimes());
                textViews[2].setText("Player3 Buzzes: " + playersArray[2].getWinningTimes());
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buzzerRecordManager.sendEmail("zhaorui@ualberta.ca");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        textViews[0].setText("Player1 Buzzes: " + playersArray[0].getWinningTimes());
        textViews[1].setText("Player2 Buzzes: " + playersArray[1].getWinningTimes());
        textViews[2].setText("Player3 Buzzes: " + playersArray[2].getWinningTimes());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buzzer_stat3p, menu);
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
