import java.io.*;
import java.util.Scanner;

public class BigFileReader {

    final private int pageSize = 1800;

    private String path;

    BigFileReader(String path) {
        this.path = path;
    }

    public String readPage(int page) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            br.skip(pageSize * (page - 1));
            char[] chars = new char[pageSize];
            br.read(chars, 0, pageSize);
            return new String(chars);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
