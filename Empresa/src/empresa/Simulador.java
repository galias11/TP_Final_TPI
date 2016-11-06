package empresa;

/**
 * Clase: Simulador.
 * Esta clase extiende a la clase maquina para
 * crear una maquina predeterminada (Simulador) con sus
 * correspondientes atributos.
 */
public class Simulador extends Maquina{
    private static final int codigo = 100003;
    private static final String descripcion = "Simulador: Simulador de irealidad virtual";
    
    public Simulador() {
        super(codigo, descripcion);
    }
}
