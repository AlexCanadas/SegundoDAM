import java.io.File;
import java.io.IOException;

public class GestionFicheros {


    public void trabajoFicherosBase() {
        // FILE -> ruta (absolutas o relativas)
        // WINDOWS -> C:\\asasd\\asdasd\\asdasd
        // fisico y logico
        File file = new File("src/resources/ficheros/ejemplo.txt");
        // logico
        File carpeta = new File("src/resources/ficheros");
        if (!file.exists()) {
            // CREARLO
            try {
                file.createNewFile(); // logico -> fisico
                System.out.println(file.getName());
                System.out.println(file.getAbsolutePath());
                System.out.println(file.canRead());
                System.out.println(file.canWrite());
                System.out.println(file.isFile());
                System.out.println(file.isHidden());
            } catch (IOException e) {
                System.out.println("Error en la creacion del fichero");
            }

        }
        if (!carpeta.exists()) {
            // excepcion IO no por:
            carpeta.mkdir();
        }

        // carpeta.list();  // String[] nombre de los ficheros que estan dentro
        /*for ( String item : carpeta.list() ) {
            System.out.println(item);
        }*/
        // carpeta.listFiles() // File[] ficheros que estan dentro
        // System.out.println(carpeta.list()[1]);
        /*for ( File item : carpeta.listFiles() ) {
            System.out.println(item.getName());
        }*/

        // sacar el nombre de todas las carpetas y ficheros de una ruta
        // de forma RECURSIVO -> hasta que no encuentre mas carpetas
        File carpetaCodigosUE = new File("/Users/Alex/Desktop/GitHub");
        for (File item : carpetaCodigosUE.listFiles()) {
            if (!item.isHidden()){
                if (item.isDirectory()){
                    System.out.println(item.getName());
                    for (File item2 : item.listFiles() ) {
                        System.out.println("\t"+item2.getName());
                        // cuando hago otro recorrido: CARPETA

                    }
                } else {
                    System.out.println(item.getName());
                }

            }
        }

    }

    public static void funcionRecursiva(File dir) {
        // Comprobar si dir es un directorio
        if (dir.isDirectory()) {
            try {
                // Iterar sobre los elementos del directorio que le pasemos
                File[] archivos = dir.listFiles(); // creamos un array de obj File
                if (archivos != null) { // Verificar que no es null
                    for (File item : archivos) { // Recorremos archivos[] con la var item de tipo File
                        if (!item.isHidden()) {
                            if (item.isDirectory()) {
                                System.out.println("\t" + item.getName()); // Imprimir nombre de la carpeta con una tabulación
                                funcionRecursiva(item); // Llamar a la función recursivamente para explorar la carpeta
                            } else {
                                System.out.println("\t\t" + item.getName()); // Imprimir nombre del archivo con 2 tabulaciones
                            }
                        }
                    }
                } else {
                    System.out.println("No se puede acceder al contenido de " + dir.getAbsolutePath());
                }
            } catch (SecurityException e) {
                System.out.println("Acceso denegado a " + dir.getAbsolutePath());
            } catch (Exception e) {
                System.out.println("Ocurrió un error al acceder a " + dir.getAbsolutePath() + ": " + e.getMessage());
            }
        } else {
            System.out.println(dir.getAbsolutePath() + " no es un directorio.");
        }
    }


}
