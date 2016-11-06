package empresa;

/**
 * Clase Operacion.
 * Representa una operación a realizar dentro del sistema.
 * inv:
 * codigo mayor que cero.
 * descripcion no nula y no vacia.
 */
public class Operacion {
    private int codigo;
    private String descripcion;
    
    /**
     * Constructor vacio para serializacion.
     * No utilizar.
     */
    public Operacion(){
        
    }
    
    /**
     * Constructor con parametros.
     * Precondicion:
     * codigo mayor que cero.
     * descripcion no nula ni vacia.
     * @param codigo
     * int: codigo de la operacion.
     * @param descripcion
     * String: descripcion de la operacion.
     */
    public Operacion(int codigo, String descripcion) {
        assert(codigo > 0) : ("Codigo no valido.");
        assert(descripcion != null) : ("Descripcion nula");
        assert(!descripcion.isEmpty()) : ("Descripcion vacia");
        this.codigo = codigo;
        this.descripcion = descripcion;
        verificarInvariante();
    }
    
    /*
     * ***********************************************
     * Getters y setters agregados para serializacion.
     * ***********************************************
     */


    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /*
     * ***********************************************
     */


    public int getCodigo() {
        return codigo;
    }

    private void verificarInvariante(){
        assert(codigo > 0) : ("Codigo no valido.");
        assert(descripcion != null) : ("Descripcion nula");
        assert(!descripcion.isEmpty()) : ("Descripcion vacia");
    }
}
