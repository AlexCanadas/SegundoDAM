package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.Nota;

public class NotasDAO {

	public static List<Nota> obtenerNotasAlumno(int alumnoId) {

		List<Nota> lista = new ArrayList<>();

		String sql = """
				    SELECT m.nombre AS modulo, n.nota
				    FROM notas n
				    JOIN modulos m ON m.id = n.modulo_id
				    WHERE n.alumno_id = ?
				""";

		try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, alumnoId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(new Nota(rs.getString("modulo"), rs.getDouble("nota")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
}
