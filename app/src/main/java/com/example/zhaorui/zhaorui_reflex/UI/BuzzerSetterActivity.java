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

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.zhaorui.zhaorui_reflex.R;

public class BuzzerSetterActivity extends ActionBarActivity {
    /*
     * The class is to let user to choose how many players will
     * play in the gameshow buzzer game. It will offer 3 options:
     * 2players, 3players, 4players. And will pass the number of
     * players through intent to BuzzerActivity.
     *
     */

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
