package empresa;

import java.util.HashMap;

public class Maquina {
    private int codigo;
    private String descripcion;
    private HashMap<Integer, Material> listadoMateriales;
    
    public Maquina(int codigo, String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
        listadoMateriales = new HashMap<Integer, Material>();
    }


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
    }
    
    /**
     * Metodo: modificarCantidadMaterial
     * Modifica la cantidad necesaria para un material pasado como parametro.
     * PreCondicion:
     * La cantidad a modificcar es mayor que cero.
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
        if(listadoMateriales.containsKey(codigo)){
            Material mAux = listadoMateriales.get(codigo);
            Material mInsert = new Material(codigo, mAux.getDescripcion(), cantidad);
            listadoMateriales.put(codigo, mInsert);
        }else
            throw new EmpresaException("El material no se encuentra en el listado.");
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
    }

}
