package com.infoshareacademy;

import com.infoshareacademy.searchengine.dao.UsersRepositoryDaoRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws NamingException {

        System.out.println( "Hello from console!" );
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put("jboss.naming.client.ejb.context", "true");
        properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        properties.put(Context.SECURITY_PRINCIPAL, "mmil");
        properties.put(Context.SECURITY_CREDENTIALS, "samsung700");
        Context context = new InitialContext(properties);

        UsersRepositoryDaoRemote usersRepository =
                (UsersRepositoryDaoRemote)
                context.lookup("search-engine/UsersRepositoryDaoBean!com.infoshareacademy.searchengine.dao.UsersRepositoryDaoRemote"
                        );

        for (String name : usersRepository.getUsersNames()){
            System.out.println("user: " + name);
        }
    }
}
