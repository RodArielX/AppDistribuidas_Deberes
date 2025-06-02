package RMI.cliente.clase;

import RMI.serividor.clase.Persona;
import RMI.serividor.clase.Servidor;

import java.rmi.Naming;

public class Cliente {
    private static Servidor servicio;

    static {
        try {
            servicio = (Servidor) Naming.lookup("rmi://localhost/Datos");
        } catch (Exception e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }

    public static int insertar(Persona e) throws Exception {
        return servicio.insertar(e);
    }

    public static Persona buscar(int id) throws Exception {
        return servicio.buscar(id);
    }

    public static String actualizar(Persona e) throws Exception {
        return servicio.actualizar(e);
    }

    public static String eliminar(int id) throws Exception {
        return servicio.eliminar(id);
    }
}