package cn.hungslabsec.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {
    public static void main(String[] args) throws ReflectiveOperationException
    {
        String className = "cn.hungslabsec.Reflection.Cat";
        Class cl = Class.forName(className);
        Class supercl = cl.getSuperclass();
        String modifiers = Modifier.toString(cl.getModifiers());
        if (modifiers.length()>0) System.out.print(modifiers+" ");
        System.out.print("class " + className);
        if(supercl !=null && supercl !=Object.class) System.out.print(" extends " + supercl.getName());
        System.out.print("\n{\n");
        printConstructors(cl);

        printMethods(cl);

        printFields(cl);
        System.out.print("}");
    }

    public static void printConstructors(Class cl)
    {
        Constructor[] constructors = cl.getDeclaredConstructors();
        for(Constructor c:constructors)
        {
            String name = c.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(c.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers + " ");
            System.out.print(name + "(");

            Class[] paramTypes = c.getParameterTypes();
            for(int j = 0;j<paramTypes.length;j++)
            {
                if(j>0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
            

        }
    }

    public static void printMethods(Class cl)
    {
        Method[] methods = cl.getDeclaredMethods();
        for(Method m :methods)
        {
            Class retType = m.getReturnType();
            String name = m.getName();

            System.out.print("   ");
            String modifiers = Modifier.toString(m.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers+" ");
            System.out.print(retType.getName() + " " + name + "(");

            Class[] paramTypes = m.getParameterTypes();
            for(int j =0; j<paramTypes.length;j++)
            {
                if(j>0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");

        }
    }

    public static void printFields(Class cl)
    {
        Field[] fields = cl.getDeclaredFields();

        for(Field e:fields)
        {
            Class type = e.getType();
            String name = e.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(e.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers+" ");
            System.out.println(type.getName()+" " + name+ ";");
        }
    }
}
