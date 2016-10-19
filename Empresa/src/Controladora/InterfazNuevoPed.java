package Controladora;

import java.text.ParseException;

import java.util.Calendar;

public interface InterfazNuevoPed {
    public void mostrar();
    public void ocultar();
    public void cerrar();
    
    public int getCodigoMaquina() throws NumberFormatException;
    public int getCantidad() throws NumberFormatException;
    public Calendar getFecha() throws ParseException;
    
    public void setControlador(Controladora c);
    
    public static String VOLVER = "VOLVER";
    public static String AGREGAR = "AGREGAR";
}
