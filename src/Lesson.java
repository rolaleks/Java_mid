public class Lesson {

    public static void main(String[] args) {

        try {
            Tester.start("TestClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
