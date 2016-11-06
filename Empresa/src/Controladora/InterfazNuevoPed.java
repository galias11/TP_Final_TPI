package Controladora;

import java.text.ParseException;

import java.util.Calendar;

/**
 * interface InterfazNuevoPed
 * Determina las constantes y metodos que debe tener una interfaz
 * de usuario de ingreso de un nuevo pedido para poder intertactuar
 * con la controladora del sistema.
 */
public interface InterfazNuevoPed extends Interfaz {
    /**
     * metodo getCodigoMaquina
     * Utilizado para MVC (desacoplamiento).
     * obtiene un codigo de maquina de la interfaz de usuario.
     * @return
     * int: codigo de maquina.
     * @throws NumberFormatException
     * Si se detecta un formato numerico incorrecto se lanza
     * esta excepcion.
     * @throws InterfazException
     * Si el valor numerico se encuentra fuera de rango (1..999999)
     * lanza esta excepcion.
     */
    public int getCodigoMaquina() throws NumberFormatException, InterfazException;
    /**
     * metodo getCantidad.
     * Utilizado para MVC (desacoplamiento).
     * obtiene una cantidad para el nuevo pedido de la interfaz
     * de usuario.
     * @return
     * int: cantidad.
     * @throws NumberFormatException
     * Si se detecta algun desvio en el formtato numerico ingresado
     * lanza esta excepcion
     * @throws InterfazException
     * Si el numero ingresado es menor que uno se lanza esta excepcion.
     */
    public int getCantidad() throws NumberFormatException, InterfazException;
    /**
     * metodo getFecha.
     * Utilizado para MVC (desacoplamiento)
     * Obtiene una fecha para el nuevo pedido desde la interfaz
     * de usuario.
     * @return
     * Calendar: fecha de entrega pedido.
     * @throws ParseException
     * Si se detecta un error al parsear la fecha lanza esta
     * excepcion.
     * @throws InterfazException
     * Si detecta una fecha no valida (en el pasado), se lanza
     * esta excepcion.
     */
    public Calendar getFecha() throws ParseException, InterfazException;
    
    public final static String VOLVER = "VOLVER_NPED";
    public final static String AGREGAR = "AGREGAR_NPED";
}
