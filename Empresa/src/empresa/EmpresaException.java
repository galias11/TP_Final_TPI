package empresa;

/**
 * Clase EmpresaException
 * Excepcion personalizada para el sistema de la empresa.
 */
public class EmpresaException extends Exception{
    
    /**
     * Constructor de la excepcion.
     * Precondicion:
     * descripcion no nula ni vacia.
     * Como no se pueden comprobar con assertos a priori se comprueban
     * luego de instanciar al constructor de la superclase.
     * PostCondicion:
     * 
     * @param descripcion
     * String: descricion de la excepcion.
     */
    public EmpresaException(String descripcion) {
        super(descripcion);
        assert(descripcion != null): ("Descripcion excepcion nula.");
        assert(!descripcion.isEmpty()) : ("Descripcion vacia.");
    }
    
    @Override
    public String toString(){
        return getMessage();
    }
}
