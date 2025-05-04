package D2_Deber_Hilos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Deber Cambio
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ventanaCarrera();
            }
        });

    }
}

class Hilo implements Runnable{
    Thread hilo;
    String nombre;
    JLabel personaje;
    JLabel labelFinal;

    public static int lugar;

    public Hilo(String nombre, JLabel personaje, JLabel labelFinal){
        this.nombre = nombre;
        this.personaje = personaje;
        this.labelFinal = labelFinal;
        hilo = new Thread(this,nombre);
        hilo.start();
    }

    @Override
    public void run(){
        int retardo;

        try {
            lugar = 1;
            retardo = (int)(Math.random()*15) +1;
            labelFinal.setVisible(false);
            personaje.setVisible(true);

            for (int i = 50; i <= 500; i++) {
                personaje.setLocation(i,personaje.getY());
                Thread.sleep(retardo);
            }
            personaje.setVisible(false);
            labelFinal.setVisible(true);

            labelFinal.setText(nombre + "ha llegado en la posicion: " + lugar);
            lugar++;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


class ventanaCarrera extends JFrame{
    public ventanaCarrera(){
        super("Carrera GOD de Leyendas");
        JLabel mario, crash, pikachu, mario_pos, crash_pos, pikachu_pos;
        JButton botonIniciarCarrera;

        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        Image imagen_mario = new ImageIcon("src/D2_Deber_Hilos/Img/mario.gif").getImage();
        ImageIcon icon_mario = new ImageIcon(imagen_mario.getScaledInstance(50,50,Image.SCALE_DEFAULT));
        mario = new JLabel();
        mario.setIcon(icon_mario);
        mario.setBounds(50,50,50,50);

        Image imagen_crash = new ImageIcon("src/D2_Deber_Hilos/Img/crash-bandicoot.gif").getImage();
        ImageIcon icon_crash = new ImageIcon(imagen_crash.getScaledInstance(50,50,Image.SCALE_DEFAULT));
        crash = new JLabel();
        crash.setIcon(icon_crash);
        crash.setBounds(50,100,50,50);

        Image imagen_pikachu = new ImageIcon("src/D2_Deber_Hilos/Img/pikachu.gif").getImage();
        ImageIcon icon_pikachu = new ImageIcon(imagen_pikachu.getScaledInstance(50,50,Image.SCALE_DEFAULT));
        pikachu = new JLabel();
        pikachu.setIcon(icon_pikachu);
        pikachu.setBounds(50,150,50,50);

        mario_pos = new JLabel();
        mario_pos.setBounds(50,50,350,50);

        crash_pos = new JLabel();
        crash_pos.setBounds(50,100,350,50);

        pikachu_pos = new JLabel();
        pikachu_pos.setBounds(50,150,350,50);

        botonIniciarCarrera = new JButton("Iniciar Carrerita GOD");
        botonIniciarCarrera.setBounds(150,200,150,50);

        botonIniciarCarrera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hilo tiempo_mario = new Hilo("Mario ",mario,mario_pos);
                Hilo tiempo_crash = new Hilo("Crash ",crash,crash_pos);
                Hilo tiempo_pikachu = new Hilo("Pikachu ",pikachu,pikachu_pos);
            }
        });

        panel.add(mario);
        panel.add(mario_pos);
        panel.add(crash);
        panel.add(crash_pos);
        panel.add(pikachu);
        panel.add(pikachu_pos);
        panel.add(botonIniciarCarrera);

        add(panel);
        setVisible(true);

    }
}