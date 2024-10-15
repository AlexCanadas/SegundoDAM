import java.io.File;
import java.io.IOException;

public class Entrada {

    public static void main(String[] args) {
        /*GestionFicheros gestionFicheros = new GestionFicheros();
        gestionFicheros.trabajoFicherosBase();*/

        File carpetaCodigosUE = new File("/Users/Alex/Desktop/GitHub");
        GestionFicheros.listarArchivos(carpetaCodigosUE);
    }
}
