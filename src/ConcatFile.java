import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ConcatFile {

    protected ArrayList<InputStream> files;

    ConcatFile(String[] paths) {
        files = new ArrayList<>();

        for (String path : paths) {
            try {
                files.add(new FileInputStream(path));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void concatToFile(String distFilePath) {

        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(this.files));

        try {

            int x;
            byte[] arr = new byte[1000];
            FileOutputStream out = new FileOutputStream(distFilePath);

            while ((x = sequenceInputStream.read(arr)) != -1) {
                out.write(arr, 0, x);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
