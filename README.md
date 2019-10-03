Основное задание 

Сервер lesson.LessonServer.java

Клиент lesson.LessonClient.java

Доп. задание:
взял код за основу с урока, лежит в пакете lessonExtra

для решения проблемы отключения клиента добавляем в ClientHandler следущий код, который будет выполняться каждый раз при отключении клиента
```
catch (IOException e) {
    System.out.println("Client OUT");
    serv.disconnectClient(ClientHandler.this);
}
```
на сервере убираем этого клиента из списка
```
public void disconnectClient(ClientHandler clientHandler) {
    clients.remove(clientHandler);
}
```



