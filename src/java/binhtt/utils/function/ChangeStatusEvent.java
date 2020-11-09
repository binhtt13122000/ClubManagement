package binhtt.utils.function;


import binhtt.blos.EventBLO;

public class ChangeStatusEvent implements Runnable {

    @Override
    public void run() {
        EventBLO blo = new EventBLO();
        try {
            blo.changeStatus();
//            blo.changeStatusEventDetail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
