package org.example.baloncesto;

public class Main {
//    En un pabellón de deportes existen 10 pistas para jugar al baloncesto.  Para jugar en partido se
//    precisa un balón. Existe una caja donde están todos los balones. A la hora del partido, el delegado de
//    campo pone 8 balones en dicha caja. Cuando dos equipos quieren jugar un partido mandan a un representante
//    a la caja a por un balón. Si en la caja queda algún balón, el jugador lo coge y puede desarrollarse el
//    partido. Si no queda balón en la caja, el partido debe esperar hasta que alguien deje un balón libre.
//    Cuando se ha terminado el encuentro, uno de los jugadores debe devolver el balón. Si cuando va a la caja
//    se encuentra algún jugador que espera un balón, simplemente se lo da y se vuelve con sus compañeros. Ahora
//    el jugador que esperaba el balón lo toma y puede jugar el partido. Si, por el contrario, cuando se va a
//    dejar un balón en la cesta no hay nadie esperando un balón, simplemente lo deposita en la cesta y continúa

    public static void main(String[] args) {
        Pabellon pabellon = new Pabellon();

        // crear 10 partidos
        for (int i = 1; i <= 10; i++) {
            Partido p = new Partido("Partido " + i, pabellon);
            p.start(); // lanzamos los hilos
        }
    }
}
