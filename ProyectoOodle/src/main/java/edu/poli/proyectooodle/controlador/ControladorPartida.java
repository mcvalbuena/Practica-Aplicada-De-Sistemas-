package edu.poli.proyectooodle.controlador;

import edu.poli.proyectooodle.Services.UserDAO;
import edu.poli.proyectooodle.vista.GestorEscenas;
import edu.poli.proyectooodle.modelo.Intento;
import edu.poli.proyectooodle.modelo.Juego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorPartida {

    // -- Usuario Panel --
    @FXML private Label lblUsuario;
    @FXML private Label lblPuntajeTotal;
    @FXML private Label lblPuntajePartida;
    // ── Celdas por fila ──────────────────────────────────────────────────────────
    @FXML private Button f1c1, f1c2, f1c3, f1c4, f1resultado;
    @FXML private Button f2c1, f2c2, f2c3, f2c4, f2resultado;
    @FXML private Button f3c1, f3c2, f3c3, f3c4, f3resultado;
    @FXML private Button f4c1, f4c2, f4c3, f4c4, f4resultado;
    @FXML private Button f5c1, f5c2, f5c3, f5c4, f5resultado;
    @FXML private Button f6c1, f6c2, f6c3, f6c4, f6resultado;

    // ── Estado ───────────────────────────────────────────────────────────────────
    private Button   celdaSeleccionada = null;
    private int      filaActual        = 0;         // fila en juego (0-5)

    /** Modelo compartido */
    private Juego juego;

    /** Matriz de celdas [fila][columna] para acceso programático */
    private Button[][] celdas;
    /** Botones de resultado por fila */
    private Button[]   resultados;
    private UserDAO _userDAO = new UserDAO();


    // ── Inicialización ────────────────────────────────────────────────────────────

    @FXML
    public void initialize() {

        // Construir matrices de acceso a los botones del FXML
        celdas = new Button[][]{
                {f1c1, f1c2, f1c3, f1c4},
                {f2c1, f2c2, f2c3, f2c4},
                {f3c1, f3c2, f3c3, f3c4},
                {f4c1, f4c2, f4c3, f4c4},
                {f5c1, f5c2, f5c3, f5c4},
                {f6c1, f6c2, f6c3, f6c4}
        };
        resultados = new Button[]{
                f1resultado, f2resultado, f3resultado,
                f4resultado, f5resultado, f6resultado
        };

        juego = Juego.getInstancia(); // ahora sí, después del login

        if (juego == null) {
            System.out.println("Juego no inicializado aún");
            return;
        }

        juego.iniciarJuego(juego.getRangoActual(), juego.jugador);

        // Iniciar una partida nueva con el rango actualmente seleccionado
        juego.iniciarJuego(juego.getRangoActual(), juego.jugador);
        filaActual = 0;

        // Mostrar el resultado objetivo en todos los botones de resultado
        mostrarObjetivo();

        // 👤 Usuario
        if (juego.jugador != null) {
            lblUsuario.setText(juego.jugador.getNombre());
        }

        // ⭐ Puntaje total (si tienes sistema persistente)
        if (juego.getScore() != null) {
            lblPuntajeTotal.setText(String.valueOf(juego.jugador.getScore()));
        } else {
            lblPuntajeTotal.setText("0");
        }

        // 🎯 Puntaje partida (inicial)
        lblPuntajePartida.setText(""+juego.getScore().getPuntos());
    }

    /** Escribe "= X" en cada botón de resultado para que el jugador vea el objetivo. */
    private void mostrarObjetivo() {
        if (juego.getNumeroObjetivo() == null) return;
        String textoObjetivo = "" + juego.getNumeroObjetivo().getResultado();
        for (Button r : resultados) {
            r.setText(textoObjetivo);
        }
    }

    // ── Navbar ────────────────────────────────────────────────────────────────────

    @FXML
    protected void onHome() throws IOException {
        GestorEscenas.irA("hello-view.fxml");
    }

    @FXML
    protected void onRestart() {
        juego.iniciarJuego(juego.getRangoActual(), juego.jugador);
        filaActual         = 0;
        celdaSeleccionada  = null;
        limpiarTablero();
        mostrarObjetivo();
        lblPuntajePartida.setText(String.valueOf(juego.getScore().getPuntos()));
        lblPuntajeTotal.setText(String.valueOf(juego.jugador.getScore()));
    }

    // ── Selección de celda ────────────────────────────────────────────────────────

    @FXML
    protected void onCeldaClick(ActionEvent e) {
        // Quitar selección visual anterior
        if (celdaSeleccionada != null) deseleccionarCelda(celdaSeleccionada);

        celdaSeleccionada = (Button) e.getSource();

        // Solo permitir selección de celdas en la fila activa
        if (esCeldaDeFilaActual(celdaSeleccionada)) {
            seleccionarCelda(celdaSeleccionada);
        } else {
            celdaSeleccionada = null;
        }
    }

    // ── Teclado numérico ──────────────────────────────────────────────────────────

    @FXML
    protected void onNumero(ActionEvent e) {
        if (celdaSeleccionada == null) return;
        Button btnNumero = (Button) e.getSource();
        celdaSeleccionada.setText(btnNumero.getText());

        // Avanzar automáticamente a la siguiente celda de la fila
        avanzarCeldaSeleccionada();
    }

    @FXML
    protected void onDel() {
        if (celdaSeleccionada == null) return;
        celdaSeleccionada.setText("");
    }

    /** Botón global "Check": verifica la fila activa. */
    @FXML
    protected void onCheck() {
        if (filaActual < celdas.length) procesarIntento(filaActual);
        lblPuntajePartida.setText(String.valueOf(juego.getScore().getPuntos()));
    }

    // ── Verificar fila (botón resultado de cada fila) ─────────────────────────────

    @FXML
    protected void onVerificarFila(ActionEvent e) {
        int fila = obtenerFilaDe((Button) e.getSource());
        // Solo procesar si el jugador clickea el resultado de la fila activa
        if (fila == filaActual) procesarIntento(fila);
        lblPuntajePartida.setText(String.valueOf(juego.getScore().getPuntos()));
    }

    // ── Lógica central: procesar un intento ───────────────────────────────────────

    private void procesarIntento(int fila) {
        if (juego.isPartidaFinalizada()) return;

        // 1. Recoger valores de la fila
        List<Integer> valores = new ArrayList<>();
        for (Button celda : celdas[fila]) {
            String texto = celda.getText().trim();
            if (texto.isEmpty()) {
                marcarErrorFila(fila, "!");   // celda vacía
                return;
            }
            try {
                valores.add(Integer.parseInt(texto));
            } catch (NumberFormatException ex) {
                marcarErrorFila(fila, "?");
                return;
            }
        }

        // 2. Enviar al modelo
        Intento intento = juego.registrarIntento(valores);
        lblPuntajePartida.setText(String.valueOf(juego.getScore().getPuntos()));

        if (intento == null) {
            // La suma NO coincide con el objetivo → intento inválido, no se descuenta
            marcarErrorFila(fila, "✗");
            return;
        }

        // 3. Colorear celdas según el resultado Oodle
        if (intento.correcto(juego.getNumeroObjetivo())) {
            for (int i = 0; i < celdas[fila].length; i++) {
                colorearCelda(celdas[fila][i], true);
            }
        } else {
            for (int i = 0; i < celdas[fila].length; i++) {
                colorearCelda(celdas[fila][i], false);
            }
        }


        // 4. Actualizar botón resultado de la fila
        resultados[fila].setStyle(estiloResultadoUsado());
        resultados[fila].setText("= " + juego.getNumeroObjetivo().getResultado());

        // 5. Evaluar fin de partida
        if (juego.isPartidaGanada()) {
            resultados[fila].setStyle(estiloResultadoGanado());
            resultados[fila].setText("✓");

            int puntosPartida = juego.getScore().getPuntos();

            int nuevoTotal = juego.jugador.getScore() + puntosPartida;


            // 🔥 actualizar modelo en memoria
            juego.jugador.setScore(nuevoTotal);
            // 🔥 actualizar UI
            lblPuntajeTotal.setText(String.valueOf(nuevoTotal)); // UI

            _userDAO.updateScore(juego.jugador.getNombre(), nuevoTotal);
        } else if (juego.isPartidaFinalizada()) {
            mostrarSolucion();
        } else {
            filaActual++;
            celdaSeleccionada = null;
        }
    }

    // ── Coloreado de celdas ───────────────────────────────────────────────────────

    private void colorearCelda(Button celda, boolean correcto) {
        String color;
        if (correcto){
            color = "#6aaa64"; // verde
        }
        else
        {
            color = "#FF0000"; // rojo
        }

        celda.setStyle(
                "-fx-background-color: " + color + "; -fx-text-fill: #ffffff; " +
                        "-fx-border-color: " + color + "; -fx-border-width: 1.5; " +
                        "-fx-border-radius: 8; -fx-background-radius: 8; " +
                        "-fx-pref-width: 60; -fx-pref-height: 60; " +
                        "-fx-font-size: 18px; -fx-font-weight: bold; -fx-cursor: hand;"
        );
    }

    private void marcarErrorFila(int fila, String simbolo) {
        resultados[fila].setStyle(estiloError());
        resultados[fila].setText(simbolo);
        // Restaurar texto objetivo después de 1.5 s
        javafx.animation.PauseTransition pausa =
                new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.5));
        pausa.setOnFinished(ev -> {
            resultados[fila].setStyle(estiloResultadoNormal());
            resultados[fila].setText("= " + juego.getNumeroObjetivo().getResultado());
        });
        pausa.play();
    }

    private void mostrarSolucion() {
        // Al perder, mostrar la solución correcta en las celdas de la última fila libre
        List<Integer> sol = juego.getSolucion();
        if (sol == null || filaActual >= celdas.length) return;
        for (int i = 0; i < celdas[filaActual].length && i < sol.size(); i++) {
            celdas[filaActual][i].setText(String.valueOf(sol.get(i)));
            celdas[filaActual][i].setStyle(
                    "-fx-background-color: #e74c3c; -fx-text-fill: #ffffff; " +
                            "-fx-border-color: #e74c3c; -fx-border-width: 1.5; " +
                            "-fx-border-radius: 8; -fx-background-radius: 8; " +
                            "-fx-pref-width: 60; -fx-pref-height: 60; " +
                            "-fx-font-size: 18px; -fx-font-weight: bold;"
            );
        }
        resultados[filaActual].setText(":(");
        resultados[filaActual].setStyle(estiloError());
    }

    // ── Navegación entre celdas ───────────────────────────────────────────────────

    /** Avanza la selección a la siguiente celda vacía de la fila activa. */
    private void avanzarCeldaSeleccionada() {
        if (filaActual >= celdas.length) return;
        Button[] fila = celdas[filaActual];
        for (Button celda : fila) {
            if (celda.getText().trim().isEmpty()) {
                deseleccionarCelda(celdaSeleccionada);
                celdaSeleccionada = celda;
                seleccionarCelda(celda);
                return;
            }
        }
    }

    private boolean esCeldaDeFilaActual(Button celda) {
        if (filaActual >= celdas.length) return false;
        for (Button c : celdas[filaActual]) {
            if (c == celda) return true;
        }
        return false;
    }

    private int obtenerFilaDe(Button btnResultado) {
        for (int i = 0; i < resultados.length; i++) {
            if (resultados[i] == btnResultado) return i;
        }
        return -1;
    }

    // ── Selección visual ──────────────────────────────────────────────────────────

    private void seleccionarCelda(Button celda) {
        celda.setStyle(celda.getStyle()
                .replace("-fx-border-color: #d0d0d0;", "-fx-border-color: #5b4fcf;"));
    }

    private void deseleccionarCelda(Button celda) {
        celda.setStyle(celda.getStyle()
                .replace("-fx-border-color: #5b4fcf;", "-fx-border-color: #d0d0d0;"));
    }

    // ── Limpiar tablero ───────────────────────────────────────────────────────────

    private void limpiarTablero() {
        for (Button[] fila : celdas) {
            for (Button celda : fila) {
                celda.setText("");
                celda.setStyle(estiloBase());
            }
        }
        for (Button r : resultados) {
            r.setStyle(estiloResultadoNormal());
        }
    }

    // ── Estilos reutilizables ─────────────────────────────────────────────────────

    private String estiloBase() {
        return "-fx-background-color: #ffffff; -fx-border-color: #d0d0d0; " +
                "-fx-border-width: 1.5; -fx-border-radius: 8; -fx-background-radius: 8; " +
                "-fx-pref-width: 60; -fx-pref-height: 60; -fx-font-size: 18px; " +
                "-fx-font-weight: bold; -fx-cursor: hand;";
    }

    private String estiloResultadoNormal() {
        return "-fx-background-color: #f0f0f0; -fx-text-fill: #030213; " +
                "-fx-border-color: #d0d0d0; -fx-border-width: 1.5; " +
                "-fx-border-radius: 8; -fx-background-radius: 8; " +
                "-fx-pref-width: 80; -fx-pref-height: 60; -fx-font-size: 14px; " +
                "-fx-font-weight: bold; -fx-cursor: hand;";
    }

    private String estiloResultadoUsado() {
        return "-fx-background-color: #030213; -fx-text-fill: #ffffff; " +
                "-fx-background-radius: 8; -fx-border-radius: 8; " +
                "-fx-pref-width: 80; -fx-pref-height: 60; -fx-font-size: 14px; " +
                "-fx-font-weight: bold;";
    }

    private String estiloResultadoGanado() {
        return "-fx-background-color: #6aaa64; -fx-text-fill: #ffffff; " +
                "-fx-background-radius: 8; -fx-pref-width: 80; -fx-pref-height: 60; " +
                "-fx-font-size: 18px; -fx-font-weight: bold;";
    }

    private String estiloError() {
        return "-fx-background-color: #e74c3c; -fx-text-fill: #ffffff; " +
                "-fx-background-radius: 8; -fx-border-radius: 8; " +
                "-fx-pref-width: 80; -fx-pref-height: 60; -fx-font-weight: bold;";
    }
}