import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {


    public static void main(String[] args) {

        BigFileReader bigFileReader = new BigFileReader("src/files/bigFile.txt");

        System.out.println("Введите номер станицы");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                String name = reader.readLine();
                System.out.println("Текст:");
                System.out.println(bigFileReader.readPage(Integer.parseInt(name)));
                System.out.println("Введите номер станицы");
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        System.out.println(bigFileReader.readPage(1));
        System.out.println("---------------");
        System.out.println(bigFileReader.readPage(500));
        System.out.println("---------------");
        System.out.println(bigFileReader.readPage(1));
        System.out.println("---------------");
        System.out.println(bigFileReader.readPage(10000));

         */
    }
}
