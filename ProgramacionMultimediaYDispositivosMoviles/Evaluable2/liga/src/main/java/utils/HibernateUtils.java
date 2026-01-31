package utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            createSessionFactory();
        }
        return sessionFactory;
    }

    private static void createSessionFactory() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (HibernateException e){
            System.out.println("Error en la creación de SessionFactory"+ e.getMessage());
        }
    }


}
