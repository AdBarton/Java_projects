package objects;

import java.util.Optional;

public class Osoba {
    String firstName;
    Optional<String> lastName;
    int age;

    public Osoba(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = Optional.ofNullable(lastName);
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Optional<String> getLastName() {
        return lastName;
    }

    public void setLastName(Optional<String> lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "First name:" + firstName+ ", Last name:" + lastName+", Age:"+age;
    }
}
