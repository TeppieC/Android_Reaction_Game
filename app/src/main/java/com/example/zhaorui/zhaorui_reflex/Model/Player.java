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

package com.example.zhaorui.zhaorui_reflex.Model;

public class Player {
    /*
   *
   * This class is a class for each player in GameBuzzer.
   *
   */

    private int id;
    private int winningTimes = 0;

    public Player(int id) {
        this.id = id;
        this.winningTimes = 0;
    }

    public int getId() {
        return id;
    }

    public int getWinningTimes() {
        return winningTimes;
    }

    public void addWinningTimes() {
        this.winningTimes +=1;
    }

}
