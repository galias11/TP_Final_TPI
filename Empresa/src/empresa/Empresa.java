package empresa;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

public class Empresa {
    private Empleado user;
    private HashMap<Integer, Empleado> listaEmpleados;
    private HashMap<Integer, Pedido> pedidos;
    private HashMap<Integer, Material> inventario;
    private HashMap<Integer, Maquina> productos;
    private HashMap<String, Sector> sectores;
    
    private final int OP_INIPED = 1;
    private final int OP_ACEPTPED = 2;
    private final int OP_GENLOTE = 3;
    private final int OP_OBSERVAR = 4;
    
    public Empresa() 
    {
        user = null;
        inventario = new HashMap<Integer, Material>();
        listaEmpleados = new HashMap<Integer, Empleado>();
        sectores = new HashMap<String, Sector>();
        Sector s1 = new Sector("Ventas");
        Sector s2 = new Sector("Produccion");
        Sector s3 = new Sector("Contabilidad");
        Sector s4 = new Sector("Inspeccion");
        Sector s5 = new Sector("Calidad");
        Sector s6 = new Sector("System ADM");
        Operacion op1 = new Operacion(OP_INIPED, "Iniciar pedido.");
        Operacion op2 = new Operacion(OP_ACEPTPED, "Aceptar pedido.");
        Operacion op3 = new Operacion(OP_GENLOTE, "Generar lote para pedido.");
        Operacion op4 = new Operacion(OP_OBSERVAR, "Realizar observacion a pedido.");
        try{
            s1.agregarPermiso(op1);
            s1.agregarPermiso(op3);
            s2.agregarPermiso(op2);
            s2.agregarPermiso(op3);
            s2.agregarPermiso(op4);
            s3.agregarPermiso(op4);
            s4.agregarPermiso(op4);
            s5.agregarPermiso(op4);
            s6.agregarPermiso(op1);
            s6.agregarPermiso(op2);
            s6.agregarPermiso(op3);
            s6.agregarPermiso(op4);
        } catch(EmpresaException e){
            
        }
        sectores.put(s1.getNombre(), s1);
        sectores.put(s1.getNombre(), s2);
        sectores.put(s1.getNombre(), s3);
        sectores.put(s1.getNombre(), s4);
        sectores.put(s1.getNombre(), s5);
        sectores.put(s1.getNombre(), s6);
        Empleado admin = new Empleado(9999, "ADMIN", s6);
        listaEmpleados.put(admin.getLegajo(), admin);
        pedidos = new HashMap<Integer, Pedido>();
    }
    
    /**
     * Metodo: login.
     * Intenta ingresar al sistema con nro de legajo.
     * PreCondicion:
     * No debe haber un empleado logueado en esta terminal (user == null)
     * PostCondicion:
     * Se asigna al atributo user el empleado indicado.
     * @param nroLegajo
     * int: numero de legajo del empledado.
     * @return
     * 
     * @throws EmpresaException
     * Si el empleado no se encuentra en el listado de empledos tira esta excepcion.
     */
    public void login(int nroLegajo)
        throws EmpresaException
    {
        assert(user == null) : ("Ya hay un empleado logueado.");
        if(!listaEmpleados.containsKey(nroLegajo))
            throw new EmpresaException("Empleado no existente.");
        user = listaEmpleados.get(nroLegajo);
    }
    
    private HashMap<Integer, Material> consultaInventario(HashMap<Integer, Material> listaMateriales){
        HashMap<Integer, Material> faltante = new HashMap<Integer, Material>();
        Iterator<Material> it = listaMateriales.values().iterator();
        while(it.hasNext()){
            Material auxM = it.next();
            if(inventario.containsKey(auxM.getCodigoMaterial())){
                Material existencia = inventario.get(auxM.getCodigoMaterial());
                if(!existencia.satisfacePedido(auxM.getCantidad()))
                    faltante.put(auxM.getCodigoMaterial(), new Material(auxM.getCodigoMaterial(), auxM.getDescripcion(), auxM.getCantidad()));
            }
        }
        return faltante;
    }
    
    public String informeFalatantes(HashMap<Integer, Material> listaMateriales){
        String info = "";
        HashMap<Integer, Material> faltante = consultaInventario(listaMateriales);
        if(faltante.isEmpty())
            info = "Invetario suficiente.";
        else{
            Iterator<Material> it = faltante.values().iterator();
            while(it.hasNext())
                info += it.next().toString() + System.lineSeparator();
        }
        return info;
    }
    
    public double consultaStock(int codigo){
        double existencia = 0.0;
        if(inventario.containsKey(codigo))
            existencia = inventario.get(codigo).getCantidad();
        return existencia;
    }
    
    /**
     *
     * Metodo: iniciarPedido
     * Inserta un pedido al listado de pedidos.
     * PreCondicion: 
     * El empleado debe poseer permisos para realizar la operacion.
     * PostCondicion:
     * Se agrega un pedido nuevo al listado de pedidos.
     * @param codMaq
     * int: Codigo de la maquina que se desea fabricar en el pedido.
     */
    public void iniciarPedido(int codMaq)
        throws EmpresaException
    {
        assert(user.autorizaOperacion(OP_INIPED)) : ("Usuario no autorizado para realizar operación");
        if(!productos.containsKey(codMaq))
            throw new EmpresaException("Producto a fabricar inexistente.");
        Pedido p = new Pedido(productos.get(codMaq), GregorianCalendar.getInstance());
        pedidos.put(p.getNroPedido(), p);
    }
    
    /**
     * Metodo aceptarPedido
     * Acepta un pedido solicitado por su codigo de pedido del listado de pedidos.
     * PreCondicion: 
     * El empleado debe poseer permisos para realizar la operación.
     * PostCondicion:
     * El listado de pedidos tiene un pedido mas en estado de iniciado.
     * @param nPed
     * int: Numero de pedido a aceptar.
     * @throws EmpresaException
     * Si el pedido no existe o su estado no es iniciado se lanza esta excepcion.
     */
    public void aceptarPedido(int nPed)
        throws EmpresaException
    {
        assert(user.autorizaOperacion(OP_ACEPTPED)) : ("Usuario no autorizado para realizar operación");
        if(!pedidos.containsKey(nPed))
            throw new EmpresaException("Pedido inexistente.");
        Pedido p = pedidos.get(nPed);
        if(!(p.getEstado() == Pedido.INICIADO))
            throw new EmpresaException("Pedido no esta en estado de iniciado.");
        p.estadoEvaluacion();
    }
    
    /**
     * Metodo generarLote
     * Genera un lote de produccion a partir de un pedido que se encuentra en estado de evaluacion.
     * Precondicion:
     * El empleado debe poseer permisos para realizar la operación.
     * PostCondicion:
     * El listado de pedidos tiene un pedido mas en estado de aceptado.
     * @param nPed
     * int: Numero de pedido a aceptar.
     * @throws EmpresaException
     * Si el pedido no existe o su estado no es en evaluacion se lanza esta excepcion.
     */
    public void generarLote(int nPed)
        throws EmpresaException
    {
        assert(user.autorizaOperacion(OP_GENLOTE)) : ("Usuario no esta autorizado para realizar operación.");
        if(!pedidos.containsKey(nPed))
            throw new EmpresaException("Pedido inexistente.");
        Pedido p = pedidos.get(nPed);
        if(!(p.getEstado() == Pedido.EN_EVALUACION))
            throw new EmpresaException("Pedido no esta en estado de evaluacion.");
        p.estadoAceptado();
    }
    
    /**
     * Metodo observarPedido.
     * Agrega una observacion al pedido.
     * PreCondicion: 
     * La observacion no supera los 500 caracteres.
     * El tema es un tema valido.
     * PostCondicion:
     * El listado de observaciones tiene una observación más.
     * @param nPed
     * int: numero de pedido a observar.
     * @param tema
     * String: tema descriptivo de la Observacion, definido como constante por la
     * clase observacion.
     * @param obs
     * String: Descripcion de la observacion.
     * @throws EmpresaException
     * Si el pedido no existe o su estado no es en evaluacion se lanza esta excepcion.
     */
    public void observarPedido(int nPed, String tema, String obs)
        throws EmpresaException
    {
        assert(tema.equals(Observacion.FECHAS) || tema.equals(Observacion.INSUMOS) ||
               tema.equals(Observacion.OTROS)) : ("Tema no valido.");
        assert(obs.length() <= 500) : ("Observacion tiene mas caracteres de los permitidos.");
        if(!pedidos.containsKey(nPed))
            throw new EmpresaException("Pedido inexistente.");
        Pedido p = pedidos.get(nPed);
        if(!(p.getEstado() != Pedido.EN_EVALUACION))
            throw new EmpresaException("Pedido no esta en estado de evaluacion.");
        Observacion o = new Observacion(tema, user, obs);
        p.insertarObservacion(o);
    }
    
    /**
     * Metodo: materialesNecesarios
     * Devuelve un listado de los materiales necesarios para cumplir con un pedido.
     * PreCondicion:
     * 
     * PostCondicion:
     * Se devuelve una cadena con el listado de materiales necesarios.
     * @param nPed
     * int: codigo de pedido.
     * @return
     * String: cadena que contiene el listado de materiales.
     * @throws EmpresaException
     * Si el numero de pedido no existe se lanza esta excepcion.
     */
    public String materialesNecesaarios(int nPed)
        throws EmpresaException
    {
        if(!pedidos.containsKey(nPed))
            throw new EmpresaException("Pedido inexistente.");
        Pedido p = pedidos.get(nPed);
        return p.listadoMateriales();
    }
    
    /**
     * Metodo: modificarCantidadReceta
     * Mofica las cantidades para un determinado material de la receta de un producto.
     * PreCondicion:
     * La cantidad a modificar es mayor que cero.
     * PostCondicion: 
     * La cantidad necesaria para el material indicado es igual a la ingresada como parametro.
     * @param codMaq
     * int: codigo de maquina a modificar.
     * @param codMat
     * int: codigo de material a modificar.
     * @param cant
     * double: nueva cantidad.
     * @throws EmpresaException
     * Si el la maquina no existe o el material no esta en su receta se lanza esta excepcion.
     */
    public void modificarCantidadReceta(int codMaq, int codMat, double cant)
        throws EmpresaException
    {
        assert(cant > 0.0) : ("Cantidad no valida.");
        if(!productos.containsKey(codMaq))
            throw new EmpresaException("Maquina inexistente.");
        Maquina m = productos.get(codMaq);
        m.modificarCantidadMaterial(codMat, cant);
    }
    
    /**
     * Metodo: agregarMaterialReceta
     * 
     * PreCondicion:
     * La cantidad es mayor que cero.
     * PostCondicion:
     * La cantidad necesaria del material, para la maquina indicados por parametro
     * es igual a la canitdad insertada como parametro.
     * @param codMaq
     * int: codigo de la maquina a modificar.
     * @param codMat
     * int: codigo del material a modificar.
     * @param cantidad
     * double: nueva cantidad a asignar
     * @throws EmpresaException
     * Si la maquina no existe o el material no se registra el sistema de inventarios
     * se lanza esta excepcion.
     */
    public void agregarMaterialReceta(int codMaq, int codMat, double cant)
        throws EmpresaException
    {
        assert(cant > 0.0) : ("Cantidad no valida.");
        if(!productos.containsKey(codMaq))
            throw new EmpresaException("Maquina inexistente.");
        Maquina maq = productos.get(codMaq);
        if(!inventario.containsKey(codMat))
            throw new EmpresaException("Material no registrado.");
        Material mat = inventario.get(codMat);
        maq.agregarMaterial(new Material(mat.getCodigoMaterial(), mat.getDescripcion(), cant));
    }
    
    /**
     * Metodo: eliminarMaterialReceta
     * Elimina un material de la receta de la maquina indicada.
     * @param codMaq
     * int: codigo de la maquina.
     * @param codMat
     * int: codigo del material.
     * @throws EmpresaException
     * Si la maquina no existe o el material no se encuentra en su receta se lanza
     * esta excepcion.
     */
    public void eliminarMaterialReceta(int codMaq, int codMat)
        throws EmpresaException
    {
        if(!productos.containsKey(codMaq))
            throw new EmpresaException("Maquina inexistente.");
        Maquina m = productos.get(codMaq);
        m.eliminarMaterial(codMat);
    }
    
}
