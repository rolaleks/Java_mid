import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {


    private HashMap<String, ArrayList<String>> data;

    PhoneBook() {
        this.data = new HashMap<>();
    }


    public void add(String name, String phone) {

        ArrayList<String> phones = this.data.get(name);

        if (phones == null) {
            phones = new ArrayList<String>();
        }

        if (!phones.contains(phone)) {
            phones.add(phone);
        }

        this.data.put(name, phones);
    }

    public String get(String name) {
        ArrayList<String> phones = this.data.get(name);

        if (phones == null) {
            return "Такой человек не найден";
        }

        return String.join(", ", phones);
    }

}
