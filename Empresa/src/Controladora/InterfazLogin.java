package Controladora;

/**
 * interface InterfazLogin
 * Define las constantes y los metodos que debe proveer una
 * interfaz grafica para el login de los usuarios.
 */
public interface InterfazLogin extends Interfaz{
    /**
     * metodo getNroLegajo
     * Utilizado para MVC (desacoplamiento)
     * Obtiene un numero de legajo de la interfaz de usuario.
     * @return
     * int: nro de legajo ingresado en la interfaz.
     * @throws NumberFormatException
     * Si el ingreso no es un formato numerico correcto
     * se lanza esta excepcion.
     */
    public int getNroLegajo() throws NumberFormatException;
    
    public final static String LOGIN = "LOGIN";
}
