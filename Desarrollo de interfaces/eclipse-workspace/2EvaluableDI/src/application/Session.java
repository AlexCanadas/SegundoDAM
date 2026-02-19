package application;

public class Session {
	private static int userId;
	private static String nombre;
	private static String cargo;

	public static int getUserId() {
		return userId;
	}

	public static String getNombre() {
		return nombre;
	}

	public static String getCargo() {
		return cargo;
	}

	public static void setSession(int id, String nom, String car) {
		userId = id;
		nombre = nom;
		cargo = car;
	}

	public static void clear() {
		userId = 0;
		nombre = null;
		cargo = null;
	}

}
