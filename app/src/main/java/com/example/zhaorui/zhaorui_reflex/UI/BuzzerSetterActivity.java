package com.example.zhaorui.zhaorui_reflex.UI;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.zhaorui.zhaorui_reflex.R;

public class BuzzerSetterActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer_setter);
    }

    public void start2pBuzzer(View view){
        Intent i = new Intent(BuzzerSetterActivity.this, BuzzerActivity.class);
        i.putExtra("strNumPlayers", String.valueOf(2));
        startActivity(i);
    }

    public void start3pBuzzer(View view){
        Intent i = new Intent(BuzzerSetterActivity.this, BuzzerActivity.class);
        i.putExtra("strNumPlayers",String.valueOf(3));
        startActivity(i);
    }

    public void start4pBuzzer(View view){
        Intent i = new Intent(BuzzerSetterActivity.this, BuzzerActivity.class);
        i.putExtra("strNumPlayers",String.valueOf(4));
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buzzer_setter, menu);
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
