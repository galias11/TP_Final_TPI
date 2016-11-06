package empresa;

/**
 * Clase Flipper.
 * Esta clase extiende a la clase maquina para
 * crear una maquina predeterminada (Flipper) con sus
 * correspondientes atributos.
 */
public class Flipper extends Maquina{
    private static final int codigo = 100004;
    private static final String descripcion = "Flipper: Flipper clasico estilo 80´s"; 
    
    public Flipper() {
        super(codigo, descripcion);
    }
}
