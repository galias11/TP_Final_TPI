package Controladora;

import empresa.Pedido;

public interface InterfazPrincipal {
    public Pedido pedidoSeleccionado();
    public void mostrar();
    public void ocultar();
    public void refresh();
    public void setControlador(Controladora c);
    public void cerrar();
    
    public static final String DESLOG = "DESLOG";
    public static final String NPED = "NPED";
    public static final String APED = "APED";
    public static final String GLOT = "GLOT";
    public static final String OBS = "OBS";
    public static final String APROD = "APROD";
}
