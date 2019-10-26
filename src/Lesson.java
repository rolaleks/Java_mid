import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Lesson {


    public static void main(String[] args) {

        PrintFile pf = new PrintFile("src/files/file1.txt");
        pf.print();

        String[] concatFiles = new String[]{
                "src/files/concat_file_1.txt",
                "src/files/concat_file_2.txt",
                "src/files/concat_file_3.txt",
                "src/files/concat_file_4.txt",
                "src/files/concat_file_5.txt"
        };
        ConcatFile concatFile = new ConcatFile(concatFiles);
        concatFile.concatToFile("src/files/concat_result.txt");


        ReverseReader reverseReader = new ReverseReader("src/files/file1.txt");
        reverseReader.read();

    }
}
