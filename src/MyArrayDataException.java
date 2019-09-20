public class MyArrayDataException extends Exception {

    private int i;

    private int j;

    public MyArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public String toString() {

        return "Ошибка в строке: " + (i + 1) + " и в столбце : " + (j + 1);
    }
}
