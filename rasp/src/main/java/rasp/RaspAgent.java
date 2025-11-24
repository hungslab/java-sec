package rasp;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.jar.JarFile;

public class RaspAgent {
    public static void premain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {
        System.out.println("premain start");
        addJarToBootStrap(inst);
        inst.addTransformer(new RaspTransformer(), true);
    }

    public static void addJarToBootStrap(Instrumentation inst) {
        URL localUrl = RaspAgent.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            String path = URLDecoder.decode(
                    localUrl.getFile().replace("+", "%2B"), "UTF-8");
            System.out.println(path);
            inst.appendToBootstrapClassLoaderSearch(new JarFile(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}