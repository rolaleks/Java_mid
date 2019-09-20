public class SingleMethod {

    public int run(String[][] array) throws MyArraySizeException, MyArrayDataException {


        int sum = 0;
        if (array.length != 4) {
            throw new MyArraySizeException("Первое измерение массива не равно 4");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Второе измерение массива не равно 4");
            }
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return sum;
    }
}
