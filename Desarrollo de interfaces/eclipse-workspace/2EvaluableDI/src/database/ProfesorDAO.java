package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {

	public static List<String> obtenerModulosProfesor(int profesorId) {
		List<String> modulos = new ArrayList<>();

		String sql = """
				    SELECT m.nombre
				    FROM profesor_modulo pm
				    JOIN modulos m ON m.id = pm.modulo_id
				    WHERE pm.profesor_id = ?
				    ORDER BY m.nombre
				""";

		try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, profesorId);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				modulos.add(rs.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modulos;
	}

	public static Integer obtenerModuloIdPorNombre(String nombreModulo) {
		String sql = "SELECT id FROM modulos WHERE nombre = ?";
		try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, nombreModulo);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
