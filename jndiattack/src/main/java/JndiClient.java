import javax.naming.InitialContext;

public class JndiClient {
    public static void main(String[] args) throws Exception {
        new InitialContext().lookup("rmi://127.0.0.1:1099/Object");
//        new InitialContext().lookup("ldap://127.0.0.1:7777/calc");
    }

}




