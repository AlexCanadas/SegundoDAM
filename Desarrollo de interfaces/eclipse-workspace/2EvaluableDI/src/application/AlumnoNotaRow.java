package application;

public class AlumnoNotaRow {
	private int alumnoId;
	private String alumno;
	private Double nota;

	public AlumnoNotaRow(int alumnoId, String alumno, Double nota) {
		this.alumnoId = alumnoId;
		this.alumno = alumno;
		this.nota = nota;
	}

	public int getAlumnoId() {
		return alumnoId;
	}

	public String getAlumno() {
		return alumno;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}
}
