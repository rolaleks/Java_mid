public class Lesson {

    public static void main(String[] args) {


        Logger logger = new Logger();
        Echo e1 = new Echo("A", logger);
        Echo e2 = new Echo("B", logger);
        Echo e3 = new Echo("C", logger);
        e1.setNextEcho(e2);
        e2.setNextEcho(e3);

        Thread t1 = new Thread(e1);
        Thread t2 = new Thread(e2);
        Thread t3 = new Thread(e3);

        t1.start();
        t2.start();
        t3.start();

    }
}
