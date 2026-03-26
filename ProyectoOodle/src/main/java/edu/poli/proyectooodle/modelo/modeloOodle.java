package edu.poli.proyectooodle.modelo;


public class modeloOodle {

    private boolean modoActual = true; // false = 1-12, true = 1-9

    public boolean getModoActual() {
        return modoActual;
    }

    public void toggleModo() {
        modoActual = !modoActual;
    }

    public String getTextoCambiarModo() {
        return modoActual ? "Cambiar a modo 1-9" : "Cambiar a modo 1-12";
    }

    public String getTextoReglas() {
        String numeros = modoActual ? "1 al 9" : "1 al 12";
        return "REGLAS" +
                "\nUtilice los números del " + numeros + " sólo una vez\n" +
                "Haga click en el cuadro de respuestas rojo para verificar la solución\n" +
                "Verde = ¡El número está en la posición correcta!" +
                "\nAmarillo = Número de posición incorrecta" +
                "\nGris = Número no presente en la ecuación" +
                "\nTienes un máximo de seis intentos";
    }
}