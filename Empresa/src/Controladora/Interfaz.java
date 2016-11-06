package Controladora;

/**
 * interface Interfaz.
 * Define los metodos comunes de todas las interfaces graficas
 * que utiliza la controladora. Establece que metodos debe proveer 
 * cualquier interfaz de usuario que se quiera usar para el 
 * modelo planteado.
 */
public interface Interfaz {
    /**
     * metodo ocultar.
     * Utilizado por MVC (desacoplamiento).
     * oculta la ventana sin cerrarla.
     */
    public void ocultar();
    /**
     * metodo mostrar.
     * Utilizado por MVC (desacoplamiento).
     * muestra una ventana, la hace visible.
     */
    public void mostrar();
    /**
     * metodo cerrar.
     * Utilizado por MVC (desacoplamiento).
     * Cierra una ventana eliminando la instancia.
     */
    public void cerrar();
    /**
     * metodo setControlador.
     * Utilizado para el patron MVC, asigna a la ventana
     * el actionListener del controlador.
     * Precondicion: 
     * c no puede ser nulo.
     * PostCondicion:
     * 
     * @param c
     * Controladora: controladora que actuara como actionListener,
     * de la ventana.
     */
    public void setControlador(Controladora c);
}
