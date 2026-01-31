package dao;

import model.Entrenador;
import model.Jugador;
import model.Posicion;
import org.hibernate.Session;
import model.Equipo;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class EquipoDAO {
    private Session session;

    public void crearEquipo(Equipo equipo) throws ConstraintViolationException {
        session = new HibernateUtils().getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(equipo);
        System.out.println("El equipo ha sido registrado");
        session.getTransaction().commit();
        session.close();
    }

    public void modificarInfoEquipo(int id, String nuevo_nombre, String nueva_ciudad) {
        session = new HibernateUtils().getSessionFactory().openSession();
        session.beginTransaction();
        Equipo equipo = session.get(Equipo.class, id);
        if (equipo == null) {
            System.out.println("El equipo no existe");
        } else {
            Query query = session.createNamedQuery("Equipo.modifyById");
            query.setParameter("id", id);
            query.setParameter("nuevo_nombre", nuevo_nombre);
            query.setParameter("nueva_ciudad", nueva_ciudad);
            query.executeUpdate();
            System.out.println("El equipo " + equipo.getNombre_equipo() + " ha sido modificado");
        }
        session.getTransaction().commit();
        session.close();
    }


    public void eliminarEquipo(int id) {
        session = new HibernateUtils().getSessionFactory().openSession();
        session.beginTransaction();

        Equipo equipo = session.get(Equipo.class, id);
        if (equipo == null) {
            System.out.println("El equipo no existe");
        } else {
            Query query = session.createNamedQuery("Equipo.deleteById");
            query.setParameter("id", id);
            query.executeUpdate();
            System.out.println("el equipo " + equipo.getNombre_equipo() + " ha sido eliminado");
        }
        session.getTransaction().commit();
        session.close();
    }


    public void mostrarJugadores(int id_Equipo) {
        session = new HibernateUtils().getSessionFactory().openSession();
        session.beginTransaction();
        Equipo equipo = session.get(Equipo.class, id_Equipo);
        if (equipo != null) {
            System.out.println("Jugadores del equipo " + id_Equipo);
            for (Jugador j : equipo.getListaJugadores()) {
                System.out.println(j.getNombre() + " posición " + j.getPosicion());
            }
            session.getTransaction().commit();
        }
        session.close();
    }


    public void asignaEntrenadorAEquipo(int idEntrenador, int idEquipo) {
        session = new HibernateUtils().getSessionFactory().openSession();
        session.beginTransaction();
        Entrenador entrenador = session.get(Entrenador.class, idEntrenador);
        Equipo equipo = session.get(Equipo.class, idEquipo);
        if (entrenador != null && equipo != null) {
            entrenador.setEquipo(equipo);
            session.merge(entrenador);
            System.out.println("El entrenador " + entrenador.getNombre() + " se ha asignado al equipo " + equipo.getNombre_equipo());
        } else {
            System.out.println("El entrenador o equipo no existe");
        }
        session.getTransaction().commit();
        session.close();
    }

    public void mostrarEquipos() {
        session = new HibernateUtils().getSessionFactory().openSession();
        session.beginTransaction();
        List<Equipo> equipos =  session.createNamedQuery("Equipo.showTeams", Equipo.class).getResultList();
        for(Equipo equipo: equipos) {
            System.out.println(equipo);
        }
        session.getTransaction().commit();
        session.close();

    }
}
