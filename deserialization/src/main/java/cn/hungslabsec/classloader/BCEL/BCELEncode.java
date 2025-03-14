//package cn.hungslabsec.classloader.BCEL;
//
//import com.sun.org.apache.bcel.internal.classfile.Utility;
//import org.apache.commons.io.FileUtils;
//
//import java.io.File;
//import java.io.IOException;
//
//public class BCELEncode {
//
//    /**
//     * 将一个Class文件编码成BCEL类
//     *
//     * @param classFile Class文件路径
//     * @return 编码后的BCEL类
//     * @throws IOException 文件读取异常
//     */
//    public static String bcelEncode(File classFile) throws IOException {
//        return "$$BCEL$$" + Utility.encode(FileUtils.readFileToByteArray(classFile), true);
//    }
//}
