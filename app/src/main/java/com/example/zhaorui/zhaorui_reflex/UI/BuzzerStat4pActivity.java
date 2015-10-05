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

public class BuzzerStat4pActivity extends ActionBarActivity {
    /*
   * This class is to show, clear and email statistics of
   * 4-Player Game Buzzer.
   * Note: If there are not enough playing times(less than 10 or 100),
   * the record will show all statistics as many as possible.
   *
   */

    private TextView[] textViews;
    private Button btnClear;
    private Button btnSend;
    private BuzzerRecordManager buzzerRecordManager;
    private Player[] playersArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer_stat4p);

        buzzerRecordManager = new BuzzerRecordManager(BuzzerStat4pActivity.this, "buz4p.sav",4);
        playersArray = buzzerRecordManager.loadBuzzerFromFile();

        textViews = new TextView[4];
        textViews[0] = (TextView)findViewById(R.id.textView4pP1);
        textViews[1] = (TextView)findViewById(R.id.textView4pP2);
        textViews[2] = (TextView)findViewById(R.id.textView4pP3);
        textViews[3] = (TextView)findViewById(R.id.textView4pP4);

        btnClear = (Button)findViewById(R.id.button4pClear);
        btnSend = (Button)findViewById(R.id.button4pSend);

        //set a listener for user pressing the clear button
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buzzerRecordManager.clearData(4);
                playersArray = buzzerRecordManager.loadBuzzerFromFile();
                textViews[0].setText("Player1 Buzzes: " + playersArray[0].getWinningTimes());
                textViews[1].setText("Player2 Buzzes: " + playersArray[1].getWinningTimes());
                textViews[2].setText("Player3 Buzzes: " + playersArray[2].getWinningTimes());
                textViews[3].setText("Player4 Buzzes: " + playersArray[3].getWinningTimes());
            }
        });


        // when press the "send email"button
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
        textViews[3].setText("Player4 Buzzes: " + playersArray[3].getWinningTimes());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buzzer_stat4p, menu);
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
