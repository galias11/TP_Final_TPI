package Controladora;

import empresa.Material;

public interface InterfazMaquina extends Interfaz{
    public Material getMatStockSeleccionado() throws InterfazException;
    public Material getMatProdSeleccionado() throws InterfazException;
    public double getCantidad() throws InterfazException;
    public int getCodigoMaquina();
    public void refresh();
    
    public static final String AGREGAR = "AGREGAR_MAT";
    public static final String MODIFICAR = "MODIFICAR_MAT";
    public static final String ELIMINAR = "ELIMINAR_MAT";
    public static final String VOLVER = "VOLVER_MAT";
}
