package controlador.reservas;

import modelo.dao.reserva.ReservaDAO;
import modelo.dao.reserva.ReservaDAOImpl;
import modelo.dao.usuario.Usuario;
import modelo.dao.usuario.UsuarioDAO;
import vista.logueo.Login;
import vista.reservas.Reserva;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class ControladorReservas {
    private ReservaDAO modelo;
    private Reserva vista;
    private Usuario usuario;
    private JOptionPane elegirNombreFicheroAGuardar;

    public ControladorReservas(Usuario usuario) {
        this.modelo = new ReservaDAOImpl();
        this.vista = new Reserva();
        this.usuario = usuario;
        inicializarVista();
    }

    private void inicializarVista() {
        vista.getVentanaReservas().setVisible(true);
        /*if (usuario.getRol()== 1 ) {
            vista.getButtonCargar().setEnabled(true);
            vista.getButtonGuardar().setEnabled(true);
            vista.getLabelPrueba().setText(usuario.getEmail());
        }else {
            vista.getButtonCargar().setEnabled(false);
            vista.getButtonGuardar().setEnabled(false);
            vista.getLabelPrueba().setText(usuario.getEmail());
        }*/
       // vista.getLabelPrueba().setText(usuario.toString());
    }

    public void inicializarControlador() {
        vista.getButtonGuardar().addActionListener(actionEvent -> guardarReserva());
        vista.getButtonCargar().addActionListener(actionEvent -> cargarReserva());
    }

    private void cargarReserva() {
        modelo.leerDatosFicheroVolcarABD("prueba.csv");

    }

    private void guardarReserva ()  {
            String nombreFichero = elegirNombreFicheroAGuardar.showInputDialog("Introduzca el nombre del fichero.\nSi el nombre ya existe, será sobreescrito.");
            modelo.guardarDatosAFichero(nombreFichero);
            if (nombreFichero != null | nombreFichero != "") {
                JOptionPane.showMessageDialog(null, "Reservas guardadas correctamente",
                        "Acción completada", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "No se ha introducido un nombre valido al fichero",
                        "Acción no completada", JOptionPane.INFORMATION_MESSAGE);
            }

        }
}








