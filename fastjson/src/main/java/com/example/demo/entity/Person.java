package com.example.demo.entity;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        System.out.println("Constructs");
        this.name = name;
        this.age = age;
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
}
