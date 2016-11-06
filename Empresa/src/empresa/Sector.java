package empresa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase Sector.
 * Representa un sector dentro de la empresa, contiene la lista
 * de permisos que estan a autorizados a realizar los empleados
 * de cada sector.
 * inv:
 * el atributo nombre no sera nulo ni vacio.
 * el atributo permisos no sera nulo.
 */
public class Sector {
    private String nombre;
    private HashMap<Integer,  Operacion> permisos;

    /**
     * @associates <{empresa.Operacion}>
     */
    private Map newAtt;

    /**
     * Constructor vacio para serializacion.
     * No utilizar.
     */
    public Sector(){
        
    }
    
    /**
     * Constructor con parametros.
     * PreCondicion:
     * el parametro nombre no puede ser ni nulo ni vacio.
     * PostCondicion:
     * 
     * @param nombre
     * String: nombre del sector a crear.
     */
    public Sector(String nombre) {
        assert(nombre != null) : ("Nombre nulo");
        assert(!nombre.isEmpty()) : ("Nombre vacio");
        this.nombre = nombre;
        permisos = new HashMap<Integer, Operacion>();
        verificarInvariante();
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
    
    /**
     * Metodo: agregarPermiso
     * Agrega un nuevo permiso a la lista de autorizaciones que
     * tiene el sector.
     * PreCondicion:
     * la operacion a agregar no puede ser nula.
     * PostCondicion:
     * El listado de pedidos tendra un elemento mas que su cantidad
     * original.
     * @param op
     * Operacion: operacion a la cual se desea dar autorizacion
     * para realizar al sector
     * @throws EmpresaException
     * Si la operación ya se encuentra en el listado de autorizadas
     * lanza esta excepcion.
     */
    public void agregarPermiso(Operacion op)
        throws EmpresaException
    {
        assert(op != null) : ("Operacion nula.");
        if(!permisos.containsKey(op.getCodigo()))
            permisos.put(op.getCodigo(), op);
        else
            throw new EmpresaException("Permiso ya existente.");
        verificarInvariante();
    }
    
    /**
     * Metodo que indica si el sector da autorizacion a realizar
     * una determinada operacion pasada como parametro.
     * @param codOp
     * int: codigo de la operacion a consultar.
     * @return
     * boolean: true si esta autorizado, false en caso contrario.
     */
    public boolean permiteOperar(int codOp){
        return permisos.containsKey(codOp);
    }
    
    private void verificarInvariante(){
        assert(nombre != null) : ("Nombre nulo.");
        assert(!nombre.isEmpty()) : ("Nombre vacio.");
        assert(permisos != null) : ("Lista de permisos nula");
    }
}
