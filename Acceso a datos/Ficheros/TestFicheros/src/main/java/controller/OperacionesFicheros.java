package controller;

import model.Usuario;

import java.io.*;

public class OperacionesFicheros {
    public void lecturaFicheros(String path){
        File file = new File(path);
        FileReader fileReader = null;


        try {
            // Leer letra a letra con fileReader
            fileReader = new FileReader(file);
           int numero;
           while ((numero = fileReader.read()) != -1) {
               System.out.print((char) numero);
           }
          System.out.println((char) numero);

    } catch (IOException e) {
        System.out.println("No puedes hacer la lectura");
    } finally {
        try {
            fileReader.close();

        } catch (IOException | NullPointerException e) {
            System.out.println("Error al cerrar");
        }
    }
}
    public void lecturaFicherosBuffer(String path){
        File file = new File(path);
        BufferedReader bufferedReader = null;

        try {
            // Leer fila a fila con bufferedReader
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea = null;
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println("No puedes hacer la lectura");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar");
            }
        }
    }
    public void escrituraSimple (String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("La creación no se puede dar");
            }
        }
        FileWriter fileWriter = null;
        String mensaje = "Este mensaje ha sido encriptado!";

        try {
            fileWriter = new FileWriter(file, true);
            for (int i = 0; i < mensaje.length(); i++) {
                char letra = mensaje.charAt(i);
                fileWriter.write((int) letra * 9); // Dividir entre 9 para descifrarlo)
            }
        } catch (IOException e) {
            System.out.println("No puedes escribir");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado del fichero");
            }
        }
    }
    public void escrituraCompleja (String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error en el IO");
            }
        }
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
            printWriter.println("Esta es la primera línea");
            printWriter.println("Esta es la segunda línea");
        } catch (IOException e) {
            System.out.println("No hay permisos de IO");
        } finally {
            try {
                printWriter.close();
            } catch (NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }
    public void escrituraObjectos() {
        Usuario usuario = new Usuario();
        usuario.


    }
}
