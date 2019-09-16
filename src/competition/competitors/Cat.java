package competition.competitors;

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    public Cat(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        super(name, maxRunDistance, maxJumpHeight, maxSwimDistance);
    }

    public String getType(){
        return "Кот";
    }
}