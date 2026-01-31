import dao.EntrenadorDAO;
import dao.EquipoDAO;
import dao.JugadorDAO;
import dao.LigaDAO;
import model.*;
import org.hibernate.exception.ConstraintViolationException;

public class Entrada {
    public static void main(String[] args){

        EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
        EquipoDAO equipoDAO = new EquipoDAO();
        JugadorDAO jugadorDAO = new JugadorDAO();
        LigaDAO ligaDAO = new LigaDAO();

        try {
            //Crear 1 liga
            ligaDAO.creaLiga(new Liga("Liga UE", "01-01-2024", "31-12-2024"));

            //Crea 3 equipos y asócialos a la liga
            equipoDAO.crearEquipo(new Equipo("Equipo A", "Madrid"));
            equipoDAO.crearEquipo(new Equipo("Equipo B", "Getafe"));
            equipoDAO.crearEquipo(new Equipo("Equipo C", "Barcelona"));

            ligaDAO.asignarEquipoALiga(1, 1);
            ligaDAO.asignarEquipoALiga(2, 1);
            ligaDAO.asignarEquipoALiga(3, 1);

            //Crear 6 jugadores y asociarlos a un equipo

            jugadorDAO.crearJugador(new Jugador("Juan",14.000, 17, "Fracesa"));
            jugadorDAO.crearJugador(new Jugador("Ruben",9.000, 12, "Portuguesa"));
            jugadorDAO.crearJugador(new Jugador("Antonio",2.500, 4, "Japonesa"));
            jugadorDAO.crearJugador(new Jugador("Raul",10.000, 26, "Española"));
            jugadorDAO.crearJugador(new Jugador("Mariano",16.500, 18, "Noruega"));
            jugadorDAO.crearJugador(new Jugador("Hugo",5.500, 20, "Chilena"));

            //Asignar una posicion a los jugadores
            jugadorDAO.asignarPosicionAjugador(1, 1);
            jugadorDAO.asignarPosicionAjugador(2, 2);
            jugadorDAO.asignarPosicionAjugador(3, 3);
            jugadorDAO.asignarPosicionAjugador(4, 4);
            jugadorDAO.asignarPosicionAjugador(5, 5);
            jugadorDAO.asignarPosicionAjugador(6, 6);

            //Asociar jugadores a un equipo
            jugadorDAO.ficharJugadorAEquipo(1, 1);
            jugadorDAO.ficharJugadorAEquipo(2, 1);
            jugadorDAO.ficharJugadorAEquipo(3, 2);
            jugadorDAO.ficharJugadorAEquipo(4, 2);
            jugadorDAO.ficharJugadorAEquipo(5, 3);
            jugadorDAO.ficharJugadorAEquipo(6, 3);


            //Ficha un par de jugadores para otro equipo
            jugadorDAO.ficharJugadorAEquipo(2, 2);
            jugadorDAO.ficharJugadorAEquipo(6, 1);

           // Crea tres entrenadores y asócialos a un equipo
            entrenadorDAO.registrarEntrenador(new Entrenador("Joaquin", 7.0, 9));
            entrenadorDAO.registrarEntrenador(new Entrenador("Javier", 9.5, 13));
            entrenadorDAO.registrarEntrenador(new Entrenador("Carlos", 6.8, 8));

            equipoDAO.asignaEntrenadorAEquipo(1, 1);
            equipoDAO.asignaEntrenadorAEquipo(2, 2);
            equipoDAO.asignaEntrenadorAEquipo(3, 3);

            //Muestra datos de todos los equipos
             equipoDAO.mostrarEquipos();

            //Muestra los jugadores de un equipo
             equipoDAO.mostrarJugadores(2);

            //Muestra los equipos de una liga
             ligaDAO.mostrarEquipoDeLiga(1);

            //Muestra todos los entrenadores de los equipos de una liga
            ligaDAO.mostrarEntrenadoresDeLosEquipos(1);


        }catch (ConstraintViolationException e){
            System.out.println("Error de violación de restricciones"+e.getMessage());
        }catch (Exception ex){
            System.out.println("Error inesperado"+ex.getMessage());
        }
    }
}
