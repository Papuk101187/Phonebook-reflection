package org.example.entity;

public class Tokentime {

    private long timer_s = 0;
    private long timer_f = 0;
    private long timer_d = 3300000; //55 минут


    private boolean timer() {

        if (timer_s == 0) {
            timer_s = System.currentTimeMillis();
            timer_f = timer_s + timer_d;
        }

        if (timer_f < System.currentTimeMillis()) {
            timer_s = 0;
            return true;
        } else return false;

    }


    public boolean runTimer() {

        boolean timers = true;
        boolean status = false;

        while (true) {
            status = timer();
            if (status == true) {
                status = true;
                return status;
            }
            return false;
        }


    }


}
