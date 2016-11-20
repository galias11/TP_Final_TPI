package empresa;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase Maquina.
 * Clase que representa a cada uno de los productos que fabrica
 * la empresa.
 * invariante:
 * codigo debe ser un valor entre 1 y 999999.
 * descripcion debe ser distinto de null y no vacio.
 * listado de materiales no puede ser nulo.
 */
public class Maquina {
    private int codigo;
    private String descripcion;
    private HashMap<Integer, Material> listadoMateriales;

    /**
     * @associates <{empresa.Material}>
     * @aggregation shared
     */
    private Map newAtt;

    /**
     * Constructor vacio para serializacion.
     * No utilizar.
     */
    public Maquina(){
        
    }
    
    /**
     * Contructor con parametros.
     * PreCondicion:
     * codigo se debe encontrar en un rango entre 1 y 999999.
     * descripcion debe ser distinto de null y no vacio.
     * @param codigo
     * int: codigo identificatorio de la maquina.
     * @param descripcion
     * String: texto descriptivo de la maquina.
     */
    public Maquina(int codigo, String descripcion){
        assert(descripcion != null) : ("Descripcion nula.");
        assert(!descripcion.isEmpty()) : ("Descripcion vacia.");
        assert(codigo > 0 && codigo < 1000000) : ("Codigo fuera de rango.");
        this.codigo = codigo;
        this.descripcion = descripcion;
        listadoMateriales = new HashMap<Integer, Material>();
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

    public void setListadoMateriales(HashMap<Integer, Material> listadoMateriales) {
        this.listadoMateriales = listadoMateriales;
    }

    /*
     * ***********************************************
     */

    public String getDescripcion() {
        return descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public HashMap<Integer, Material> getListadoMateriales() {
        return listadoMateriales;
    }
    
    /**
     * Metodo: agregarMaterial
     * Agrega un nuevo material a la lista de materiales de la maquina.
     * PreCondicion:
     * El material a insertar no es nulo.
     * PostCondicion:
     * La lista de materiales incrementa la cantidad de materiales en 1.
     * Ahora contiine al material pasado como parametro.
     * @param material
     * Material: material a insertar con su respectiva cantidad.
     * @throws EmpresaException
     * Si la receta para la maquina ya contiene el material se lanza esta excepcion.
     */
    public void agregarMaterial(Material material)
        throws EmpresaException
    {
        assert(material != null) : ("Material nulo");
        if(!listadoMateriales.containsKey(material.getCodigoMaterial()))
            listadoMateriales.put(material.getCodigoMaterial(), material);
        else
            throw new EmpresaException("El material ya se encuentra en el listado.");
        verificarInvariante();
    }
    
    /**
     * Metodo: modificarCantidadMaterial
     * Modifica la cantidad necesaria para un material pasado como parametro.
     * PreCondicion:
     * La cantidad a modificar es mayor que cero.
     * PostCondicion:
     * La cantidad necesaria para el material ahora es el valor del parametro.
     * @param codigo
     * int: codigo del material a modificar.
     * @param cantidad
     * double: nuevo valor para la cantidad.
     * @throws EmpresaException
     * Si el material no se encuentra en el listado de materiales se lanza esta excepcion.
     */
    public void modificarCantidadMaterial(int codigo, double cantidad)
        throws EmpresaException
    { 
        assert(cantidad > 0.0) : ("Cantidad no valida.");
        if(listadoMateriales.containsKey(codigo)){
            Material mAux = listadoMateriales.get(codigo);
            Material mInsert = new Material(codigo, mAux.getDescripcion(), cantidad);
            listadoMateriales.put(codigo, mInsert);
        }else
            throw new EmpresaException("El material no se encuentra en el listado.");
        verificarInvariante();
    }
    
    /**
     * Metodo: eliminarMaterial
     * Elimina un material de la receta de la maquina.
     * PreCondicion:
     * 
     * PostCondicion:
     * El tamaño del listado se decrementa en 1 y ya no contiene al codigo indicado.
     * @param codigo
     * int: codigo del material a eliminar.
     * @throws EmpresaException
     * Si el material no se encuentra en la receta de la maquina se lanza esta excepcion.
     */
    public void eliminarMaterial(int codigo)
        throws EmpresaException {
        if(listadoMateriales.containsKey(codigo)){
            Material mAux = new Material(codigo, "A borrar", 0.0);
            listadoMateriales.remove(codigo, mAux);
        } else
            throw new EmpresaException("El material no se encuentra en el listado.");
        verificarInvariante();
    }

    /**
     * Invariante de clase (ver comentario de clase)
     */
    private void verificarInvariante(){
        assert(codigo > 1 && codigo < 1000000) : ("Codigo fuera de rango");
        assert(descripcion != null) : ("Descripcion nula.");
        assert(!descripcion.isEmpty()) : ("Descripcion vacia.");
        assert(listadoMateriales != null) : ("Listado de materiales nulo.");
    }
}
