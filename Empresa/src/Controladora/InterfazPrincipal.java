package Controladora;

import empresa.Pedido;

import java.text.ParseException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public interface InterfazPrincipal extends Interfaz{
    public Calendar getFecha()throws InterfazException, ParseException;
    public Pedido pedidoSeleccionado() throws InterfazException;
    public void refresh();
    
    public void lanzarCartel(String str);
    public void lanzarCartelConLista(Iterator i);
    
    public static final String DESLOG = "DESLOG";
    public static final String NPED = "NPED";
    public static final String APED = "APED";
    public static final String GLOT = "GLOT";
    public static final String OBS = "OBS";
    public static final String APROD = "APROD";
    public static final String MATNEC = "MATNEC";
    public static final String MATFALT = "MATFALT";
}
