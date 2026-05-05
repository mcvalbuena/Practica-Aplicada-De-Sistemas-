package edu.poli.proyectooodle.controlador;

import edu.poli.proyectooodle.Services.Autenticacion;
import edu.poli.proyectooodle.vista.GestorEscenas;
import edu.poli.proyectooodle.modelo.Juego;
import edu.poli.proyectooodle.modelo.Usuario;
import edu.poli.proyectooodle.Services.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class ControladorLoginRegistro {

    // ── Tabs ──────────────────────────────────────────────────────────────────────
    @FXML private Button btnTabLogin;
    @FXML private Button btnTabRegistro;

    // ── Paneles ───────────────────────────────────────────────────────────────────
    @FXML private VBox panelLogin;
    @FXML private VBox panelRegistro;

    // ── Campos Login ──────────────────────────────────────────────────────────────
    @FXML private TextField     loginNombre;
    @FXML private PasswordField loginContrasena;
    @FXML private Label         lblErrorLogin;

    // ── Campos Registro ───────────────────────────────────────────────────────────
    @FXML private TextField     regNombre;
    @FXML private PasswordField regContrasena;
    @FXML private PasswordField regConfirmar;
    @FXML private Label         lblErrorRegistro;
    @FXML private Label         lblExitoRegistro;

    // ── Dependencias ──────────────────────────────────────────────────────────────
    private final UserDAO usuarioDAO = new UserDAO();
    private final Autenticacion autenticacion = new Autenticacion();

    // ── Toggle de pestañas ────────────────────────────────────────────────────────

    @FXML
    protected void onMostrarLogin() {
        panelLogin.setVisible(true);
        panelLogin.setManaged(true);
        panelRegistro.setVisible(false);
        panelRegistro.setManaged(false);

        btnTabLogin.setStyle(estiloTabActivo());
        btnTabRegistro.setStyle(estiloTabInactivo());

        limpiarErrores();
    }

    @FXML
    protected void onMostrarRegistro() {
        panelRegistro.setVisible(true);
        panelRegistro.setManaged(true);
        panelLogin.setVisible(false);
        panelLogin.setManaged(false);

        btnTabRegistro.setStyle(estiloTabActivo());
        btnTabLogin.setStyle(estiloTabInactivo());

        limpiarErrores();
    }

    // ── Login ─────────────────────────────────────────────────────────────────────

    @FXML
    protected void onLogin() throws IOException {
        String nombre     = loginNombre.getText().trim();
        String contrasena = loginContrasena.getText();

        // Validación de campos vacíos
        if (nombre.isEmpty() || contrasena.isEmpty()) {
            mostrarError(lblErrorLogin, "Completa todos los campos.");
            return;
        }

        // Buscar usuario en la BD
        Usuario usuario = usuarioDAO.getByUsername(nombre);

        if (usuario == null) {
            mostrarError(lblErrorLogin, "El usuario no existe.");
            return;
        }

        // Verificar contraseña
        if (!autenticacion.ValidarLogeo(nombre, contrasena)) {
            mostrarError(lblErrorLogin, "Usuario o contraseña invalidos.");
            loginContrasena.clear();
            return;
        }

        // Login exitoso → guardar usuario en el Juego y navegar al menú
        Juego.getInstancia(usuario);
        //Juego.getInstancia().(usuario);
        GestorEscenas.irA("NuevaPartida.fxml");
    }

    // ── Registro ──────────────────────────────────────────────────────────────────

    @FXML
    protected void onRegistrar() {
        String nombre     = regNombre.getText().trim();
        String contrasena = regContrasena.getText();
        String confirmar  = regConfirmar.getText();

        // Campos vacíos
        if (nombre.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
            mostrarError(lblErrorRegistro, "Completa todos los campos.");
            return;
        }

        // Longitud mínima de nombre
        if (nombre.length() < 2) {
            mostrarError(lblErrorRegistro, "El usuario debe tener al menos 3 caracteres.");
            return;
        }

        // Longitud mínima de contraseña
        if (contrasena.length() < 4) {
            mostrarError(lblErrorRegistro, "La contraseña debe tener al menos 4 caracteres.");
            return;
        }

        // Contraseñas coinciden
        if (!contrasena.equals(confirmar)) {
            mostrarError(lblErrorRegistro, "Las contraseñas no coinciden.");
            regConfirmar.clear();
            return;
        }

        // Verificar que el nombre no esté tomado
        if (usuarioDAO.getByUsername(nombre) != null) {
            mostrarError(lblErrorRegistro, "Ese nombre de usuario ya existe.");
            return;
        }

        // Crear y persistir el usuario
        Usuario nuevo = new Usuario(nombre, contrasena);
        int idGenerado = nuevo.getId();

        if (idGenerado == -1) {
            mostrarError(lblErrorRegistro, "Error al crear la cuenta. Intenta de nuevo.");
            return;
        }

        // Registro exitoso → limpiar campos y sugerir login
        autenticacion.registrar(nuevo.getNombre(), nuevo.getPasswordHash());
        ocultarLabel(lblErrorRegistro);
        mostrarExito(lblExitoRegistro, "¡Cuenta creada! Ya puedes iniciar sesión.");
        regNombre.clear();
        regContrasena.clear();
        regConfirmar.clear();
    }

    // ── Utilidades visuales ───────────────────────────────────────────────────────

    private void mostrarError(Label lbl, String mensaje) {
        lbl.setText(mensaje);
        lbl.setVisible(true);
        lbl.setManaged(true);
    }

    private void mostrarExito(Label lbl, String mensaje) {
        lbl.setText(mensaje);
        lbl.setVisible(true);
        lbl.setManaged(true);
    }

    private void ocultarLabel(Label lbl) {
        lbl.setVisible(false);
        lbl.setManaged(false);
    }

    private void limpiarErrores() {
        ocultarLabel(lblErrorLogin);
        ocultarLabel(lblErrorRegistro);
        ocultarLabel(lblExitoRegistro);
    }

    // ── Estilos de tabs ───────────────────────────────────────────────────────────

    private String estiloTabActivo() {
        return "-fx-background-color: #5b4fcf; -fx-text-fill: #ffffff; " +
                "-fx-font-size: 13px; -fx-font-weight: bold; " +
                "-fx-background-radius: 26; -fx-cursor: hand; " +
                "-fx-pref-width: 136; -fx-pref-height: 36;";
    }

    private String estiloTabInactivo() {
        return "-fx-background-color: transparent; -fx-text-fill: #5b4fcf; " +
                "-fx-font-size: 13px; -fx-background-radius: 26; " +
                "-fx-cursor: hand; -fx-pref-width: 136; -fx-pref-height: 36;";
    }
}