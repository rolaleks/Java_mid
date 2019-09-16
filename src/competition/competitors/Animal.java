package competition.competitors;

import java.util.Random;

public class Animal implements Competitor {
    String type;
    String name;

    int maxRunDistance;
    int maxJumpHeight;
    int maxSwimDistance;

    boolean onDistance;

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    public Animal(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.type = this.getType();
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }

    public Animal(String name) {
        this.type = this.getType();
        this.name = name;
        this.maxRunDistance =this.getRandomAttributeValue();
        this.maxJumpHeight = this.getRandomAttributeValue();
        this.maxSwimDistance = this.getRandomAttributeValue();
        this.onDistance = true;
    }



    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println(type + " " + name + " хорошо справился с кроссом");
        } else {
            System.out.println(type + " " + name + " не справился с кроссом");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(type + " " + name + " удачно перепрыгнул через стену");
        } else {
            System.out.println(type + " " + name + " не смог перепрыгнуть стену");
            onDistance = false;
        }
    }

    @Override
    public void swim(int dist) {
        if (maxSwimDistance == 0) {
            System.out.println(type + " " + name + " не умеет плавать");
            onDistance = false;
            return;
        }
        if (dist <= maxSwimDistance) {
            System.out.println(type + " " + name + " отлично проплыл");
        } else {
            System.out.println(type + " " + name + " не смог проплыть");
            onDistance = false;
        }
    }

    @Override
    public void info() {

        System.out.println(type + " " + name + " - " + onDistance);
    }

    public String toString()
    {
        return this.name;
    }

    public String getType(){
        return "Животное";
    }

    protected int getRandomAttributeValue(){

        Random random = new Random();
        return random.nextInt(300);
    }
}
