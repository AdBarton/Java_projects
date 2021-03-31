package utils;

import cz.bartad.miniormframework.annotation.Identifier;
import cz.bartad.miniormframework.annotation.Table;
import cz.bartad.miniormframework.annotation.TableColumn;

import java.util.Date;

@Table("Osoby")
public class Osoba {
    @Identifier
    @TableColumn("ID")
    private Long id;

    @TableColumn("NAME")
    private String firtName;
    private String lastName;
    private int age;
    private Date birthDate;

    public Osoba(String firtName, String lastName, int age, Date birthDate) {
        this.firtName = firtName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}

