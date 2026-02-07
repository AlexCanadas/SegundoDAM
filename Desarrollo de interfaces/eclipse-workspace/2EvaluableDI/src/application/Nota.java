package application;

public class Nota {

	private String modulo;
	private double nota;

	public Nota(String modulo, double nota) {
		this.modulo = modulo;
		this.nota = nota;
	}

	public String getModulo() {
		return modulo;
	}

	public double getNota() {
		return nota;
	}
}
