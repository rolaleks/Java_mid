package lesson;

public class Cat implements Participant {

    //Прописываем в каждом классе переменные тк в задании сказано не наследовать от одного класса
    private int maxJump;
    private int maxDistance;

    public Cat(int maxJump, int maxDistance) {
        this.maxJump = maxJump;
        this.maxDistance = maxDistance;
    }

    //Прописываем в каждом классе методы тк в задании сказано не наследовать от одного класса
    @Override
    public void jump() {
        System.out.println(this + " прыгает");
    }

    //Прописываем в каждом классе методы тк в задании сказано не наследовать от одного класса
    @Override
    public void run() {
        System.out.println(this + " бежит");
    }

    //Прописываем в каждом классе методы тк в задании сказано не наследовать от одного класса
    @Override
    public boolean canJump(int height) {
        return height <= this.maxJump;
    }

    //Прописываем в каждом классе методы тк в задании сказано не наследовать от одного класса
    @Override
    public boolean canRun(int length) {
        return length <= this.maxDistance;
    }

    @Override
    public String toString() {
        return "Cat";
    }
}
