public class Echo implements Runnable {


    private String echoStr;

    private Echo nextEcho;
    private Echo prevEcho;
    private Logger logger;

    public int printedCount = 0;

    Echo(String str, Logger logger) {
        this.echoStr = str;
        this.logger = logger;
    }

    public void setNextEcho(Echo echo) {
        this.nextEcho = echo;
        echo.prevEcho = this;
    }

    public String getText() {
        return this.echoStr;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {

            while (true) {
                //prevEcho == null --- начинаем всегда с первого потока
                //printedCount < prevEcho.printedCount --- если в предыдущем потоке распечатлась буква то тоже печатаем
                if (prevEcho == null || printedCount < prevEcho.printedCount) {
                    if (nextEcho == null) {
                        //закончили круг вывода буквы
                        System.out.println(echoStr);
                        printedCount++;
                        //Если это последний поток в очереди, то вызываем notify для всех потоков
                        logger.endPrintCircle();
                    } else {
                        //печатаем букву и подвешиваем поток для ожидания следующего круга
                        logger.print(this);
                    }
                    break;
                } else {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
