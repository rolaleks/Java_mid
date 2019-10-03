package lesson;

import lesson.client.Client;

public class LessonClient {

    public static void main(String[] args) {
        new Client("localhost", 7777);
    }
}
