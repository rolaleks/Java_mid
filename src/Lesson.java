public class Lesson {

    static final int size = 10000000;
    static int threadNumber = 2;

    public static void main(String[] args) {

        syncMethod();
        System.out.println("2 Потока");
        asyncMethod();
        System.out.println("8 Потоков");
        threadNumber = 8;
        asyncMethod();
        System.out.println("16 Потоков");
        threadNumber = 16;
        asyncMethod();
    }

    public static float[] getInitArray() {
        float[] numbers = new float[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = 1;
        }
        return numbers;
    }

    public static void syncMethod() {

        System.out.println("Синхронный Метод:");
        float[] numbers = getInitArray();

        long a = System.currentTimeMillis();

        ThreadCalculation threadCalculation = new ThreadCalculation(numbers);
        threadCalculation.run();

        System.out.println("Время выполнения, мс: " + (System.currentTimeMillis() - a));
    }

    public static void asyncMethod() {

        System.out.println("Метод с потоками:");
        float[] numbers = getInitArray();

        long a = System.currentTimeMillis();

        //Ищем остаток для деления
        int divideDiff = size % threadNumber;

        //Размер массива для каждого потока
        int piecesSize = (size - divideDiff) / threadNumber;

        //Список классов исполнителей потоков
        ThreadCalculation[] threadCalculationsArray = new ThreadCalculation[threadNumber];

        //разбивка массива и начало потоков
        for (int i = 0; i < threadNumber; i++) {
            int startPos = i * piecesSize;
            int length = piecesSize;
            if (i == (threadNumber - 1)) {
                //Если это последний "кусок", то оставшиеся значение добавляем к нему
                length += divideDiff;
            }
            float[] newArray = new float[length];
            System.arraycopy(numbers, startPos, newArray, 0, length);

            threadCalculationsArray[i] = new ThreadCalculation(newArray);
            threadCalculationsArray[i].startThread();
        }

        //склейка массива
        for (int i = 0; i < threadNumber; i++) {
            try {
                threadCalculationsArray[i].getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            float[] resultNumbers = threadCalculationsArray[i].getArray();
            int startPos = i * piecesSize;
            System.arraycopy(resultNumbers, 0, numbers, startPos, resultNumbers.length);
        }

        System.out.println("Время выполнения, мс: " + (System.currentTimeMillis() - a));
    }
}
