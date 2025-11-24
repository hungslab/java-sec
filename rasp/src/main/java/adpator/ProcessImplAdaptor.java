package adpator;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.Opcodes;

public class ProcessImplAdaptor extends ClassVisitor {
    public ProcessImplAdaptor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
        System.out.println("init ProcessImplAdaptor");
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

        if (name.equals("start") && descriptor.equals("()Ljava/lang/Process;")) {
            System.out.println("hooked");
            System.out.println(name + "方法的描述符是：" + descriptor);
            MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
            return new AdviceAdapter(Opcodes.ASM5, mv, access, name, descriptor) {
                @Override
                protected void onMethodEnter() {
                    mv.visitVarInsn(ALOAD, 0);
                    super.visitMethodInsn(INVOKESTATIC, "protection/ProcessImplThrow", "protect", "([Ljava/lang/String;)V", false);
                }
            };
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}