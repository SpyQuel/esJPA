package it.unibo.sdls.sampleproject.dao;

import java.rmi.registry.LocateRegistry;
import java.util.Enumeration;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class RegisterMySQL {
        final static int port = 1099;
        public static void main(String[] args) throws Exception{
                try{
                        System.out.println(LocateRegistry.getRegistry("localhost"));
                        
                }
                catch (Exception e){
                        System.out.print("jndi already up");
                }
            ConnectionPoolDataSource dataSource = createDataSource("lorenzo", "abbidubbi");

            InitialContext context = createContext();
            context.rebind("MySQLds", dataSource);

            Enumeration<NameClassPair> list = context.list("");
            while (list.hasMoreElements()){
                NameClassPair ncp = list.nextElement();
                System.out.println(ncp.toString());
            }

            System.out.println("context created!");
        }
        private static InitialContext createContext() throws NamingException {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:"+port);
        InitialContext context = new InitialContext(env);
        return context;
    }

    private static ConnectionPoolDataSource createDataSource(String username, String password) {
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("library");
        return dataSource;
    }
}