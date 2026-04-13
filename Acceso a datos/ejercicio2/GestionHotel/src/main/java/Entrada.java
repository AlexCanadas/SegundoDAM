import model.Cliente;
import model.Habitacion;
import org.hibernate.Session;
import utils.HibernateUtils;

public class Entrada {

    public static void main(String[] args) {

        HibernateUtils hibernateUtils = new HibernateUtils();
        Session session = hibernateUtils.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        Habitacion habitacion = new Habitacion(101, 1, 2);
        session.persist(habitacion);

        Cliente cliente = new Cliente("Alejandro", "Cañadas", 123456789, "Madrid", habitacion);
        session.persist(cliente);

        session.getTransaction().commit();
        session.close();

        System.out.println("Habitación y cliente insertados correctamente");
    }
}