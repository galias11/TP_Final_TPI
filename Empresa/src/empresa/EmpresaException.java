package empresa;

public class EmpresaException extends Exception{
    private String descripcion;
    
    public EmpresaException(String descripcion) {
        super(descripcion);
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return descripcion;
    }
}
