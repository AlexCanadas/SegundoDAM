import sys
import mysql.connector
from PyQt5 import uic
from PyQt5.QtWidgets import (
    QApplication,
    QWidget,
    QMessageBox,
    QTableWidgetItem
)
from PyQt5.QtWidgets import QAbstractItemView


HOST = "127.0.0.1"
PORT = 41063
USER = "root"
PASSWORD = "root"
DATABASE = "universidad"


def conectar_bd():
    return mysql.connector.connect(
        host=HOST,
        port=PORT,
        user=USER,
        password=PASSWORD,
        database=DATABASE
    )


class VentanaProfesor(QWidget):
    def __init__(self, profesor_id, profesor_nombre):
        super().__init__()
        uic.loadUi("ui/profesor.ui", self)

        self.profesor_id = profesor_id
        self.profesor_nombre = profesor_nombre

        self.btnCerrar.clicked.connect(self.close)
        self.btnVolver.clicked.connect(self.volver)
        self.btnGuardar.clicked.connect(self.guardar_nota)

        self.comboModulo.currentIndexChanged.connect(self.cargar_alumnos)
        self.comboAlumno.currentIndexChanged.connect(self.cargar_nota_actual)

        self.cargar_modulos()

    def volver(self):
        self.login = VentanaLogin()
        self.login.show()
        self.close()

    def cargar_modulos(self):
        self.comboModulo.clear()

        try:
            conexion = conectar_bd()
            cursor = conexion.cursor()

            cursor.execute("""
                SELECT m.id, m.nombre
                FROM modulos m
                INNER JOIN profesor_modulo pm ON m.id = pm.modulo_id
                WHERE pm.profesor_id = %s
                ORDER BY m.nombre
            """, (self.profesor_id,))

            modulos = cursor.fetchall()
            conexion.close()

            for modulo_id, nombre in modulos:
                self.comboModulo.addItem(nombre, modulo_id)

            self.cargar_alumnos()

        except Exception as e:
            QMessageBox.critical(self, "Error BD", str(e))

    def cargar_alumnos(self):
        self.comboAlumno.clear()
        self.txtNota.clear()

        modulo_id = self.comboModulo.currentData()
        if modulo_id is None:
            return

        try:
            conexion = conectar_bd()
            cursor = conexion.cursor()

            cursor.execute("""
                SELECT a.id, a.nombre
                FROM alumnos a
                INNER JOIN matriculas m ON a.id = m.alumno_id
                WHERE m.modulo_id = %s
                ORDER BY a.nombre
            """, (modulo_id,))

            alumnos = cursor.fetchall()
            conexion.close()

            for alumno_id, nombre in alumnos:
                self.comboAlumno.addItem(nombre, alumno_id)

            self.cargar_nota_actual()

        except Exception as e:
            QMessageBox.critical(self, "Error BD", str(e))

    def cargar_nota_actual(self):
        self.txtNota.clear()

        alumno_id = self.comboAlumno.currentData()
        modulo_id = self.comboModulo.currentData()

        if alumno_id is None or modulo_id is None:
            return

        try:
            conexion = conectar_bd()
            cursor = conexion.cursor()

            cursor.execute("""
                SELECT nota
                FROM notas
                WHERE alumno_id = %s AND modulo_id = %s
            """, (alumno_id, modulo_id))

            resultado = cursor.fetchone()
            conexion.close()

            if resultado:
                self.txtNota.setText(str(resultado[0]))
            else:
                self.txtNota.setText("")

        except Exception as e:
            QMessageBox.critical(self, "Error BD", str(e))

    def guardar_nota(self):
        alumno_id = self.comboAlumno.currentData()
        modulo_id = self.comboModulo.currentData()
        nota_texto = self.txtNota.text().strip()

        if alumno_id is None or modulo_id is None or nota_texto == "":
            QMessageBox.warning(self, "Error", "Completa todos los datos")
            return

        try:
            nota = float(nota_texto)
        except ValueError:
            QMessageBox.warning(self, "Error", "La nota debe ser numérica")
            return

        try:
            conexion = conectar_bd()
            cursor = conexion.cursor()

            cursor.execute("""
                SELECT id
                FROM notas
                WHERE alumno_id = %s AND modulo_id = %s
            """, (alumno_id, modulo_id))
            existe = cursor.fetchone()

            if existe:
                cursor.execute("""
                    UPDATE notas
                    SET nota = %s
                    WHERE alumno_id = %s AND modulo_id = %s
                """, (nota, alumno_id, modulo_id))
            else:
                cursor.execute("""
                    INSERT INTO notas (alumno_id, modulo_id, nota)
                    VALUES (%s, %s, %s)
                """, (alumno_id, modulo_id, nota))

            conexion.commit()
            conexion.close()

            QMessageBox.information(self, "Correcto", "Nota guardada correctamente")
            self.cargar_nota_actual()

        except Exception as e:
            QMessageBox.critical(self, "Error BD", str(e))


class VentanaAlumno(QWidget):
    def __init__(self, alumno_id, alumno_nombre):
        super().__init__()
        uic.loadUi("ui/alumno.ui", self)

        self.alumno_id = alumno_id
        self.alumno_nombre = alumno_nombre

        self.btnCerrar.clicked.connect(self.close)
        self.btnVolver.clicked.connect(self.volver)
        self.btnVer.clicked.connect(self.ver_notas)

        self.tablaNotas.setEditTriggers(QAbstractItemView.NoEditTriggers)

        self.cargar_modulos()

    def volver(self):
        self.login = VentanaLogin()
        self.login.show()
        self.close()

    def cargar_modulos(self):
        self.comboModulo.clear()
        self.comboModulo.addItem("Todos", 0)

        try:
            conexion = conectar_bd()
            cursor = conexion.cursor()

            cursor.execute("""
                SELECT mo.id, mo.nombre
                FROM modulos mo
                INNER JOIN matriculas ma ON mo.id = ma.modulo_id
                WHERE ma.alumno_id = %s
                ORDER BY mo.nombre
            """, (self.alumno_id,))

            modulos = cursor.fetchall()
            conexion.close()

            for modulo_id, nombre in modulos:
                self.comboModulo.addItem(nombre, modulo_id)

        except Exception as e:
            QMessageBox.critical(self, "Error BD", str(e))

    def ver_notas(self):
        modulo_id = self.comboModulo.currentData()

        try:
            conexion = conectar_bd()
            cursor = conexion.cursor()

            if modulo_id == 0:
                cursor.execute("""
                    SELECT mo.nombre, n.nota
                    FROM notas n
                    INNER JOIN modulos mo ON n.modulo_id = mo.id
                    WHERE n.alumno_id = %s
                    ORDER BY mo.nombre
                """, (self.alumno_id,))
            else:
                cursor.execute("""
                    SELECT mo.nombre, n.nota
                    FROM notas n
                    INNER JOIN modulos mo ON n.modulo_id = mo.id
                    WHERE n.alumno_id = %s AND n.modulo_id = %s
                    ORDER BY mo.nombre
                """, (self.alumno_id, modulo_id))

            resultados = cursor.fetchall()
            conexion.close()

            self.tablaNotas.setRowCount(len(resultados))
            self.tablaNotas.setColumnCount(2)
            self.tablaNotas.setHorizontalHeaderLabels(["Módulo", "Nota"])

            fila = 0
            for nombre_modulo, nota in resultados:
                item_modulo = QTableWidgetItem(str(nombre_modulo))
                item_nota = QTableWidgetItem(str(nota))

                self.tablaNotas.setItem(fila, 0, item_modulo)
                self.tablaNotas.setItem(fila, 1, item_nota)
                fila += 1

        except Exception as e:
            QMessageBox.critical(self, "Error BD", str(e))


class VentanaLogin(QWidget):
    def __init__(self):
        super().__init__()
        uic.loadUi("ui/login.ui", self)

        self.cargar_cargos()

        self.btnSalir.clicked.connect(self.salir)
        self.btnLimpiar.clicked.connect(self.limpiar)
        self.btnEntrar.clicked.connect(self.entrar)

    def cargar_cargos(self):
        self.comboCargo.clear()
        self.comboCargo.addItem("Profesor")
        self.comboCargo.addItem("Alumno")

    def salir(self):
        self.close()

    def limpiar(self):
        self.txtUsuario.clear()
        self.txtContrasena.clear()

    def entrar(self):
        usuario = self.txtUsuario.text().strip()
        password = self.txtContrasena.text().strip()
        cargo = self.comboCargo.currentText()

        if usuario == "" or password == "":
            QMessageBox.warning(self, "Error", "Introduce usuario y contraseña")
            return

        try:
            conexion = conectar_bd()
            cursor = conexion.cursor()

            if cargo == "Profesor":
                cursor.execute("""
                    SELECT id, nombre
                    FROM profesores
                    WHERE usuario = %s AND password = %s
                """, (usuario, password))

                resultado = cursor.fetchone()

                if resultado:
                    profesor_id, nombre = resultado
                    QMessageBox.information(self, "Correcto", f"Bienvenido profesor {nombre}")
                    self.ventana_profesor = VentanaProfesor(profesor_id, nombre)
                    self.ventana_profesor.show()
                    self.close()
                else:
                    QMessageBox.warning(self, "Error", "Datos incorrectos")

            else:
                cursor.execute("""
                    SELECT id, nombre
                    FROM alumnos
                    WHERE usuario = %s AND password = %s
                """, (usuario, password))

                resultado = cursor.fetchone()

                if resultado:
                    alumno_id, nombre = resultado
                    QMessageBox.information(self, "Correcto", f"Bienvenido alumno {nombre}")
                    self.ventana_alumno = VentanaAlumno(alumno_id, nombre)
                    self.ventana_alumno.show()
                    self.close()
                else:
                    QMessageBox.warning(self, "Error", "Datos incorrectos")

            conexion.close()

        except Exception as e:
            QMessageBox.critical(self, "Error BD", str(e))


if __name__ == "__main__":
    app = QApplication(sys.argv)
    ventana = VentanaLogin()
    ventana.show()
    sys.exit(app.exec_())