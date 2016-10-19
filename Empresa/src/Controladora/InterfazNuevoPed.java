package Controladora;

import java.text.ParseException;

import java.util.Calendar;

public interface InterfazNuevoPed extends Interfaz {
    public int getCodigoMaquina() throws NumberFormatException;
    public int getCantidad() throws NumberFormatException;
    public Calendar getFecha() throws ParseException;
    
    public static String VOLVER = "VOLVER_NPED";
    public static String AGREGAR = "AGREGAR_NPED";
}
