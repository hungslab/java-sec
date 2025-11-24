
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class RaspAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain start");
        ClassFileTransformer transformer = new RaspTransformer();
        inst.addTransformer(transformer, true);
    }
}