package dao;

import model.Equipo;
import model.Liga;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;


public class LigaDAO {
    private Session session;

    public void creaLiga(Liga liga){
        session = new HibernateUtils().getSessionFactory().openSession();
            session.beginTransaction();
            session.persist(liga);
            System.out.println("La liga ha sido creada");
            session.getTransaction().commit();
                session.close();
            }

    public void editarLiga(int id, String nuevonombre, String nuevoinicio, String nuevofin){
        session = new HibernateUtils().getSessionFactory().openSession();
            session.beginTransaction();
            Liga liga = session.get(Liga.class, id);
            if(liga == null){
                System.out.println("La liga no existe");
            }else {
                Query query = session.createNamedQuery("Liga.editById");
                query.setParameter("id", id);
                query.setParameter("nuevonombre", nuevonombre);
                query.setParameter("nuevoinicio", nuevoinicio);
                query.setParameter("nuevofin", nuevofin);
                query.executeUpdate();
                System.out.println("La liga "+liga.getNombre_liga()+" ha sido editada");
                session.getTransaction().commit();
            }
                session.close();
    }

    public void eliminarLiga(int id){
        session = new HibernateUtils().getSessionFactory().openSession();
            session.beginTransaction();
            Liga liga = session.get(Liga.class, id);
            if(liga == null){
                System.out.println("La liga no existe");
            }else {
                Query query = session.createNamedQuery("Liga.deleteById");
                query.setParameter("id", id);
                query.executeUpdate();
                System.out.println("La liga "+liga.getNombre_liga()+" ha sido eliminada");
            }
            session.getTransaction().commit();
                session.close();
        }

    public void consultarLigasDisponibles(){
        session = new HibernateUtils().getSessionFactory().openSession();
            session.beginTransaction();
                List<Liga> ligaList = session.createQuery("FROM Liga", Liga.class).getResultList();
                if(!ligaList.isEmpty()){
                    for (Liga l: ligaList) {
                        System.out.println(l.toString());
                    }
                }else{
                    System.out.println("La liga no existe");
                }
            session.getTransaction().commit();
                session.close();
    }


    public void asignarEquipoALiga(int idEquipo, int idLiga){
        session = new HibernateUtils().getSessionFactory().openSession();
            session.beginTransaction();
            Liga liga = session.get(Liga.class, idLiga);
            Equipo equipo = session.get(Equipo.class, idEquipo);
            if(liga != null && equipo != null){
                liga.getListEquipo().add(equipo);
                equipo.setLiga(liga);
                session.merge(equipo);
                System.out.println("Se ha asignado el  "+equipo.getNombre_equipo()+" a la "+liga.getNombre_liga());
            }else{
                System.out.println("El equipo o liga no existe");
            }
            session.getTransaction().commit();
                session.close();
        }

    public void mostrarEquipoDeLiga(int idLiga){
        session = new HibernateUtils().getSessionFactory().openSession();
            session.beginTransaction();
            Liga liga = session.get(Liga.class, idLiga);
            if(liga != null){
                System.out.println("Equipos de la liga "+liga.getNombre_liga());
                System.out.println(liga.getListEquipo());
            }else{
                System.out.println("La liga no existe");
            }
            session.getTransaction().commit();
                session.close();
        }

    public void mostrarEntrenadoresDeLosEquipos(int idLiga) {
        session = new HibernateUtils().getSessionFactory().openSession();
            session.beginTransaction();
            Liga liga = session.get(Liga.class, idLiga);
            if(liga != null){
                System.out.println("Entrenadores de los equipos de la liga "+liga.getNombre_liga());
                for(Equipo e: liga.getListEquipo()){
                    if(e.getEntrenador() != null){
                        System.out.println(e.getNombre_equipo()+" con Entrenador "+e.getEntrenador().getNombre());
                    }else {
                        System.out.println(e.getNombre_equipo()+" No tiene asignado un entrenador");
                    }
                }
            }else{
                System.out.println("La liga no existe");
            }
            session.getTransaction().commit();
                session.close();
        }
    }

