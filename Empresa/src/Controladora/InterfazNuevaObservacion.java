package Controladora;

/**
 * interface InterfazNuevaObservacion.
 * Implementa los metodos que requiere la controladora que 
 * realice una interfaz de usuario cuando se desea insertar
 * una nueva observacion.
 */
public interface InterfazNuevaObservacion extends Interfaz {
    /**
     * metodo getTema.
     * Utilizado para MVC (desacoplamiento)
     * Devuelve una cadena de caracteres con un tema desde
     * la interfaz.
     * Precondicion:
     * Se asume que la interfaz de usuario solo dejara
     * seleccionar de los temas disponibles.
     * @return
     * String: tema de la observacion.
     */
    public String getTema();
    /**
     * metodo getObservacion.
     * Utilizado para MVC (desacoplamiento).
     * Devuelve una cadena con la descripcion de la observacion.
     * @return
     * String: descripcion de la observacion.
     * @throws InterfazException
     * Si la observacion esta vacia o excede el numero
     * maximo de caracteres (500) se lanza esta excepcion.
     */
    public String getObservacion() throws InterfazException;
    
    public final static String CANCELAR = "CANCELAR_NUEVA_OBS";
    public final static String AGREGAR = "AGREGAR_NUEVA_OBS";
}
