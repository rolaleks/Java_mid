import java.util.ArrayList;
import java.util.HashMap;

public class Lesson {


    public static void main(String[] args) {
        /**
         * 1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
         * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.
         */
        ArrayList<String> list = new ArrayList<String>();
        list.add("Ivan");
        list.add("Petr");
        list.add("Inna");
        list.add("Ivan");
        list.add("Lisa");
        list.add("Misha");
        list.add("Anton");
        list.add("Slava");
        list.add("Inna");
        list.add("Sergey");

        System.out.println(list);

        HashMap<String, Integer> map = new HashMap<>();

        System.out.println("Список уникальных слов:");
        for (String str : list) {
            Integer i = map.get(str);
            if (i == null) {
                System.out.println(str);
                map.put(str, 1);
            } else {
                map.put(str, i + 1);
            }
        }

        System.out.println("Список упоминаний каждого слова:");
        System.out.println(map);

        /**
         * . Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
         * В этот телефонный справочник с помощью метода add() можно добавлять записи.
         * С помощью метода get() искать номер телефона по фамилии.
         * Следует учесть, что под одной фамилией может быть несколько телефонов, тогда при запросе такой фамилии должны выводиться все телефоны.
         */

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("ivan", "+799999999999");
        phoneBook.add("Ivan", "+788888888888");
        phoneBook.add("petr", "+4444444444");
        phoneBook.add("petr", "+5555555");
        System.out.println("Телефоны ivan:");
        System.out.println(phoneBook.get("ivan"));
        System.out.println("Телефоны petr:");
        System.out.println(phoneBook.get("petr"));
    }

}
