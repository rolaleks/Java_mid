package lesson;

import java.util.ArrayList;
import java.util.HashSet;

public class Lesson {

    public ArrayList<Integer> arraySlice(ArrayList<Integer> list) {
        ArrayList<Integer> out = new ArrayList<>();
        for (Integer number : list) {
            if (number == 4) {
                out.clear();
            } else {
                out.add(number);
            }
        }
        if (out.size() == list.size()) {
            throw new RuntimeException("Массив должен содержить число 4");
        }
        return out;
    }

    public boolean numberExists(ArrayList<Integer> list) {
        ArrayList<Integer> numbersToFine = new ArrayList<Integer>() {{
            add(1);
            add(4);
        }};
        HashSet<Integer> foundNumbers = new HashSet<Integer>();
        for (Integer number : list) {
            if (numbersToFine.contains(number)) {
                foundNumbers.add(number);
                if (foundNumbers.size() == numbersToFine.size()) {
                    return true;
                }
            }

        }
        return false;
    }


    public static void main(String[] args) {
        Lesson l = new Lesson();
        ArrayList<Integer> test = new ArrayList<>();
        test.add(3);
        test.add(4);
        test.add(5);
        test.add(3);
        System.out.println(l.arraySlice(test));
    }

}
