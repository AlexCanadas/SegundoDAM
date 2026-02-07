package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.AlumnoNotaRow;

public class MatriculasDAO {

	public static List<AlumnoNotaRow> alumnosConNotaEnModulo(int moduloId) {
		List<AlumnoNotaRow> lista = new ArrayList<>();

		String sql = """
				    SELECT a.id AS alumno_id, a.nombre AS alumno, n.nota AS nota
				    FROM matriculas mat
				    JOIN alumnos a ON a.id = mat.alumno_id
				    LEFT JOIN notas n ON n.alumno_id = mat.alumno_id AND n.modulo_id = mat.modulo_id
				    WHERE mat.modulo_id = ?
				    ORDER BY a.nombre
				""";

		try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, moduloId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int alumnoId = rs.getInt("alumno_id");
				String alumno = rs.getString("alumno");
				Double nota = (Double) rs.getObject("nota"); // puede ser null
				lista.add(new AlumnoNotaRow(alumnoId, alumno, nota));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public static void guardarNota(int alumnoId, int moduloId, Double nota) {
		// si existe, UPDATE; si no existe, INSERT
		String update = "UPDATE notas SET nota=? WHERE alumno_id=? AND modulo_id=?";
		String insert = "INSERT INTO notas (nota, alumno_id, modulo_id) VALUES (?,?,?)";

		try (Connection con = DB.getConnection()) {

			// 1) intentamos update
			try (PreparedStatement ps = con.prepareStatement(update)) {
				ps.setDouble(1, nota);
				ps.setInt(2, alumnoId);
				ps.setInt(3, moduloId);

				int filas = ps.executeUpdate();
				if (filas > 0)
					return; // ya estaba, actualizado
			}

			// 2) si no actualizó nada, insert
			try (PreparedStatement ps2 = con.prepareStatement(insert)) {
				ps2.setDouble(1, nota);
				ps2.setInt(2, alumnoId);
				ps2.setInt(3, moduloId);
				ps2.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
