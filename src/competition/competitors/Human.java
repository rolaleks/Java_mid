package competition.competitors;

import java.util.Random;

public class Human implements Competitor {
    String name;

    int maxRunDistance;
    int maxJumpHeight;
    int maxSwimDistance;

    boolean active;

    @Override
    public boolean isOnDistance() {
        return active;
    }

    public Human(String name) {
        this.name = name;
        this.maxRunDistance =this.getRandomAttributeValue();
        this.maxJumpHeight = this.getRandomAttributeValue();
        this.maxSwimDistance = this.getRandomAttributeValue();
        this.active = true;
    }

    public Human(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.active = true;
    }

    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println(name + " хорошо справился с кроссом");
        } else {
            System.out.println(name + " не справился с кроссом");
            active = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(name + " удачно перепрыгнул через стену");
        } else {
            System.out.println(name + " не смог перепрыгнуть стену");
            active = false;
        }
    }

    @Override
    public void swim(int dist) {
        if (dist <= maxSwimDistance) {
            System.out.println(name + " отлично проплыл");
        } else {
            System.out.println(name + " не смог проплыть");
            active = false;
        }
    }

    @Override
    public void info() {
        System.out.println(name + " - " + active);
    }

    public String toString()
    {
        return this.name;
    }

    protected int getRandomAttributeValue(){

        Random random = new Random();
        return random.nextInt(400);
    }
}