import java.util.ArrayList;

public class Box<T extends Fruit> {


    private ArrayList<T> fruits;

    Box() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        this.fruits.add(fruit);
    }

    public double getWeight() {
        double weight = 0;
        for (T fruit : fruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> box) {

        return this.getWeight() == box.getWeight();
    }

    public void move(Box<T> distBox) {
        for (T fruit : fruits) {
            distBox.add(fruit);
        }
        fruits.clear();
    }

    public int count() {
        return fruits.size();
    }
}
