package empresa;

/**
 * Clase: ConsolaGrupal
 * Esta clase extiende a la clase maquina para
 * crear una maquina predeterminada (ConsolaGrupal) con sus
 * correspondientes atributos.
 */
public class ConsolaGrupal extends Maquina{
    private static final int codigo = 100002;
    private static final String descripcion = "Consola grupal: Consola de mesa para dos jugadores."; 
    
    public ConsolaGrupal() {
        super(codigo, descripcion);
    }
}
