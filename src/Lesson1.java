import java.util.ArrayList;
import java.util.Arrays;

public class Lesson1 {

    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        String[] stringArray = new String[]{"one", "two", "three", "four", "five"};

        System.out.println("------------- 1 задание");
        System.out.println("Swap - ArrayList");
        System.out.println(arrayList);
        Swapper.swap(arrayList, 1, 4);
        System.out.println(arrayList);

        System.out.println("Swap - String Array");
        System.out.println(Arrays.toString(stringArray));
        Swapper.swap(stringArray, 1, 4);
        System.out.println(Arrays.toString(stringArray));

        System.out.println("------------- 2 задание");
        System.out.println("Массив строк в ArrayList");
        System.out.println(ArrayTransform.toArrayList(stringArray).getClass());

        System.out.println("------------- 3 задание");
        Box<Apple> appleBox1 = new Box<>();
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.add(new Apple());
        appleBox2.add(new Apple());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());

        if (appleBox1.compare(orangeBox)) {
            System.out.println("Коробки appleBox1 и orangeBox равны");
        } else {
            System.out.println("Коробки appleBox1 и orangeBox НЕ равны");
        }

        System.out.println("Размер коробки appleBox1 " + appleBox1.count());
        System.out.println("Размер коробки appleBox2 " + appleBox2.count());
        appleBox1.move(appleBox2);
        System.out.println("После пересыпания: ");
        System.out.println("Размер коробки appleBox1 " + appleBox1.count());
        System.out.println("Размер коробки appleBox2 " + appleBox2.count());


        ArrayList<NameList> nameListArray = new ArrayList<>();

        nameListArray.add(new NameList(1, "name1"));
        nameListArray.add(new NameList(2, "name2"));
        nameListArray.add(new NameList(3, "name3"));
        nameListArray.add(new NameList(4, "name4"));
        nameListArray.add(new NameList(5, "name1"));
        nameListArray.add(new NameList(6, "name4"));

        System.out.println("------------- 4 задание");
        System.out.println("toHashMap:");
        System.out.println(ArrayTransform.toHashMap(nameListArray));



    }
}
