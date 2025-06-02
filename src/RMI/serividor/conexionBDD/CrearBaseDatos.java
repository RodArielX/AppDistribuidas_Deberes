package RMI.serividor.conexionBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CrearBaseDatos {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:db/empleados.db";

        String sql = """
            CREATE TABLE IF NOT EXISTS empleados (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                correo TEXT NOT NULL,
                cargo TEXT NOT NULL,
                salario REAL NOT NULL
            );
        """;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("""
                =========================================
                 Base de datos y tabla creadas con éxito
                =========================================
                Ruta: db/empleados.db
                Tabla: empleados
                """);

        } catch (Exception e) {
            System.out.println(" Error al crear la base de datos:");
            e.printStackTrace();
        }
    }
}

