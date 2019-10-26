import java.io.IOException;
import java.io.RandomAccessFile;

public class ReverseReader {

    private String file;


    ReverseReader(String file) {
        this.file = file;
    }

    public void read() {

        try (RandomAccessFile f = new RandomAccessFile(this.file, "r")) {
            int x;
            for (long i = f.length(); i >= 0; i--) {
                f.seek(i);
                if ((x = f.read()) != -1) {
                    System.out.print((char) x);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
