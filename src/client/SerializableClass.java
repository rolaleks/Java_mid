package client;

import java.io.Serializable;

public class SerializableClass implements Serializable {

    private int a;
    private int b;

    SerializableClass(int a, int b) {
        this.a = a;
        this.b = b;
    }


    public void info(){
        System.out.println("A: " + a);
        System.out.println("B: " + b);
    }

}
