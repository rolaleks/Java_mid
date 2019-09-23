import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LessonExtra {


    public static void main(String[] args) {

        System.out.println("Введите пароль");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Boolean validationResult = false;
        try {
            do {
                String name = reader.readLine();
                PasswordValidator passwordValidator = new PasswordValidator(name);
                try {
                    validationResult = passwordValidator.validate();
                } catch (InvalidPassException e) {
                    System.out.println(e.getMessage());
                }
            } while (!validationResult);

            System.out.println("Пароль валидный");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
