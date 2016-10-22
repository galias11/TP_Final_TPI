package Controladora;

import java.text.ParseException;

import java.util.Calendar;

public interface InterfazNuevoPed extends Interfaz {
    public int getCodigoMaquina() throws NumberFormatException;
    public int getCantidad() throws NumberFormatException, InterfazException;
    public Calendar getFecha() throws ParseException, InterfazException;
    
    public static String VOLVER = "VOLVER_NPED";
    public static String AGREGAR = "AGREGAR_NPED";
}
