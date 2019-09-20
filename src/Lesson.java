public class Lesson {

    public static void main(String[] args) {


        String[][] incorrectSize1 = new String[2][2];
        String[][] incorrectSize2 = new String[4][2];
        String[][] wrongType = {{"1", "3", "-4", "2"}, {"0", "3", "Ooops", "2"}, {"1", "3", "-4", "2"}, {"1", "3", "4", "2"}};
        String[][] correct = {{"1", "-5", "4", "2"}, {"1", "-3", "4", "2"}, {"1", "3", "0", "2"}, {"1", "3", "4", "2"}};
        test(incorrectSize1);
        test(incorrectSize2);
        test(wrongType);
        test(correct);
    }

    //созда метод test что бы не дублировать блок try catch
    protected static void test(String[][] testData) {

        SingleMethod singleMethod = new SingleMethod();
        try {
            System.out.println("Сумма массива: " + singleMethod.run(testData));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e);
        }
    }
}
