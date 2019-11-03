import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private ArrayList<Stage> stages;
    private int carsCount;
    private CountDownLatch startReady;
    private boolean isRaceStarted = false;
    private AtomicInteger carFinished;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(int carsCount, Stage... stages) {
        this.carsCount = carsCount;
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.startReady = new CountDownLatch(carsCount);
        this.carFinished = new AtomicInteger();
    }

    public void readyForRace() {
        this.startReady.countDown();
    }

    public void waitAllParticipantsToStart() {
        try {
            this.startReady.await();
            if (!isRaceStarted) {
                isRaceStarted = true;
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возращает место машины на финише
     * @return
     */
    public Integer finish() {

        return this.carFinished.incrementAndGet();
    }
}
