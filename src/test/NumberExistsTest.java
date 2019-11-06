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
public class NumberExistsTest {

    @Parameterized.Parameters
    public static Collection<Object[]> arrays() {
        return Arrays.asList(new Object[][]{
                {new ArrayList<Integer>(Arrays.asList(1, 3, 4)), true},
                {new ArrayList<Integer>(Arrays.asList(5, 3, 2)), false},
                {new ArrayList<Integer>(Arrays.asList(1, 3, 2)), false},
                {new ArrayList<Integer>(Arrays.asList(4, 3, 4)), false},
                {new ArrayList<Integer>(Arrays.asList(4, 3, 4, 1, 1)), true},
                {new ArrayList<Integer>(Arrays.asList()), false},
                {new ArrayList<Integer>(Arrays.asList(1)), false},
                {new ArrayList<Integer>(Arrays.asList(4)), false},
        });
    }

    private ArrayList<Integer> list;
    private  Boolean result;

    public NumberExistsTest(ArrayList<Integer> list, Boolean result) {
        this.list = list;
        this.result = result;
    }

    Lesson lesson;

    @Before
    public void init() {
        lesson = new Lesson();
    }

    @Test
    public void testSlice() {
        Assert.assertTrue(lesson.numberExists(list) == result);
    }
}
