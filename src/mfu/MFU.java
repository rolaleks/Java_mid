package mfu;

import java.util.ArrayList;
import java.util.HashMap;

public class MFU {

    private ArrayList<MFUService> services;

    public MFU() {
        services = new ArrayList<>();
        this.addService(new Scanner());
        this.addService(new Printer());
    }

    //Включаем МФУ
    public void on() {
        for (MFUService service : services) {
            service.on();
        }
    }

    //Выключаем МФУ
    public void off() {
        for (MFUService service : services) {
            service.off();
        }
    }

    public void addService(MFUService service) {
        services.add(service);
    }

    public boolean print(String file) throws MFUException {
        //На случай если будет несколько печатающих устройств
        boolean found = false;
        for (MFUService service : services) {
            if (service instanceof Printable) {
                HashMap<String, Object> params = new HashMap<>();
                params.put("fileName", file);
                service.addJob(params);
                found = true;
            }
        }

        if (!found) {
            throw new MFUException("Print service not found");
        }

        return found;
    }

    public boolean scan(String saveToFile) throws MFUException {

        boolean found = false;
        for (MFUService service : services) {
            if (service instanceof Scannable) {
                HashMap<String, Object> params = new HashMap<>();
                params.put("saveToFile", saveToFile);
                service.addJob(params);
                found = true;
            }
        }
        if (!found) {
            throw new MFUException("Scan service not found");
        }

        return found;
    }

}
