package empresa;

/**
 * Clase EmpresaException
 * Excepcion personalizada para el sistema de la empresa.
 */
public class EmpresaException extends Exception{
    
    /**
     * Constructor de la excepcion.
     * Precondicion:
     * descripcion no nula ni vacia (se verifican a post).
     * No se puede comprobar por parametro, se deja estipulada
     * por este medio en el contrato de la clase.
     * PostCondicion:
     * 
     * @param descripcion
     * String: descricion de la excepcion.
     */
    public EmpresaException(String descripcion) {
        super(descripcion);
    }
    
    @Override
    public String toString(){
        return getMessage();
    }
}
