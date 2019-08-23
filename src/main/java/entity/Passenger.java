package entity;

import lombok.Data;

/**
 * Class Passenger
 *
 * @author create by ivanslusar
 * 8/21/19
 * @project CustomSerialization
 */
@Data
public class Passenger {
    private String name;
    private String soName;
    private int age;

    public Passenger(String name, String soName, int age) {
        this.name = name;
        this.soName = soName;
        this.age = age;
    }


    public Passenger() {
        this("Test","user",99);
    }
}
