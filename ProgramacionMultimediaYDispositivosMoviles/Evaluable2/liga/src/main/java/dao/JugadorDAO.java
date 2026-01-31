package dao;

import model.Equipo;
import model.Jugador;
import model.Posicion;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class JugadorDAO {
    private Session session;
    public void crearJugador(Jugador jugador){
        session = new HibernateUtils().getSessionFactory().openSession();
            session.beginTransaction();
            session.persist(jugador);
            System.out.println("El jugador ha sido registrado");
            session.getTransaction().commit();
                session.close();
    }
    public void ficharJugadorAEquipo(int id_jugador, int id_equipo) {
        session = new HibernateUtils().getSessionFactory().openSession();
        session.beginTransaction();

        Equipo equipo = session.get(Equipo.class, id_equipo);
        Jugador jugador = session.get(Jugador.class, id_jugador);
        int id_posicion = jugador.getPosicion().getId();

        if (equipo != null && jugador != null) {
            Query query1 = session.createQuery("FROM Jugador j WHERE j.equipo.id = :id_equipo AND j.posicion.id = :posicion");
            query1.setParameter("id_equipo", id_equipo);
            query1.setParameter("posicion", id_posicion);
            List<Jugador> posicionList = query1.getResultList();

            //Si no hay un jugador con la misma posición
            if (posicionList.isEmpty()) {
                jugador.setEquipo(equipo);
                session.merge(jugador);

                jugador.setEquipo(equipo);
                session.merge(jugador);
                //System.out.println("El jugador " + jugador.getNombre() + " ha sido fichado en " + equipo.getNombre_equipo() + " en la posición " + jugador.getPosicion().getPosicion());
                System.out.println("Jugadores fichados correctamente en nuevos equipos");
            } else {
                System.out.println("Ya existe un jugador con la posición " + jugador.getPosicion().getPosicion()+ " en este equipo");
            }
        } else {
            System.out.println("Equipo o jugador no existe");
        }
        session.getTransaction().commit();
        session.close();
    }
    public void asignarPosicionAjugador(int id_jugador, int id_posicion){
        session = new HibernateUtils().getSessionFactory().openSession();
        Jugador jugador = session.get(Jugador.class, id_jugador);
        Posicion posicion = session.get(Posicion.class, id_posicion);
        session.beginTransaction();
        if(jugador == null){
            System.out.println("No existe el jugador con ID: " + id_jugador);
        }else if(posicion == null){
            System.out.println("No existe la posición con ID: " + id_posicion);
        }else{
            jugador.setPosicion(posicion);
            session.merge(jugador);
            System.out.println("El jugador "+jugador.getNombre()+" está en la posicion "+posicion.getPosicion());
        }
        session.getTransaction().commit();
        session.close();
    }

}
