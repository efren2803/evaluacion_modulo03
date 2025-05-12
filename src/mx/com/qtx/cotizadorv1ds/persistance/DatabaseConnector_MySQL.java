package mx.com.qtx.cotizadorv1ds.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para obtener conexiones a la base de datos.
 * ¡ADAPTAR CON CREDENCIALES Y CONFIGURACIÓN REALES!
 */
public class DatabaseConnector_MySQL {

    // Reemplaza con tu URL, usuario y contraseña de MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/diparq_modulo03_evaluacion?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "S3rv3rM1Sql@2819";

    // Cargar el driver JDBC (solo necesario una vez)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC de MySQL no encontrado.");
            // Considera lanzar una RuntimeException aquí para detener la aplicación si el driver falta
            // throw new RuntimeException("Driver JDBC de MySQL no encontrado", e);
        }
    }

    /**
     * Obtiene una nueva conexión a la base de datos.
     * @return Una conexión JDBC.
     * @throws SQLException Si ocurre un error al conectar.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
