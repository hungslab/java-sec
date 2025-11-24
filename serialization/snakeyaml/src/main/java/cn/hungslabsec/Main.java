package cn.hungslabsec;

import org.yaml.snakeyaml.Yaml;

public class Main {
    public static void main(String[] args) {
//        User user = new User("taco", 18);
        Yaml yaml = new Yaml();
//        System.out.println(yaml.dump(user));

        String s = "!!cn.hungslabsec.User {age: 18, name: taco}";
        User user = yaml.load(s);
        System.out.println(user);
//
//        String s = "!!com.snake.demo.User [\"taco\", 18]";
//        User user = yaml.load(s);
        System.out.println(user);
    }

}