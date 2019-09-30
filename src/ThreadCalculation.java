public class ThreadCalculation implements Runnable {

    private float[] arr;

    private Thread thread;

    ThreadCalculation(float[] array) {
        this.arr = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public void startThread() {
        this.thread = new Thread(this);
        this.thread.start();
    }


    public float[] getArray() {
        return arr;
    }

    public Thread getThread() {
        return this.thread;
    }


}
