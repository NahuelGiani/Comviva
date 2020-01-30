package ar.gob.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                sessionFactory = config.configure("hibernate.xml").buildSessionFactory();
            } catch (HibernateException he) {
                System.out.println("Hubo un error al intentar crear el session factory");
            }
        }
        return sessionFactory;
    }
    
}
