package app;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        System.out.println("Insertando una habitacion y un cliente...");

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Habitacion h = new Habitacion(101, 1, 2);
            session.persist(h);

            Cliente c = new Cliente("Ana", "Lopez", 123456789, "Ciudad Real", h);
            session.persist(c);

            tx.commit();

            System.out.println("Habitacion id: " + h.getId());
            System.out.println("Cliente id: " + c.getId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
