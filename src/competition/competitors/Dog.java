package competition.competitors;

public class Dog extends Animal {
    public Dog(String name) {

        super(name);
    }

    public Dog(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        super(name, maxRunDistance, maxJumpHeight, maxSwimDistance);
    }

    public String getType(){
        return "Собака";
    }
}
