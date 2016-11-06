package Controladora;

import empresa.Pedido;

import java.text.ParseException;

import java.util.Calendar;
import java.util.Iterator;

/**
 * interface InterfazPrincipal
 * Define las constantes y los metodos que debe tener una
 * interfaz de usuario para funcionar en conjunto con la controladora
 * del sistema.
 */
public interface InterfazPrincipal extends Interfaz{
    /**
     * metodo getFecha.
     * Utilizado para MVC (desacoplamiento).
     * Obtiene de la interfaz de usuario una fecha.
     * @return
     * Calendar: fecha ingresada por el usuario.
     * @throws InterfazException
     * Si la fecha esta en el pasado o el usuario cancela
     * el ingreso se lanza esta excepcion.
     * @throws ParseException
     * Si ocurre algun error de parseo con el ingreso del usuario
     * se lanza esta excepcion.
     */
    public Calendar getFecha()throws InterfazException, ParseException;
    /**
     * metodo pedidoSeleccionado.
     * Utilizado para MVC (desacoplamiento).
     * Obtiene el pedido seleccionado en la interfaz de usuario.
     * @return
     * Pedido: pedido seleccionado en la interfaz de usuario.
     * @throws InterfazException
     * Si no ha sido seleccionado ningun pedido, se lanza
     * esta excepcion.
     */
    public Pedido pedidoSeleccionado() throws InterfazException;
    /**
     * metodo motivoCancelacion.
     * Utilizado para MVC (desacoplamiento).
     * Obtiene un motivo para la cancelacion de un pedido en la
     * interfaz de usuario.
     * @return
     * String: cadena que describe el motivo.
     * @throws InterfazException
     * Si el usuario cancela el ingreso, o el motivo no cumple la
     * condicion de no estar vacio / menor igual a 500 caracteres,
     * se lanza esta excepcion.
     */
    public String motivoCancelacion() throws InterfazException;
    /**
     * metodo refresh
     * Utilizado para MVC (desacoplamiento).
     * Refresca los componentes de la interfaz grafica de usuario.
     */
    public void refresh();
    
    /**
     * metodo lanzarCartel
     * Utilizado para MVC (desacoplamiento).
     * Indica a la interfaz de usuario que debe informar al usuario
     * la informacion contenida en el parametro str.
     * Precondicion:
     * str no puede ser nulo.
     * PostCondicion:
     * 
     * @param str
     * String: informacion a mostrar.
     */
    public void lanzarCartel(String str);
    /**
     * metodo lanzarCartelConLista
     * Utilizado para MVC (desacoplamiento).
     * Indica a la interfaz de usuario que debe informar al usuario
     * con un listado.
     * Precondicion:
     * i no puede ser nulo.
     * PostCondicion:
     * 
     * @param i
     * Iterator: iterador que contiene las entradas del listado.
     */
    public void lanzarCartelConLista(Iterator i);
    
    public static final String DESLOG = "DESLOG_PRINC";
    public static final String INIC_PED = "INIC_PED_PRINC";
    public static final String EVAL_PED = "EVAL_PED_PRINC";
    public static final String ACEPT_PED = "ACEPT_PED_PRINC";
    public static final String OBS = "OBS_PRINC";
    public static final String ADM_PROD = "ADM_PROD_PRINC";
    public static final String MAT_NEC = "MATNEC_PRINC";
    public static final String MAT_FALT = "MATFALT_PRINC";
    public static final String CANC = "CANC_PRINC";
}
