package mfu;

import java.util.ArrayList;
import java.util.HashMap;

abstract class Service implements MFUService, Runnable {

    protected ArrayList<HashMap<String, Object>> queue;

    protected Thread thread;

    Service() {
        queue = new ArrayList<>();
    }

    @Override
    public void run() {

        while (true) {

            int s = queue.size();
            if (s > 0) {
                this.executeJob(queue.get(0));
                queue.remove(0);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void addJob(HashMap<String, Object> params) {

        this.queue.add(params);
    }


    @Override
    public void on() {
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void off() {
        if (thread != null) {
            thread.interrupt();
        }
    }
}
