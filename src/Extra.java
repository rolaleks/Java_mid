public class Extra {

    public static void main(String[] args) {
        int sizeX = 9;
        int sizeY = 5;

        //Количество элементов в спирали
        int totalSize = sizeX * sizeY;

        //Находим самую короткую сторону
        int minSide = Math.min(sizeX, sizeY);
        //Количество кругов в спирали
        double circleCount = Math.ceil((double) minSide / 2);

        int iteration = 1;
        int[][] spiral = new int[sizeY][sizeX];

        //Заполняем спираль по кругам от большего к меньшему

        for (int circle = 0; circle < circleCount; circle++) {
            //Количество элементов в текущем круге
            int circleElementCount = (sizeX + sizeY - circle * 4) * 2 - 4;
            //с каждым кругом сужаем круг на 2
            //сторона X
            int circleXSize = sizeX - (circle * 2);
            //сторона Y, -2 тк не учитываем углы который общие с X
            int circleYSize = sizeY - (circle * 2) - 2;
            for (int x = 0; x < circleXSize; x++) {
                //Вычисляем по верхней оси Х в круге
                int circleX = x + circle;
                spiral[circle][circleX] = iteration + x;
                //Если sizeY не четное то последний полу круг снизу по оси X не нужен
                if (circle + 1 <= (double) sizeY / 2) {

                    //Вычисляем по нижний оси Х в круге, -1 тк iteration уже преинициализированна
                    spiral[sizeY - circle - 1][circleX] = iteration + (circleXSize * 2) - x + (circleYSize - 1);
                }
            }
            for (int y = 0; y < circleYSize; y++) {
                //Вычисляем по правой оси Y в круге
                int circleY = y + circle + 1;
                spiral[circleY][sizeX - circle - 1] = iteration + y + circleXSize;
                //Если sizeX не четное то последний полу круг слево по оси Y не

                if (circle + 1 <= (double) sizeX / 2) {

                    //Вычисляем по левой оси Y в круге
                    spiral[circleY][circle] = iteration + circleElementCount - 1 - y;
                }
            }

            iteration += circleElementCount;
        }

        for (int y = 0; y < spiral.length; y++) {
            for (int x = 0; x < spiral[y].length; x++) {

                System.out.print(String.format("% 3d", spiral[y][x]) + " ");
            }
            System.out.println();
        }

    }
}
