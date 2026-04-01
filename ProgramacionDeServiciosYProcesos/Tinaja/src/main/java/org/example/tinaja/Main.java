package org.example.tinaja;

public class Main {
//    En el campo de calatrava los regantes de la zona utilizan una tinaja para regar los campos de cultivo.
//    Dicha tinaja es llenada a ritmo de 10 litros por segundo por un motor. Cuando la tinaja llega a los 900 litros,
//    se activa un proceso de vaciado a 5 litros por segundo, mientras sigue llenándose a 10 litros por segundo.

    Cuando la tinaja llega a los 1000 litros, el proceso de llenado se para y el proceso de vaciado pasa a 10 litros por segundo. Cuando la tinaja llega a los 100 litros de vaciado, se activa un proceso de llenado a un ritmo de 5 litros por segundo, mientras sigue el proceso de vaciado a 10 litros por segundo. Cuando la tinaja llega a los 0 litros, el proceso de vaciado se para y se aumenta el de llenado a 10 litros por segundo. Simula los procesos y ve representando en pantalla las acciones que se van tomando.

    public static void main(String[] args) {
        Tinaja tinaja = new Tinaja();

        // Se crean los dos hilos
        Llenado llenado = new Llenado(tinaja);
        Vaciado vaciado = new Vaciado(tinaja);

        // Arrancan los hilos
        llenado.start();
        vaciado.start();
    }
}
