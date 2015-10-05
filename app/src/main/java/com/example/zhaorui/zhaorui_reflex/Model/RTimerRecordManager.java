/**Copyright 2015 Zhaorui Chen, Joshua Campbell

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
import android.content.Intent;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class RTimerRecordManager extends RecordManager {
  /*
   * This class is the class inherit from RecordManager
   * intended to save and load statistics obtained from the
   * ReactionTimer, and to send emails based on the stats.
   * The class is used in ReactionTimerActivity and RtStatActivity.
   *
   * Two methods below are modified based on Joshua Campbell's lonelyTwitter Program, 2015
   *
   */

    private List<Integer> reactionTimes;

    public RTimerRecordManager(Context context, String fileName) {
        super(context, fileName);
    }

    // the following method is modified based on Joshua Campbell's lonelyTwitter, 2015
    public List<Integer> loadRtFromFile() {
        List<Integer> reactionTimes;
        try {
            FileInputStream fis = context.openFileInput(fileName); /////////////////
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google.gson.googlecode.com/svn
            Type listType = new TypeToken<List<Integer>>() {
            }.getType();
            reactionTimes = gson.fromJson(in, listType);
            //Log.e("buzzerActivity", "In loadFromFile1, Id is "+String.valueOf(playersArray[1].getId()));
            return reactionTimes;
        } catch (FileNotFoundException e) {
            reactionTimes = new ArrayList<>();
            return reactionTimes;
            //Log.e("buzzerActivity", "In loadFromFile2, Id is "+String.valueOf(playersArray[1].getId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // the following method is modified based on Joshua Campbell's lonelyTwitter, 2015
    public void saveRTInFile(List<Integer> reactionTimes) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName,
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);//
            Gson gson = new Gson();
            gson.toJson(reactionTimes, writer); //save data
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearData(){
        // this function is used to clear all record data
        reactionTimes = new ArrayList<>();
        this.saveRTInFile(reactionTimes);
    }

    public int acquireData(int mode) {
        this.reactionTimes = this.loadRtFromFile();

        int numAllTimes = reactionTimes.size();
        List<Integer> rtTimesAll = reactionTimes;
        List<Integer> rtTimes10;
        List<Integer> rtTimes100;
        int output = 0;

        if (numAllTimes <= 10) { // if there are no more than 10 times o play
            rtTimes10 = rtTimesAll;
            rtTimes100 = rtTimesAll;
        } else {
            rtTimes10 = reactionTimes.subList(numAllTimes - 10, numAllTimes);
            if (numAllTimes <= 100) { // if there are 10~99 times of play
                rtTimes100 = rtTimesAll;
            } else { // if there are over 100 times of play
                rtTimes100 = reactionTimes.subList(numAllTimes - 100, numAllTimes);
            }
        }

        switch (mode) {
            case 1:
                //obtain min rtime of all times
                try {
                    output = Collections.min(rtTimesAll);
                }catch (NoSuchElementException e){
                    // if user hasn't played in reactionTimer yet or stats has been cleared
                    output = 0;
                }
                break;
            case 2:
                //obtain min rtime of last 10 times
                try {
                    output = Collections.min(rtTimes10);
                }catch (NoSuchElementException e){
                    output = 0;
                }
                break;
            case 3:
                //obtain min rtime of last 10 times
                try {
                    output = Collections.min(rtTimes100);
                }catch (NoSuchElementException e){
                    output = 0;
                }
                break;
            case 4:
                //obtain max rtime of all times
                try {
                    output = Collections.max(rtTimesAll);
                }catch (NoSuchElementException e){
                    output = 0;
                }
                break;
            case 5:
                //obtain max rtime of last 10 times
                try {
                    output = Collections.max(rtTimes10);
                }catch (NoSuchElementException e){
                    output = 0;
                }
                break;
            case 6:
                //obtain max rtime of last 10 times
                try {
                    output = Collections.max(rtTimes100);
                }catch (NoSuchElementException e){
                    output = 0;
                }
                break;
            case 7:
                //obtain average rtime of all times
                output = avgOfList(rtTimesAll);
                break;
            case 8:
                //obtain average rtime of last 10 times
                output = avgOfList(rtTimes10);
                break;
            case 9:
                //obtain average rtime of last 10 times
                output = avgOfList(rtTimes100);
                break;
            case 10:
                //obtain median rtime of all times
                Collections.sort(rtTimesAll);
                try {
                    output = rtTimesAll.get(rtTimesAll.size() / 2);
                }catch (IndexOutOfBoundsException e){
                    output = 0;
                }
                break;
            case 11:
                //obtain median rtime of last 10 times
                Collections.sort(rtTimes10);
                try {
                    output = rtTimes10.get(rtTimes10.size() / 2);
                }catch (IndexOutOfBoundsException e){
                    output = 0;
                }
                break;
            case 12:
                //obtain median rtime of last 10 times
                Collections.sort(rtTimes100);
                try {
                    output = rtTimes100.get(rtTimes100.size() / 2);
                }catch (IndexOutOfBoundsException e){
                    output = 0;
                }
                break;
        }
        return output;
    }

    private int avgOfList(List<Integer> list)
    { // helper function to compute the average value of a integer list
        int sum = 0;
        for(int i=0; i<list.size(); i++)
        {
            sum+=list.get(i);
        }
        try {
            return sum/list.size();
        }catch (ArithmeticException e){
            // if size of the list is 0
            return 0;
        }
    }

    public void sendEmail(String reciever){
        // this function is to send emails to a selected reciever as given in the parameter
        for (int i=1;i<=12;i++){
            this.acquireData(i); // obtain statistics from file
        }
        Intent stats = new Intent(Intent.ACTION_SENDTO);
        stats.setData(Uri.parse("mailto:" + reciever));
        stats.putExtra(Intent.EXTRA_SUBJECT, "My Reaction Timer Statistics");

        String content =
                String.format(" Minimum of All times: %d ms\n " +
                        "Minimum of The last 10 times: %d ms\n " +
                        "Minimum of The last 100 Times: %d ms\n", this.acquireData(1), this.acquireData(2), this.acquireData(3))
                        + String.format(" Maximum of All times: %d ms\n " +
                        "Maximum of The last 10 times: %d ms\n " +
                        "Maximum of The last 100 Times: %d ms\n", this.acquireData(4),this.acquireData(5),this.acquireData(6))
                        + String.format(" Average of All times: %d ms\n " +
                        "Average of The last 10 times: %d ms\n " +
                        "Average of The last 100 Times: %d ms\n",this.acquireData(7),this.acquireData(8),this.acquireData(9))
                        +String.format(" Median of All times: %d ms\n " +
                        "Median of The last 10 times: %d ms\n " +
                        "Median of The last 100 Times: %d ms\n", this.acquireData(10),this.acquireData(11),this.acquireData(12));

        content +="\nZhaorui Chen";
        stats.putExtra(Intent.EXTRA_TEXT, content);
        context.startActivity(stats);
    }
}
