import java.util.Random;

public class Cliente extends Thread {

	private int id;
	private Random r = new Random();

	public Cliente(int id) {
		this.id = id;
	}

	@Override
	public void run() {

		int ale = 0;

		try {
			Supermercado.tienda.acquire();
			System.out.println("El cliente " + id + " ha entrado en la tienda");
			Thread.sleep(3000);

			ale = (int) (Math.random() * 3 + 1);

			switch (ale) {

			case 1:
				Supermercado.c1.acquire();
				System.out.println("El cliente " + id + " ha entrado en la caja 1");
				Supermercado.caja1 += r.nextDouble(100) + 1;
				Supermercado.c1.release();
				System.out.println("El cliente " + id + " ha salido de la caja 1");
				break;

			case 2:
				Supermercado.c2.acquire();
				System.out.println("El cliente " + id + " ha entrado en la caja 2");
				Supermercado.caja2 += r.nextDouble(100) + 1;
				Supermercado.c2.release();
				System.out.println("El cliente " + id + " ha salido de la caja 2");
				break;

			case 3:
				Supermercado.c3.acquire();
				System.out.println("El cliente " + id + " ha entrado en la caja 3");
				Supermercado.caja3 += r.nextDouble(100) + 1;
				Supermercado.c3.release();
				System.out.println("El cliente " + id + " ha salido de la caja 3");
				break;
			}

			Supermercado.tienda.release();
			System.out.println("El cliente " + id + " ha salido del supermercado");

		} catch (InterruptedException e) {
			System.out.println("Error en el acceso");
		}
	}
}
