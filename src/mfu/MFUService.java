package mfu;

import java.util.HashMap;

interface MFUService {


    public void executeJob(HashMap<String, Object> params);

    public void addJob(HashMap<String, Object> params);

    public void on();

    public void off();
}
