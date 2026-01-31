package dao;

import model.Entrenador;
import org.hibernate.Session;

import utils.HibernateUtils;

public class EntrenadorDAO {
    public void registrarEntrenador(Entrenador entrenador) {
        Session session = new HibernateUtils().getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(entrenador);
        System.out.println("El entrenador ha sido registrado");
        session.getTransaction().commit();

        session.close();
    }

}