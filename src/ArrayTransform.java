import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArrayTransform {

    public static <T> ArrayList<T> toArrayList(T[] array) {

        return new ArrayList<T>(Arrays.asList(array));
    }

    public static HashMap<String, ArrayList<Integer>> toHashMap(List<NameList> list) {

        HashMap<String, ArrayList<Integer>> hm = new HashMap<>();

        list.forEach(item -> {
            ArrayList<Integer> ids = hm.getOrDefault(item.name, new ArrayList<Integer>());
            ids.add(item.id);
            hm.put(item.name, ids);
        });

        return hm;
    }
}
