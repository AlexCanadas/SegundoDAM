package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {

	public static Integer loginAlumno(String usuario, String password) {
		String sql = "SELECT id FROM alumnos WHERE usuario=? AND password=?";
		try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, usuario);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Integer loginProfesor(String usuario, String password) {
		String sql = "SELECT id FROM profesores WHERE usuario=? AND password=?";
		try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, usuario);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
