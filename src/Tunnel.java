import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private int tunnelBandwidth;
    private Semaphore semaphore;

    public Tunnel(int tunnelBandwidth) {
        this.tunnelBandwidth = tunnelBandwidth;
        this.semaphore = new Semaphore(tunnelBandwidth);
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                this.semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                this.semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
