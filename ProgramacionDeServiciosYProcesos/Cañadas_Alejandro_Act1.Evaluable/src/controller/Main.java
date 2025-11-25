package controller;

import java.util.Scanner;

import models.NumImpares;
import models.NumPares;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce el tiempo de pausa de los hilos (ms): ");
		int tiempo = sc.nextInt();

		// Crear hilos impares
		NumImpares hilo1Impar = new NumImpares("Hilo 1 impar", tiempo);
		NumImpares hilo2Impar = new NumImpares("Hilo 2 impar", tiempo);

		// Crear hilos pares
		NumPares hilo1Par = new NumPares("Hilo 1 par", tiempo);
		NumPares hilo2Par = new NumPares("Hilo 2 par", tiempo);

		// Lanzar todos los hilos
		hilo1Impar.start();
		hilo2Impar.start();
		hilo1Par.start();
		hilo2Par.start();

		try {
			// Esperar a que todos los hilos terminen
			hilo1Impar.join();
			hilo2Impar.join();
			hilo1Par.join();
			hilo2Par.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
