package cn.hungslabsec;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Hungs
 * @date 2024/2/6
 * @Description Description of the file.
 */
public class UnserializeTest {
    public static Object unserialize(String Filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        Object obj = ois.readObject();
        return obj;
    }

    public static void main (String[] args) throws Exception {
        Persion persion = (Persion) unserialize("ser.bin");
        System.out.println(persion);
    }
}
