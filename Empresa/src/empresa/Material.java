package empresa;

/**
 * Clase: Material
 * Esta clase representa a un material, puede ser tanto un
 * material del stock de la empresa como un material 
 * en una receta de un producto o un material auxiliar que
 * se instancia.
 * invariante:
 * codigoMaterial debe estar en un rango entre 1 y 99999
 * descripcion no puede ser nulo ni vacio.
 * cantidad tiene que ser mayor o igual que 0.0
 */
public class Material {
    private int codigoMaterial;
    private String descripcion;
    private double cantidad;
    
    /**
     * Constructor vacio para serializacion.
     * No utilizar.
     */
    public Material(){
        
    }
    
    /**
     * Constructor con parametros.
     * Precondicion:
     * codigo material en rango 1..99999
     * descripcion no nula ni vacia
     * cantidad mayor o igual que 0
     * @param codigoMaterial
     * int: codigo del material
     * @param descripcion
     * String: descripcion del material
     * @param cantidad
     * cantidad: cantidad a setear del material.
     */
    public Material(int codigoMaterial, String descripcion, double cantidad) {
        assert(codigoMaterial > 0 && codigoMaterial < 100000) : ("CodigoMaterial fuera de rango");
        assert(descripcion != null) : ("Descripcion nula.");
        assert(!descripcion.isEmpty()) : ("Descripcion vacia.");
        assert(cantidad >= 0.0) : ("Cantidad menor o igual que cero");
        this.codigoMaterial = codigoMaterial;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        verificarInvariante();
    }
    
    /*
     * ***********************************************
     * Getters y setters agregados para serializacion.
     * ***********************************************
     */

    public void setCodigoMaterial(int codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    /*
     * ***********************************************
     */


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
        verificarInvariante();
    }
    
    /**
     * Metodo: registrarIngreso
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
        verificarInvariante();
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
    
    private void verificarInvariante(){
        assert(codigoMaterial > 0 && codigoMaterial < 100000) 
            : ("Codigo material fuera de rango");
        assert(descripcion != null) : ("Descripcion nula");
        assert(!descripcion.isEmpty()) : ("Descripcion vacia.");
        assert(cantidad >= 0.0) : ("Cantidad por debajo de cero.");
    }
}
