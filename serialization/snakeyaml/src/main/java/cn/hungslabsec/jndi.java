package cn.hungslabsec;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class jndi {
    public static void main(String[] args) throws NamingException {
       new InitialContext().lookup("rmi://127.0.0.1:1099/Object");
//        new InitialContext().lookup("ldap://127.0.0.1:7777/calc");
    }

}





