package empresa;

public class Material {
    private int codigoMaterial;
    private String descripcion;
    private double cantidad;
    
    public Material(int codigoMaterial, String descripcion, double cantidad) {
        this.codigoMaterial = codigoMaterial;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }


    public int getCodigoMaterial() {
        return codigoMaterial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }
    
    /**
     * Metodo: registrarRetiro
     * Retira una cantidad pasada como parametro del disponible para el material.
     * PreCondicion:
     * La cantidad a retirar es mayor a cero.
     * PostCondicion:
     * La cantidad disponible es igual a la cantidad anterior menos la retirada.
     * @param cantidad
     * double: cantidad a retirar.
     * @throws EmpresaException
     * Si la cantidad solicitada es mayor que la disponible lanza la excepcion.
     */
    public void registrarRetiro(double cantidad)
        throws EmpresaException
    {
        assert(cantidad > 0.0) : ("Cantidad a retirar no valida.");
        if(cantidad > this.cantidad)
            throw new EmpresaException("Cantidad a retirar no valida.");
        this.cantidad -= cantidad;    
    }
    
    /**
     * Metodo: registrarIngresa
     * Registra el ingreso de una cantidad de existencias para el material.
     * PreCondicion:
     * La cantidad a ingresar debe ser mayor que cero.
     * PostCondicion:
     * La cantidad disponible sera la cantidad original mas la ingresada.
     * @param cantidad
     * double: cantidad a ingresar
     * @throws EmpresaException
     */
    public void registrarIngreso(double cantidad)
        throws EmpresaException
    {
        assert(cantidad > 0.0) : ("Cantidad no valida.");
        this.cantidad += cantidad;
    }
    
    /**
     * Metodo: satisfacePedido
     * Indica si la cantidad disponible del material puede satisfacer una cantidad
     * solicitada como parametro.
     * PreCondicion:
     * 
     * PostCondicion:
     * 
     * @param cantidad
     * double: cantidad solicitad.
     * @return
     * boolean: indicador de si se puede o no satisfacer la cantidad.
     */
    public boolean satisfacePedido(double cantidad){
        return this.cantidad >= cantidad;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof Material))
            return false;
        return ((Material)o).codigoMaterial == codigoMaterial;
    }
}
