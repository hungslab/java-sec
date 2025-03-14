package cn.hungslabsec.Reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Properties;

public class ReflectTest {

    public void Reflect() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // 根据配置文件 re.properties 指定信息，创建Cat对象并调用方法hi

        // 传统的方式 new 对象-》 调用方法
//        Cat cat = new Cat();
//        cat.hi();

        // 1.使用Properties类，可以读配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("E:\\Projects\\JavaProject\\java-sec\\javabase\\target\\classes\\re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();
        System.out.printf("classfullpath = %s, methodName = %s\n", classfullpath, methodName);

        //2.创建对象，传统的方法，行不通 =》反射机制
        //new classfullpath();
        //3. 使用反射机制解决
        //(1) 加载类，返回Class类型的对象。
        Class cls = Class.forName(classfullpath);
        //(2) 通过 cls 得到你加载的类 cn.hungslabsec.Reflection.Cat 的对象实例
        Object o = cls.newInstance();
        System.out.println("o的运行类型 = " + o.getClass());
        //(3) 通过 cls 得到你加载的类 cn.hungslabsec.Reflection.Cat 的 methodName "hi" 的方法对象
        //    即：在反射中，可以把方法视为对象（万物皆对象）
        Method method1 = cls.getMethod(methodName);
        //(4) 通过 method1 调用方法：即通过对象来实现调用方法
        method1.invoke(o);// 传统方法 对象.方法 ， 反射机制 方法.invoke(对象)
    }

    public void execute() throws Exception {
        /**
         * (1) runtime类构造方法是私有的，无法执行命令（单例模式）
         * Class clazz = Class.forName("java.lang.Runtime");
         * clazz.getMethod("exec", String.class).invoke(clazz.newInstance(), "calc.exe");
         */


        // 1. 利用Runtime.getRuntime()来获取Runtime对象
//        Class clazz = Class.forName("java.lang.Runtime");
//        clazz.getMethod("exec", String.class).invoke(clazz.getMethod("getRuntime").invoke(clazz), "calc.exe");

        // 2. payload 分解
//        Class clazz = Class.forName("java.lang.Runtime");
//        Method execmethod = clazz.getMethod("exec", String.class);
//        Method getRuntimeMethod = clazz.getMethod("getRuntime");
//        Object runtime = getRuntimeMethod.invoke(clazz);
//        execmethod.invoke(runtime, "calc.exe");


        // 3. ProcessBuilder(List<String> command) 执行命令
//        Class clazz = Class.forName("java.lang.ProcessBuilder");
//        ((ProcessBuilder) clazz.getConstructor(List.class).newInstance(Arrays.asList("calc.exe"))).start();

        // 4. 反射完成
//        Class clazz = Class.forName("java.lang.ProcessBuilder");
//        clazz.getMethod("start").invoke(clazz.getConstructor(List.class).newInstance(Arrays.asList("calc.exe")));

        // 5. ProcessBuilder(String... command) 执行命令
//        Class clazz = Class.forName("java.lang.ProcessBuilder");
//        ((ProcessBuilder) clazz.getConstructor(String[].class).newInstance(new String[][]{{"calc.exe"}})).start();

        // 6. 反射完成
//        Class clazz = Class.forName("java.lang.ProcessBuilder");
//        clazz.getMethod("start").invoke(clazz.getConstructor(String[].class).newInstance(new String[][]{{"calc.exe"}}));

        // 7. getDeclared系列
//        Class clazz = Class.forName("java.lang.Runtime");
//        Constructor m = clazz.getDeclaredConstructor();
//        m.setAccessible(true);
//        clazz.getMethod("exec", String.class).invoke(m.newInstance(), "calc.exe");


    }

}
