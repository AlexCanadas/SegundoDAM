package app;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        System.out.println("Insertando un usuario en la BD...");

        Usuario u = new Usuario("Alex", "Perez", 666777888);

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(u);
            tx.commit();

            System.out.println("Insertado con id: " + u.getId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
