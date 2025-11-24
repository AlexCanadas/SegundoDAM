package bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GestionBD {

	// VALIDAR USUARIO
	public static ResultSet validarUsuario(String usuario, String password, String cargo) throws Exception {
		Connection conn = ConexionBD.conectar();
		String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = ? AND cargo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, usuario);
		ps.setString(2, password);
		ps.setString(3, cargo);

		return ps.executeQuery(); // devolvemos el ResultSet
	}

	// MODULOS DEL PROFESOR
	public static ResultSet obtenerModulosProfesor(int idProfesor) throws Exception {
		Connection conn = ConexionBD.conectar();
		String sql = "SELECT m.id, m.nombre_modulo FROM modulos m " + "JOIN profesor_modulo pm ON m.id = pm.id_modulo "
				+ "WHERE pm.id_profesor = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idProfesor);
		return ps.executeQuery();
	}

	// ALUMNOS DE UN MODULO
	public static ResultSet obtenerAlumnosModulo(int idModulo) throws Exception {
		Connection conn = ConexionBD.conectar();
		String sql = "SELECT u.id, u.nombre_usuario, am.nota " + "FROM alumno_modulo am "
				+ "JOIN usuarios u ON am.id_alumno = u.id " + "WHERE am.id_modulo = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idModulo);
		return ps.executeQuery();
	}

	// GUARDAR NOTA DEL ALUMNO
	public static void guardarNota(int idAlumno, int idModulo, double nota) throws Exception {
		Connection conn = ConexionBD.conectar();
		String sql = "UPDATE alumno_modulo SET nota = ? WHERE id_alumno = ? AND id_modulo = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, nota);
		ps.setInt(2, idAlumno);
		ps.setInt(3, idModulo);

		ps.executeUpdate();
	}

	// MODULOS + NOTAS DEL ALUMNO
	public static ResultSet obtenerModulosAlumno(int idAlumno) throws Exception {
		Connection conn = ConexionBD.conectar();
		String sql = "SELECT m.nombre_modulo, am.nota " + "FROM modulos m "
				+ "JOIN alumno_modulo am ON m.id = am.id_modulo " + "WHERE am.id_alumno = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idAlumno);
		return ps.executeQuery();
	}

}
