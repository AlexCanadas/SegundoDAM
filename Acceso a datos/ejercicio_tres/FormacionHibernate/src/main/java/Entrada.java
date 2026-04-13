import database.HibernateUtil;
import org.hibernate.Session;
import model.Usuario;

public class Entrada {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Usuario usuario = new Usuario("Alejandro", "Cañadas", 123456789);
        session.persist(usuario);

        session.getTransaction().commit();
        session.close();

        System.out.println("Usuario insertado correctamente");
    }

}