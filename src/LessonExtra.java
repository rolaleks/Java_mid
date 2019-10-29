import mfu.MFU;
import mfu.MFUException;

public class LessonExtra {
    public static void main(String[] args) {


        MFU mfu = new MFU();
        mfu.on();

        try {
            mfu.print("File 1");
            mfu.print("File 2");
            mfu.scan("Save to file 1");
            mfu.scan("Save to file 2");
            mfu.scan("Save to file 3");
            mfu.print("File 3");
            mfu.print("File 4");
            mfu.print("File 5");
            mfu.print("File 6");
            mfu.scan("Save to file 5");
        } catch (MFUException e) {
            e.printStackTrace();
        }

    }
}
