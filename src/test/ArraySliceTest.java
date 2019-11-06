package test;

import lesson.Lesson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArraySliceTest {

    @Parameterized.Parameters
    public static Collection<Object[]> arrays() {
        return Arrays.asList(new Object[][]{
                {new ArrayList<Integer>(Arrays.asList(1, 3, 4)), new ArrayList<Integer>()},
                {new ArrayList<Integer>(Arrays.asList(1, 3, 4, 6, 7)), new ArrayList<Integer>(Arrays.asList(6, 7))},
                {new ArrayList<Integer>(Arrays.asList(4, 6, 1)), new ArrayList<Integer>(Arrays.asList(6, 1))},
        });
    }

    private ArrayList<Integer> list;
    private ArrayList<Integer> out;

    public ArraySliceTest(ArrayList<Integer> list, ArrayList<Integer> out) {
        this.list = list;
        this.out = out;
    }

    Lesson lesson;

    @Before
    public void init() {
        lesson = new Lesson();
    }

    @Test
    public void testSlice() {
        boolean success = out.equals(lesson.arraySlice(list));
        System.out.println(success);
        Assert.assertTrue(success);
    }

    @Test(expected = RuntimeException.class)
    public void testSliceException() {
        ArrayList<Integer> exceptionList = new ArrayList<>(list);
        //Убираем все числа = 4
        exceptionList.removeIf(s -> s == 4);
        lesson.arraySlice(exceptionList);
    }

}
