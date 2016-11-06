package empresa;

/**
 * Clase: ConsolaIndividual
 * Esta clase extiende a la clase maquina para
 * crear una maquina predeterminada (ConsolaIndividual) con sus
 * correspondientes atributos.
 */
public class ConsolaIndividual extends Maquina{;
    private static final int codigo = 100001;
    private static final String descripcion = "Consola individual: Consola de mesa para un jugador.";
    
    public ConsolaIndividual() {
        super(codigo, descripcion);
    }
}
