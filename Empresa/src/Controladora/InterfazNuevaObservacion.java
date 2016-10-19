package Controladora;

public interface InterfazNuevaObservacion extends Interfaz {
    public String getTema();
    public String getObservacion() throws InterfazException;
    
    public static String CANCELAR = "CANCELAR_NUEVA_OBS";
    public static String AGREGAR = "AGREGAR_NUEVA_OBS";
}
