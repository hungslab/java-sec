package cn.hungslabsec;

import java.io.Serializable;

/**
 * @author Hungs
 * @date 2024/2/6
 * @Description Description of the file.
 */
public class Persion implements Serializable {
    private String name;
    private int age;

    public Persion(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
