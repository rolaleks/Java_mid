import java.util.*;

public class Swapper {

    public static void swap(List list, int i1, int i2) {
        Collections.swap(list, i1, i2);
    }
    public static void swap(Object[] array, int i1, int i2) {
        Object t = array[i1];
        array[i1] = array[i2];
        array[i2] = t;
    }
}
