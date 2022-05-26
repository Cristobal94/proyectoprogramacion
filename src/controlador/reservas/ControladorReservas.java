package controlador.reservas;

import controlador.logueo.ControladorLogin;
import modelo.dao.reserva.ReservaDAO;
import modelo.dao.reserva.ReservaDAOImpl;
import modelo.dao.usuario.Usuario;
import modelo.dao.usuario.UsuarioDAO;
import vista.logueo.Login;
import vista.reservas.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class ControladorReservas {
    private ReservaDAO modelo;
    private Reserva vista;
    private Usuario usuario;
    private JOptionPane elegirNombreFicheroAGuardar;
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;

    private JFrame login;

    public ControladorReservas(Usuario usuario, JFrame login) {
        this.login = login;
        this.modelo = new ReservaDAOImpl();
        this.vista = new Reserva();
        this.usuario = usuario;
        inicializarVista();
    }

    private void inicializarVista() {
        vista.getVentanaReservas().setVisible(true);
        if (usuario.getRol() == 1) {
            vista.getButtonCargar().setVisible(true);
            vista.getButtonGuardar().setVisible(true);
            vista.getLabelPrueba().setText(usuario.getEmail());
        } else {
            vista.getButtonCargar().setVisible(false);
            vista.getButtonGuardar().setVisible(false);
            vista.getLabelPrueba().setText(usuario.getEmail());
        }
        // vista.getLabelPrueba().setText(usuario.toString());
    }

    public void inicializarControlador() {
        vista.getButtonGuardar().addActionListener(actionEvent -> guardarReserva());
        vista.getButtonCargar().addActionListener(actionEvent -> cargarReserva());
        vista.getBotonSalir().addActionListener(event -> salirApp());

        vista.getBotonCerrarSesion().addActionListener(event -> {
            // desactivamos la venana actual
            vista.getVentanaReservas().setVisible(false);

            // volvemos a visualizar la ventana login
            login.setVisible(true);
        });
    }

    private void cargarReserva() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int option = fc.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            if (fichero == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "No has seleccionado ningún fichero"
                );
                return;
            }

            if (modelo.leerDatosFicheroVolcarABD(fichero.getAbsolutePath())) {
                JOptionPane.showMessageDialog(null, "Se han cargado los datos correctamente.");
            }else {
                JOptionPane.showMessageDialog(
                        null,
                        "No se ha podido leer el fichero, no tiene el formato correcto",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void guardarReserva() {
        String nombreFichero = elegirNombreFicheroAGuardar.showInputDialog("Introduzca el nombre del fichero.\nSi el nombre ya existe, será sobreescrito.");
        if (nombreFichero != null && !nombreFichero.equals("")) {
            modelo.guardarDatosAFichero(nombreFichero);
            JOptionPane.showMessageDialog(
                    null,
                    "Reservas guardadas correctamente",
                    "Acción completada",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "No se ha introducido un nombre valido al fichero",
                    "Acción no completada",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void salirApp() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de salir?",
                "SALIR", JOptionPane.YES_NO_OPTION);
        if (opcion == 0)
            System.exit(0);
    }

}








