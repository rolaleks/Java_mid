public class Logger {

    public synchronized void print(Echo echo) {
        System.out.println(echo.getText());
        echo.printedCount++;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void endPrintCircle()
    {
        notifyAll();
    }
}
