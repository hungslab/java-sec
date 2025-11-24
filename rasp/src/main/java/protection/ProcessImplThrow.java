package protection;

import java.util.Arrays;

public class ProcessImplThrow {
    public static void protect(String[] cmd) {
        System.out.println("Evil Command: " + Arrays.toString(cmd));
        throw new RuntimeException("protected by rasp :)");
    }
}