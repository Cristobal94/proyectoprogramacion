package vista.reservas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Reserva {
    private JFrame ventanaReservas;
    private JPanel panelPrincipal;
    private JLabel labelPrueba;
    private JPanel panelInferior;
    private JButton botonSalir;
    private JButton botonCerrarSesion;
    private JButton buttonGuardar;
    private JButton buttonCargar;
    private JTextField reservasTextField;

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public JButton getBotonCerrarSesion() {
        return botonCerrarSesion;
    }

    public JButton getButtonGuardar() {
        return buttonGuardar;
    }

    public JButton getButtonCargar() {
        return buttonCargar;
    }

    public Reserva() {
        ventanaReservas = new JFrame("Aplicación reservas");
        ventanaReservas.setContentPane(panelPrincipal);
        ventanaReservas.setSize(800, 800);
        ventanaReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaReservas.setLocationRelativeTo(null);
        // ventanaLogin.pack();
        ventanaReservas.setVisible(true);
    }

    public JFrame getVentanaReservas() {
        return ventanaReservas;
    }

    public JLabel getLabelPrueba() {
        return labelPrueba;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reserva");
        frame.setContentPane(new Reserva().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
