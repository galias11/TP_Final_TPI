package empresa;

import java.util.ArrayList;
import java.util.HashMap;

public class Sector {
    private String nombre;
    private HashMap<Integer,  Operacion> permisos;
    
    /**
     * Constructor vacio para serializacion.
     * No utilizar.
     */
    public Sector(){
        
    }
    
    public Sector(String nombre) {
        this.nombre = nombre;
        permisos = new HashMap<Integer, Operacion>();
    }
    
    /*
     * ***********************************************
     * Getters y setters agregados para serializacion.
     * ***********************************************
     */


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPermisos(HashMap<Integer, Operacion> permisos) {
        this.permisos = permisos;
    }

    public HashMap<Integer, Operacion> getPermisos() {
        return permisos;
    }

    /*
     * ***********************************************
     */

    public String getNombre() {
        return nombre;
    }
    
    public void agregarPermiso(Operacion op)
        throws EmpresaException
    {
        if(!permisos.containsKey(op.getCodigo()))
            permisos.put(op.getCodigo(), op);
        else
            throw new EmpresaException("Permiso ya existente.");
    }
    
    public boolean permiteOperar(int codOp){
        return permisos.containsKey(codOp);
    }
    

}
