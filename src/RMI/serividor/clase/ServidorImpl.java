package RMI.serividor.clase;

import RMI.serividor.conexionBDD.Consulta;

import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.rmi.RemoteException;

public class ServidorImpl extends UnicastRemoteObject implements Servidor {

    public ServidorImpl() throws RemoteException {
        super();
    }

    @Override
    public int insertar(Persona p) throws RemoteException {
        int resultado = 0;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db/empleados.db")) {
            String sql = "INSERT INTO empleados(id, nombre, correo, cargo, salario) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p.getId());        // <- se usa el ID proporcionado
            stmt.setString(2, p.getNombre());
            stmt.setString(3, p.getCorreo());
            stmt.setString(4, p.getCargo());
            stmt.setDouble(5, p.getSalario());

            resultado = stmt.executeUpdate(); // devuelve 1 si se insertó

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public Persona buscar(int id) {
        String sql = "SELECT * FROM empleados WHERE id = ?";
        try (Connection conn = Consulta.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("cargo"),
                        rs.getDouble("salario")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar: " + ex.getMessage());
        }
        return null; // No encontrado
    }

    @Override
    public String actualizar(Persona e) {
        String sql = "UPDATE empleados SET nombre = ?, correo = ?, cargo = ?, salario = ? WHERE id = ?";
        try (Connection conn = Consulta.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getCorreo());
            stmt.setString(3, e.getCargo());
            stmt.setDouble(4, e.getSalario());
            stmt.setInt(5, e.getId());
            int filas = stmt.executeUpdate();
            return filas > 0 ? "Empleado actualizado." : "Empleado no encontrado.";
        } catch (SQLException ex) {
            return "Error al actualizar: " + ex.getMessage();
        }
    }

    @Override
    public String eliminar(int id) {
        String sql = "DELETE FROM empleados WHERE id = ?";
        try (Connection conn = Consulta.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0 ? "Empleado eliminado." : "Empleado no encontrado.";
        } catch (SQLException ex) {
            return "Error al eliminar: " + ex.getMessage();
        }
    }
}
