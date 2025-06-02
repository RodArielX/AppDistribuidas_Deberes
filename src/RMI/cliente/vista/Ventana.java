package RMI.cliente.vista;

import RMI.cliente.clase.Cliente;
import RMI.serividor.clase.Persona;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Ventana extends JFrame {
    private JTextField txtId, txtNombre, txtCorreo, txtCargo, txtSalario;
    private JTextArea txtResultado;

    public Ventana() {
        setTitle("CRUD Empleados - Cliente RMI");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color fondo = new Color(245, 245, 245);
        Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 14);
        Font fuenteTitulos = new Font("Segoe UI", Font.BOLD, 16);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(fondo);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Panel campos
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBackground(fondo);
        panelCampos.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Datos del Empleado",
                TitledBorder.LEFT, TitledBorder.TOP,
                fuenteTitulos, Color.DARK_GRAY));

        // Campos
        c.gridx = 0; c.gridy = 0;
        panelCampos.add(new JLabel("ID:"), c);
        txtId = new JTextField(10); txtId.setFont(fuenteCampos);
        c.gridx = 1;
        panelCampos.add(txtId, c);

        c.gridx = 0; c.gridy = 1;
        panelCampos.add(new JLabel("Nombre:"), c);
        txtNombre = new JTextField(20); txtNombre.setFont(fuenteCampos);
        c.gridx = 1;
        panelCampos.add(txtNombre, c);

        c.gridx = 0; c.gridy = 2;
        panelCampos.add(new JLabel("Correo:"), c);
        txtCorreo = new JTextField(20); txtCorreo.setFont(fuenteCampos);
        c.gridx = 1;
        panelCampos.add(txtCorreo, c);

        c.gridx = 0; c.gridy = 3;
        panelCampos.add(new JLabel("Cargo:"), c);
        txtCargo = new JTextField(20); txtCargo.setFont(fuenteCampos);
        c.gridx = 1;
        panelCampos.add(txtCargo, c);

        c.gridx = 0; c.gridy = 4;
        panelCampos.add(new JLabel("Salario:"), c);
        txtSalario = new JTextField(10); txtSalario.setFont(fuenteCampos);
        c.gridx = 1;
        panelCampos.add(txtSalario, c);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(fondo);
        panelBotones.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Acciones",
                TitledBorder.LEFT, TitledBorder.TOP,
                fuenteTitulos, Color.DARK_GRAY));

        JButton btnInsertar = new JButton("Insertar");
        JButton btnConsultar = new JButton("Consultar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        btnInsertar.setBackground(new Color(0, 153, 76));
        btnInsertar.setForeground(Color.WHITE);
        btnConsultar.setBackground(new Color(0, 102, 204));
        btnConsultar.setForeground(Color.WHITE);
        btnActualizar.setBackground(new Color(255, 153, 0));
        btnActualizar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(204, 0, 0));
        btnEliminar.setForeground(Color.WHITE);

        panelBotones.add(btnInsertar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        // Área de resultados
        txtResultado = new JTextArea(8, 45);
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtResultado.setEditable(false);
        txtResultado.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Resultado",
                TitledBorder.LEFT, TitledBorder.TOP,
                fuenteTitulos, Color.DARK_GRAY));

        // Agregar al panel principal
        c.gridx = 0; c.gridy = 0;
        panel.add(panelCampos, c);
        c.gridy = 1;
        panel.add(panelBotones, c);
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        panel.add(scroll, c);


        add(panel);

        // === Lógica de botones ===

        // Insertar
        btnInsertar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                String cargo = txtCargo.getText();
                double salario = Double.parseDouble(txtSalario.getText());

                Persona persona = new Persona(id, nombre, correo, cargo, salario);
                int resultado = Cliente.insertar(persona);
                if (resultado > 0) {
                    txtResultado.setText(" Empleado ingresado con ID: " + id);
                } else {
                    txtResultado.setText(" No se pudo insertar el empleado.");
                }
            } catch (Exception ex) {
                txtResultado.setText(" Error al insertar: " + ex.getMessage());
            }
        });

        // Consultar
        btnConsultar.addActionListener(e -> {
            try {
                String idText = txtId.getText().trim();
                if (idText.isEmpty()) {
                    txtResultado.setText(" Por favor ingresa un ID.");
                    return;
                }
                int id = Integer.parseInt(idText);
                Persona p = Cliente.buscar(id);
                if (p != null) {
                    txtResultado.setText("🔍 Empleado encontrado:\n" +
                            "ID: " + p.getId() + "\n" +
                            "Nombre: " + p.getNombre() + "\n" +
                            "Correo: " + p.getCorreo() + "\n" +
                            "Cargo: " + p.getCargo() + "\n" +
                            "Salario: " + p.getSalario());

                    txtNombre.setText(p.getNombre());
                    txtCorreo.setText(p.getCorreo());
                    txtCargo.setText(p.getCargo());
                    txtSalario.setText(String.valueOf(p.getSalario()));
                } else {
                    txtResultado.setText(" Empleado no encontrado.");
                }
            } catch (NumberFormatException ex) {
                txtResultado.setText(" El ID debe ser un número.");
            } catch (Exception ex) {
                txtResultado.setText(" Error al buscar: " + ex.getMessage());
            }
        });

        // Actualizar
        btnActualizar.addActionListener(e -> {
            try {
                String idText = txtId.getText().trim();
                String salarioText = txtSalario.getText().trim();

                if (idText.isEmpty() || salarioText.isEmpty()) {
                    txtResultado.setText(" El ID y el salario son obligatorios.");
                    return;
                }

                int id = Integer.parseInt(idText);
                double salario = Double.parseDouble(salarioText);
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                String cargo = txtCargo.getText();

                Persona p = new Persona(id, nombre, correo, cargo, salario);
                String mensaje = Cliente.actualizar(p);
                txtResultado.setText(mensaje);
            } catch (NumberFormatException ex) {
                txtResultado.setText(" ID y Salario deben ser números válidos.");
            } catch (Exception ex) {
                txtResultado.setText("Error al actualizar: " + ex.getMessage());
            }
        });

        // Eliminar
        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String mensaje = Cliente.eliminar(id);
                txtResultado.setText(mensaje);
            } catch (Exception ex) {
                txtResultado.setText(" Error al eliminar: " + ex.getMessage());
            }
        });
    }
}

