package Controladora;

import empresa.Material;

public interface InterfazMaquina extends Interfaz{
    public Material getMatStockSeleccionado();
    public Material getMatProdSeleccionado();
    public int getCodigoMaquina();
    public void refresh();
    
    public static final String AGREGAR = "AGREGAR_MAT";
    public static final String MODIFICAR = "MODIFICAR_MAT";
    public static final String ELIMINAR = "ELIMINAR_MAT";
    public static final String VOLVER = "VOLVER_MAT";
}
