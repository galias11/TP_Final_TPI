package empresa;

public class Operacion {
    private int codigo;
    private String descripcion;
    
    /**
     * Constructor vacio para serializacion.
     * No utilizar.
     */
    public Operacion(){
        
    }
    
    public Operacion(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
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

    
}
