import java.util.regex.Pattern;

public class PasswordValidator {

    private String pass;

    private int minLength = 8;

    public PasswordValidator(String pass) {
        this.pass = pass;
    }


    public boolean validate() throws InvalidPassException {

        if (this.pass.length() < this.minLength) {
            throw new InvalidPassException("Длина пароля не может быть меньше:" + this.minLength);
        }

        if (!Pattern.compile("\\d+").matcher(this.pass).find()) {
            throw new InvalidPassException(" В пароле должно быть число");
        }

        if (!Pattern.compile("[a-z,а-я]+").matcher(this.pass).find()) {
            throw new InvalidPassException("В пароле должна быть хотя бы 1 строчная буква");
        }

        if (!Pattern.compile("[A-Z,А-Я]+").matcher(this.pass).find()) {
            throw new InvalidPassException("В пароле должна быть хотя бы 1 заглавная буква");
        }

        if (Pattern.compile("\\s+").matcher(this.pass).find()) {
            throw new InvalidPassException("Не должно быть пробелов");
        }

        return true;
    }


    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }
}
