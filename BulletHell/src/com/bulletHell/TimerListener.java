package com.bulletHell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
    int seconds = 0;
    int minutes = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
    }

    public void reset(){
        seconds = 0;
        minutes = 0;
    }

    public String timePassed(){
        String out;
        if(seconds > 59){
            minutes++;
            seconds = 0;
        }

        if(minutes == 0){
            out = "0";
        } else {
            out = Integer.toString(minutes);
        }
        out += ":";

        if(seconds == 0){
            out += "00";
        } else if (seconds < 10){
            out += "0" + Integer.toString(seconds);
        } else {
            out += Integer.toString(seconds);
        }
        return out;
    }
}