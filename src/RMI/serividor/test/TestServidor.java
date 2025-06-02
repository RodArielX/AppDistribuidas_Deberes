package RMI.serividor.test;

import RMI.serividor.clase.ServidorImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TestServidor {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Naming.rebind("Datos", new ServidorImpl());
            System.out.println("Servidor RMI listo...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
