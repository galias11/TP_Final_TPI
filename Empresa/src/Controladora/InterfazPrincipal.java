package Controladora;

import empresa.Pedido;

import java.text.ParseException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public interface InterfazPrincipal extends Interfaz{
    public Calendar getFecha()throws InterfazException, ParseException;
    public Pedido pedidoSeleccionado() throws InterfazException;
    public String motivoCancelacion() throws InterfazException;
    public void refresh();
    
    public void lanzarCartel(String str);
    public void lanzarCartelConLista(Iterator i);
    
    public static final String DESLOG = "DESLOG_PRINC";
    public static final String NPED = "NPED_PRINC";
    public static final String APED = "APED_PRINC";
    public static final String GLOT = "GLOT_PRINC";
    public static final String OBS = "OBS_PRINC";
    public static final String APROD = "APROD_PRINC";
    public static final String MATNEC = "MATNEC_PRINC";
    public static final String MATFALT = "MATFALT_PRINC";
    public static final String CANC = "CANC_PRINC";
}
