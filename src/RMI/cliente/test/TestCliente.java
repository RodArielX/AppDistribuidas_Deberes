package RMI.cliente.test;

import javax.swing.SwingUtilities;
import RMI.cliente.vista.Ventana;

public class TestCliente {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ventana ventana = new Ventana();
            ventana.setVisible(true);
        });
    }
}
