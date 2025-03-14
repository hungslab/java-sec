package cn.hungslabsec.classloader;

import java.io.IOException;

public class EvilTest {

    public static Process exec(String cmd) throws IOException {
        return Runtime.getRuntime().exec(cmd);
    }
}