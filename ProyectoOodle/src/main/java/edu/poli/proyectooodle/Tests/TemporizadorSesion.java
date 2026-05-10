package edu.poli.proyectooodle.Tests;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TemporizadorSesion {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final long inicio = System.currentTimeMillis();

    public void iniciar() {
        scheduler.scheduleAtFixedRate(() -> {
            long minutos = (System.currentTimeMillis() - inicio) / 60_000;
            System.out.println("[Sesión] Tiempo abierto: " + minutos + " minuto(s)");
        }, 1, 1, TimeUnit.MINUTES); // espera 1 min, repite cada 1 min
    }

    public void detener() {
        scheduler.shutdownNow();
    }
}