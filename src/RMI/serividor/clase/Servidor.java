package RMI.serividor.clase;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Servidor extends Remote {
    int insertar(Persona e) throws RemoteException;
    Persona buscar(int id) throws RemoteException;
    String actualizar(Persona e) throws RemoteException;
    String eliminar(int id) throws RemoteException;
}
