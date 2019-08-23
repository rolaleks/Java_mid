package lesson;

public class Wall implements Action {
    /**
     * Высота метров
     */
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean pass(Participant participant) {

        if(participant.canJump(this.height)) {
            participant.jump();
            return true;
        }
        System.out.println(participant + " не смог прыгнуть");
        return false;
    }
}
