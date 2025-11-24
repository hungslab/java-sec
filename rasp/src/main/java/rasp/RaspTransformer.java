import com.demo.rasp.adpator.ProcessImplAdaptor;
import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class RaspTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (className.equals("java/lang/ProcessImpl")) {
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            ClassVisitor cv = new ProcessImplAdaptor(ASM9, cw);
            cr.accept(cv, ClassReader.SKIP_DEBUG | ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        }
        return classfileBuffer;
    }
}
