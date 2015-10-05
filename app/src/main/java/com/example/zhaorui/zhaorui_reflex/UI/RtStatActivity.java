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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.zhaorui.zhaorui_reflex.R;
import com.example.zhaorui.zhaorui_reflex.Model.RTimerRecordManager;

public class RtStatActivity extends Activity implements View.OnClickListener{
    /*
     * The class displays a screen to show users when the
     * user wants to check the statistics they obtained
     * in Reaction Timer game mode. User has to click
     * corresponding buttons in order to check the stats
     * recorded in several aspects.
     *
     */

    private Button btnMin;
    private Button btnMax;
    private Button btnAvg;
    private Button btnMed;
    private Button btnClear;
    private Button btnEmail;
    private String FILENAME = "rtStats.sav";
    private RTimerRecordManager rtRecord = new RTimerRecordManager(this, FILENAME);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rt_stat);

        rtRecord.loadRtFromFile(); // load record from file

        btnMin = (Button)findViewById(R.id.btnMin);
        btnMax = (Button)findViewById(R.id.btnMax);
        btnAvg = (Button)findViewById(R.id.btnAvg);
        btnMed = (Button)findViewById(R.id.btnMed);
        btnClear = (Button)findViewById(R.id.btnRtClear);
        btnEmail = (Button)findViewById(R.id.btnRtEmail);

        btnMin.setOnClickListener(this);
        btnMax.setOnClickListener(this);
        btnAvg.setOnClickListener(this);
        btnMed.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnEmail.setOnClickListener(this);

        // = reactionTimes.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMin:
                Log.e("rtStatsActivity","Now choose btnMin");

                final AlertDialog.Builder alertMin=new AlertDialog.Builder(this);
                alertMin.setTitle("Minimum Reaction Time of ");
                alertMin.setMessage(
                        String.format(" All times: %d ms\n " +
                                        "The last 10 times: %d ms\n " +
                                        "The last 100 Times: %d ms",
                                rtRecord.acquireData(1),
                                rtRecord.acquireData(2),
                                rtRecord.acquireData(3)));
                alertMin.setNegativeButton("OK", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertMin.create();
                alertMin.show();
                break;

            case R.id.btnMax:
                Log.e("rtStatsActivity","Now choose btnMax");

                final AlertDialog.Builder alertMax=new AlertDialog.Builder(this);
                alertMax.setTitle("Maximum Reaction Time of ");
                alertMax.setMessage(
                        String.format(" All times: %d ms\n " +
                                "The last 10 times: %d ms\n " +
                                "The last 100 Times: %d ms",
                                rtRecord.acquireData(4),
                                rtRecord.acquireData(5),
                                rtRecord.acquireData(6)));
                alertMax.setNegativeButton("OK", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertMax.create();
                alertMax.show();
                break;

            case R.id.btnAvg:
                Log.e("rtStatsActivity","Now choose btnAvg");

                final AlertDialog.Builder alertAvg=new AlertDialog.Builder(this);
                alertAvg.setTitle("Average Reaction Time of ");
                alertAvg.setMessage(
                        String.format(" All times: %d ms\n " +
                                "The last 10 times: %d ms\n " +
                                "The last 100 Times: %d ms",
                                rtRecord.acquireData(7),
                                rtRecord.acquireData(8),
                                rtRecord.acquireData(9)));
                alertAvg.setNegativeButton("OK", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertAvg.create();
                alertAvg.show();
                break;

            case R.id.btnMed:
                Log.e("rtStatsActivity","Now choose btnMed");

                final AlertDialog.Builder alertMed=new AlertDialog.Builder(this);
                alertMed.setTitle("Median Reaction Time of ");
                alertMed.setMessage(
                        String.format(" All times: %d ms\n " +
                                "The last 10 times: %d ms\n " +
                                "The last 100 Times: %d ms",
                                rtRecord.acquireData(10),
                                rtRecord.acquireData(11),
                                rtRecord.acquireData(12)));
                alertMed.setNegativeButton("OK", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertMed.create();
                alertMed.show();
                break;

            case R.id.btnRtClear:
                //pop out an alert dialog when user tries to clear all stats
                AlertDialog.Builder alertClear=new AlertDialog.Builder(this);
                alertClear.setTitle("Attention");
                alertClear.setMessage("Are you sure to clear all stats?");
                alertClear.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete all stats
                        rtRecord.clearData();
                        rtRecord.loadRtFromFile();
                        dialog.cancel();
                    }
                });
                alertClear.setNegativeButton("No", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertClear.create();
                alertClear.show();
                break;

            case R.id.btnRtEmail:
                rtRecord.sendEmail("zhaorui@ualberta.ca");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rt_stat, menu);
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
