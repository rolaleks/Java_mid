import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Tester {

    private static Object object;

    public static void start(Class c) {

        performTest(c);
    }


    public static void start(String c) throws ClassNotFoundException {
        Class<?> cls = Class.forName(c);

        performTest(cls);
    }

    private static void performTest(Class c) {

        try {
            object = c.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Неверный класс");
        }

        beforeSuite(c);

        ArrayList<Method> testMethods = new ArrayList<>();

        for (Method m : c.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                testMethods.add(m);
            }
        }

        Collections.sort(testMethods, Comparator.comparingInt(m -> m.getAnnotation(Test.class).priotity()));
        for (Method m : testMethods) {
            try {
                m.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        afterSuite(c);
    }

    public @interface MyAnon {
        int priotity() default 5;
    }

    private static void beforeSuite(Class c) {
        boolean found = false;
        for (Method m : c.getDeclaredMethods()) {

            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (found) {
                    throw new RuntimeException("BeforeSuite можеть быть использован только 1 раз");
                }
                found = true;
                try {
                    m.invoke(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static void afterSuite(Class c) {
        boolean found = false;
        for (Method m : c.getDeclaredMethods()) {

            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (found) {
                    throw new RuntimeException("AfterSuite можеть быть использован только 1 раз");
                }
                found = true;
                try {
                    m.invoke(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
