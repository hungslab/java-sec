package cn.hungslabsec.Reflection;

import java.lang.reflect.*;

public class TestReflect {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 获取Class对象的方法 1. 通过class.forname查找
        Class dog = Class.forName("cn.hungslabsec.Reflection.Dog");
        System.out.println(dog);

        // 直接引用class对象
        dog = Dog.class;
        System.out.println(dog);
        Dog d = new Dog();

        //通过对象动态查找到他所属的对象
        Class dog1 = d.getClass();
        System.out.println(dog1);

        // 根据方法参数查找到相对应的构造函数，查找无参的构造函数
        Constructor c = dog1.getConstructor(new Class[]{});
        System.out.println(c.newInstance());

        //上面的代码等价于直接调用Class的newinstance方法
        dog1.newInstance();

        // 查找类中，构造函数的参数是string类型的方法
        Constructor c1 = dog1.getConstructor(new Class[]{String.class});
        Object jinmao = c1.newInstance(new Object[]{"aaa"});
        System.out.println(c1.getParameterTypes());
        System.out.println(c1.toGenericString());
        System.out.println(c1.getGenericParameterTypes());
        System.out.println(c1.getDeclaringClass());

        Field nameF = dog1.getField("name");
        System.out.println(nameF.getName());
        System.out.println(nameF.get(jinmao));
        nameF.set(jinmao, "bbb");
        System.out.println(nameF.get(jinmao));

        // 私有变量，需要用getDeclaredField("age")
        Field ageF = dog1.getDeclaredField("age");
        System.out.println(ageF.getName());

        // 需要调用setAccessible才可以访问私有变量
        ageF.setAccessible(true);
        System.out.println(ageF.get(jinmao));
        ageF.set(jinmao, 5);
        System.out.println(ageF.get(jinmao));

        Field [] fields = dog1.getFields();
        for(Field f: fields){
            System.out.println(f);
        }
        Dog dd = new Dog();

        Method m = dog1.getMethod("staticMethod");
        m.invoke(null);

        Method m2 = dog1.getMethod("getName");
        m2.invoke(dd);

        Method m3 = dog1.getDeclaredMethod("setName", String.class);
        m3.setAccessible(true);
        m3.invoke(dd, "aaa");

        Dog[] dogs = new Dog[]{new Dog()};
        Dog[] dogs1 = (Dog[]) Array.newInstance(Dog.class, 1);
        Array.set(dogs1, 0, new Dog());
        Array.get(dogs1, 0);

    }
}

class Dog{
    public String name ;
    private int age;
    public Dog(){
        this.name = "default";
        System.out.println("无参构造函数");
    }
    public Dog(String name){
        this.name = name ;
        this.age = 3;
        System.out.println("有参构造函数\t" + name);
    }

    public void getName(){
        System.out.println(this.name);
    }

    private void setName(String name){
        System.out.println(name);
    }

    public static void staticMethod(){
        System.out.println("静态方法调用示例");
    }
}
