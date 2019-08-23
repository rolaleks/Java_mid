package lesson;

public class Treadmill implements Action {
    /**
     * Длина метров
     */
    private int length;

    public Treadmill(int length) {
        this.length = length;
    }

    @Override
    public boolean pass(Participant participant) {

        if(participant.canRun(this.length)) {
            participant.run();
            return true;
        }
        System.out.println(participant + " не смог пробежать");
        return false;
    }
}
