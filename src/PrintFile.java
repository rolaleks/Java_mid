import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PrintFile {

    private String path;

    PrintFile(String path) {
        this.path = path;
    }


    public void print() {

        try (FileInputStream file = new FileInputStream(this.path)) {
            int x;
            byte[] arr = new byte[1000];
            while ((x = file.read(arr)) != -1) {
                System.out.println(new String(arr, 0, x));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
