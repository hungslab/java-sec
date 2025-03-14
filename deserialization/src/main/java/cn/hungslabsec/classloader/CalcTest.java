package cn.hungslabsec.classloader;

import java.io.IOException;

public class CalcTest{
    public void calc(){
        try {
            Runtime.getRuntime().exec("calc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}