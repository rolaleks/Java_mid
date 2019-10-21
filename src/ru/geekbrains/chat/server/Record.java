package ru.geekbrains.chat.server;

public abstract class Record {

    protected boolean isNewRecord = true;



    public boolean save() {
        if (this.isNewRecord) {
            return insert();
        } else {
            return update();
        }
    }

    protected abstract boolean insert();

    protected abstract boolean update();



    public boolean isNewRecord() {
        return isNewRecord;
    }
}
