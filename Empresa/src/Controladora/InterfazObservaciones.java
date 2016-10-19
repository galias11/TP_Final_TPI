package Controladora;

import empresa.Observacion;
import empresa.Pedido;

public interface InterfazObservaciones extends Interfaz{
    public Observacion getObsSeleccionada();
    public Pedido getPedido();
    public void refresh();
    
    public static String VOLVER = "VOLVER_OBS";
    public static String AGREGAR = "AGREGAR_OBS";
}
