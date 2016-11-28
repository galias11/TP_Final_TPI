package Controladora;

import empresa.Observacion;
import empresa.Pedido;

/**
 * interface InterfazObservaciones.
 * Establece las constantes y los metodos minimos para que
 * una interfaz de usuario permita operar con el MVC diseñado.
 */
public interface InterfazObservaciones extends Interfaz{
    /**
     * metodo getObservacion.
     * Utilizado para MVC (desacoplamiento).
     * Obtiene de la interfaz grafica la observacion seleccionada.
     * @return
     * Observacion: observacion seleccionada.
     */
    public Observacion getObsSeleccionada() throws InterfazException;
    /**
     * metodo getPedido
     * Utilizado para MVC (desacoplamiento).
     * Obtiene de la interfaz grafica el pedido activo en la 
     * ventana de observaciones.
     * @return
     * Pedido: pedido actiivo.
     */
    public Pedido getPedido();
    /**
     * metodo refrsh
     * Utilizado para MVC (desacoplamiento).
     * Hace que la interfaz grafica refresque sus componentes.
     */
    public void refresh();
    
    public final static String VOLVER = "VOLVER_OBS";
    public final static String AGREGAR = "AGREGAR_OBS";
}
