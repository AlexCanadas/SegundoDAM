package org.example.cuentabancariaservidoruniversidad;

import java.io.*;

public class Cuenta {
    private final String fichero = "saldo.txt";

    public synchronized int getSaldo() {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return 0; // En caso de error, se asume saldo 0
        }
    }

    public synchronized void ingresar(int cantidad) {
        int saldo = getSaldo();
        saldo += cantidad;
        guardarSaldo(saldo);
        System.out.println("Ingreso de " + cantidad + " realizado. Nuevo saldo: " + saldo);
    }

    public synchronized void retirar(int cantidad) {
        int saldo = getSaldo();

        if (saldo >= cantidad) {
            saldo -= cantidad;
            guardarSaldo(saldo);
            System.out.println("Retiro de " + cantidad + " realizado. Nuevo saldo: " + saldo);
        } else {
            System.out.println("Saldo insuficiente para retirar " + cantidad + ". Saldo actual: " + saldo);
        }
    }

    private void guardarSaldo(int saldo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            bw.write(String.valueOf(saldo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
