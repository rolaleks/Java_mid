package mfu;

import java.util.HashMap;

public class Scanner extends Service implements Scannable {
    @Override
    public void executeJob(HashMap<String, Object> params) {

        String saveToFile = (String) params.get("saveToFile");
        this.scan(saveToFile);
    }

    @Override
    public void scan(String saveToFile) {

        System.out.println("Scan started");
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Scan stopped and saved to:" + saveToFile);
    }


}
