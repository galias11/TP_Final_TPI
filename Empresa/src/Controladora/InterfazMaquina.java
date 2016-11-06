package Controladora;

import empresa.Material;

/**
 * interface InterfazMaquina
 * Define el comportamiento que debe tener una interfaz grafica
 * para la administracion de las recetas de las maquinas que
 * se acople con la controladora del sistema.
 */
public interface InterfazMaquina extends Interfaz{
    /**
     * metodo getMatStockSeleccionado
     * Utilizado para MVC (desacoplamiento).
     * Devuelve el material de inventario seleccionado en la
     * interfaz de usuario
     * @return
     * Material: material seleccionado del inventario.
     * @throws InterfazException
     * Si el usuario no selecciona ningun material del stock
     * se lanza esta excepcion.
     */
    public Material getMatStockSeleccionado() throws InterfazException;
    /**
     * metodo getMatProdSeleccionado
     * Utilizado para MVC (desacoplamiento).
     * Devuelve el material seleccionado de la receta del producto
     * en la interfaz de usuario.
     * @return
     * Material: material seleccionado de la receta.
     * @throws InterfazException
     * Si el usuario no selecciona ningun material de la receta
     * se lanza esta excepcion.
     */
    public Material getMatProdSeleccionado() throws InterfazException;
    /**
     * metodo getCantidad.
     * Utilizado para MVC (desacoplamiento).
     * Devuelve una cantidad solitada al usuario por la interfaz
     * de usuario.
     * @return
     * double: cantidad.
     * @throws InterfazException
     * Si el usuario no ingreso ninguna cantidad se lanza 
     * esta exception
     * @throws NumberFormatException
     * Si el formato numerico no es correcto se lanza esta excepcion.
     */
    public double getCantidad() throws InterfazException, NumberFormatException;
    /**
     * metodo getCodigoMaquina.
     * Utilizado para MVC (desacoplamiento)
     * Devuelve el codigo de maquina de la maquina activa en la interfaz
     * de usuario.
     * @return
     * int: codigo de maquina.
     */
    public int getCodigoMaquina();
    /**
     * metodo refresh
     * Utilizado para MVC (desacoplamiento).
     * Refresca los componentes en la interfaz grafica.
     * Precondicion:
     * El codigo de maquina seleccionado en la interfaz es
     * valido.
     */
    public void refresh();
    
    public static final String AGREGAR = "AGREGAR_MAT";
    public static final String MODIFICAR = "MODIFICAR_MAT";
    public static final String ELIMINAR = "ELIMINAR_MAT";
    public static final String VOLVER = "VOLVER_MAT";
}
