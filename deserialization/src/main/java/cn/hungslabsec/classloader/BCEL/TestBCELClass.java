//package cn.hungslabsec.classloader.BCEL;
//
//import java.io.IOException;
//
//public class TestBCELClass {
//
//    static {
//        String command = "open -a Calculator.app";
//        String osName  = System.getProperty("os.name");
//
//        if (osName.startsWith("Windows")) {
//            command = "calc";
//        } else if (osName.startsWith("Linux")) {
//            command = "curl localhost:9999/";
//        }
//
//        try {
//            Runtime.getRuntime().exec(command);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}