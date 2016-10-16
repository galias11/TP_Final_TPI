package empresa;

public class Operacion {
    private int codigo;
    private String descripcion;
    
    
    public Operacion(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }


    public int getCodigo() {
        return codigo;
    }

    
}
