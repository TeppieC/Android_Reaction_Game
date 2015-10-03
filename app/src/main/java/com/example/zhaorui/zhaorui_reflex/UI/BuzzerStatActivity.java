package com.example.zhaorui.zhaorui_reflex.UI;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.zhaorui.zhaorui_reflex.R;

public class BuzzerStatActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer_stat);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buzzer_stat, menu);
        return true;
    }

    public void start2pStatActivity(View view){
        Intent i = new Intent(BuzzerStatActivity.this, BuzzerStat2pActivity.class);
        startActivity(i);
    }

    public void start3pStatActivity(View view){
        Intent i = new Intent(BuzzerStatActivity.this, BuzzerStat3pActivity.class);
        startActivity(i);
    }

    public void start4pStatActivity(View view){
        Intent i = new Intent(BuzzerStatActivity.this, BuzzerStat4pActivity.class);
        startActivity(i);
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
