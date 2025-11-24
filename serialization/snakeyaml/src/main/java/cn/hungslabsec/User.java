package cn.hungslabsec;


public class User {
    private String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
        System.out.println("Non Arg Constructor");
    }

    public String getName() {
        System.out.println("getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }

    public int getAge() {
        System.out.println("getAge");
        return age;
    }

    public void setAge(int age) {
        System.out.println("setAge");
        this.age = age;
    }

    @Override
    public String toString() {
        return "I am " + name + ", " + age + " years old";
    }
}