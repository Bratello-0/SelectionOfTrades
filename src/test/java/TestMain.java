import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SpringBootApplication
public class TestMain {
    public static void main(String[] args) {

        Map<Kal, Kal> results = new HashMap();

        List<Kal> list1 = List.of(
                new Kal("zalupa", "pupa"),
                new Kal("zalupa", "pupa"),
                new Kal("zalupa", "pupa"),
                new Kal("zalupa", "pupa")
        );
        List<Kal> list2 = List.of(
                new Kal("zalupa", "pupa"),
                new Kal("zalupa", "pupa"),
                new Kal("zalupa", "pupa"),
                new Kal("zalupa", "pupa"),
                new Kal("zalupa", "pupa"),
                new Kal("zalupa", "pupa"),
                new Kal("zalupa", "pupa")
        );

        list1.forEach(kal1 -> {
            if (results.containsKey(kal1)) {
                results.get(kal1).count++;
            } else {
                results.put(kal1, kal1);
            }
        });

        list2.forEach(kal2 -> {
            if (results.containsKey(kal2)) {
                results.get(kal2).count++;
            } else {
                results.put(kal2, kal2);
            }
        });

        results.entrySet().forEach(System.out::println);
    }
}

class Kal{
    public String name;
    public String lastName;
    public int count = 0;

    public Kal(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kal kal = (Kal) o;
        return Objects.equals(name, kal.name) && Objects.equals(lastName, kal.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    @Override
    public String toString() {
        return "Kal{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", count=" + count +
                '}';
    }
}
