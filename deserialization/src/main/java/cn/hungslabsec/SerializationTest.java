package cn.hungslabsec;

import java.io.*;

/**
 * @author Hungs
 * @date 2024/2/6
 * @Description Description of the file.
 */
public class SerializationTest {
    public static void serialize(Object obj) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
    }

    public static void main (String[] args) throws Exception {
        Persion persion = new Persion("aa", 22);
        System.out.println(persion);
        serialize(persion);
    }
}
