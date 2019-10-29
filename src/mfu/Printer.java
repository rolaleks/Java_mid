package mfu;

import java.util.HashMap;

public class Printer extends Service implements Printable {

    @Override
    public void executeJob(HashMap<String, Object> params) {

        String file = (String) params.get("fileName");
        this.print(file);
    }

    @Override
    public void print(String file) {

        System.out.println("Print " + file + " started");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Print " + file + " stopped");
    }
}
