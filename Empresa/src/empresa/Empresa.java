package empresa;

import java.util.ArrayList;
import java.util.Calendar;
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
        pedidos = new HashMap<Integer, Pedido>();
        
        /*
         * Las siguientes lineas inicializan los sectores, los
         * privilegios y los empleados necesarios para realizar
         * el testeo (no disponemos de persistencia)
         */
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
        Empleado eVentas = new Empleado(1, "Empleado ventas", s1);
        Empleado eProduccion = new Empleado(2, "Empleado produccion", s2);
        Empleado eContabilidad = new Empleado(3, "Empleado contabilidad", s3);
        Empleado eInspeccion = new Empleado(4, "Empleado Inspeccion", s4);
        Empleado eCalidad = new Empleado(5, "Empleado calidad", s5);
        Empleado admin = new Empleado(9999, "ADMIN", s6);
        listaEmpleados.put(admin.getLegajo(), admin);
        listaEmpleados.put(eVentas.getLegajo(), eVentas);
        listaEmpleados.put(eProduccion.getLegajo(), eProduccion);
        listaEmpleados.put(eContabilidad.getLegajo(), eContabilidad);
        listaEmpleados.put(eInspeccion.getLegajo(), eInspeccion);
        listaEmpleados.put(eCalidad.getLegajo(), eCalidad);
        
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
    
    /**
     * Metodo: deslog
     * Este metodo desloguea al usuario del sistema.
     * PreCondicion:
     * Debe haber un usuario logueado.
     * PostCondicion:
     * El atributo user toma el valor null.
     */
    public void deslog(){
        assert(user != null) : ("No hay ningun usuario logueado.");
        user = null;
    }
    
    /**
     * Metodo: consultaFaltantes
     * Consulta al inventario para ver si satisface un pedido. Devuelve
     * un listado con los materiales que no puedan satisfacer 
     * el pedido en el inventario y las respectivas cantidades.
     * PreCondicion:
     * El pedido se encuentra en estado: en_evaluacion.
     * PostCondicion:
     * Listado con la cantidad de cada material que no pueda satisfacer
     * el inventario. Si puede satisfacer completamente, listado vacio.
     * @param nPed
     * int: el numero de pedido sobre el cual se consultaran las existencias.
     * @return
     * HashMap<Integer, Material>: Listado con los materiales en falta y
     * sus respectivas cantidades.
     * @throws EmpresaException
     * Si codigo de pedido no se encuentra en el listado de pedidos
     * lanza esta excepcion.
     */
    private HashMap<Integer, Material> consultaFaltantes(int nPed)
        throws EmpresaException
    {
        if(!pedidos.containsKey(nPed))
            throw new EmpresaException("Pedidio inexistente.");
        assert(pedidos.get(nPed).getEstado() == Pedido.EN_EVALUACION) : ("Pedido no esta en estado de evaluacion.");
        HashMap<Integer, Material> faltante = new HashMap<Integer, Material>();
        Iterator<Material> it = pedidos.get(nPed).materialesNecesarios().values().iterator();
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
    
    /**
     * Metodo: consultaStock
     * Retorna la cantidad de existencias para un determinado
     * material a partir de su codigo.
     * PreCondicion: 
     * 
     * PostCondicion:
     * 
     * @param codigo
     * int: codigo del material buscado.
     * @return
     * double: cantidad de material.
     * @throws EmpresaException
     * Si codigo de material no existe en el inventario lanza
     * esta excepcion.
     */
    public double consultaStock(int codigo)
        throws EmpresaException
    {
        if(!inventario.containsKey(codigo))
            throw new EmpresaException("Material inexistente.");
        double existencia = inventario.get(codigo).getCantidad();
        return existencia;
    }
    
    /**
     * Metodo: iniciarPedido
     * Inserta un pedido al listado de pedidos.
     * PreCondicion: 
     * El empleado debe poseer permisos para realizar la operacion.
     * Se asume que la fecha de entrega no sera nula y será una fecha
     * en el futuro.
     * Se asume una cantidad a producir mayor que cero.
     * PostCondicion:
     * Se agrega un pedido nuevo al listado de pedidos.
     * @param codMaq
     * int: Codigo de la maquina que se desea fabricar en el pedido.
     * @param cantidad
     * int: Cantidad de maquinas a producir para el pedido.
     * @param fechaEntrega
     * Calendar: fecha de entrega solicitada.
     */
    public void iniciarPedido(int codMaq, int cantidad, Calendar fechaEntrega)
        throws EmpresaException
    {
        assert(user.autorizaOperacion(OP_INIPED)) : ("Usuario no autorizado para realizar operación");
        assert(fechaEntrega != null) : ("Fecha de entrega nula");
        assert(GregorianCalendar.getInstance().before(fechaEntrega)) : ("Fecha de entrega en el pasado.");
        assert(cantidad > 0) : ("Cantidad no valida.");
        if(!productos.containsKey(codMaq))
            throw new EmpresaException("Producto a fabricar inexistente.");
        Pedido p = new Pedido(productos.get(codMaq), cantidad, fechaEntrega);
        pedidos.put(p.getNroPedido(), p);
    }
    
    /**
     * Metodo aceptarPedido
     * Acepta un pedido solicitado por su codigo de pedido del listado de pedidos.
     * PreCondicion: 
     * El empleado debe poseer permisos para realizar la operación.
     * La fechaPropuesta no debe ser nula y debe encontrarse
     * en el futuro.
     * PostCondicion:
     * El listado de pedidos tiene un pedido mas en estado de en evaluacion.
     * @param nPed
     * int: Numero de pedido a aceptar.
     * @throws EmpresaException
     * Si el pedido no existe o su estado no es iniciado se lanza esta excepcion.
     */
    public void aceptarPedido(int nPed, Calendar fechaPropuesta)
        throws EmpresaException
    {
        assert(user.autorizaOperacion(OP_ACEPTPED)) : ("Usuario no autorizado para realizar operación");
        assert(fechaPropuesta != null) : ("Fecha nula.");
        assert(fechaPropuesta.after(GregorianCalendar.getInstance())) : ("Fecha propuesta en el pasado.");
        if(!pedidos.containsKey(nPed))
            throw new EmpresaException("Pedido inexistente.");
        Pedido p = pedidos.get(nPed);
        if(!(p.getEstado() == Pedido.INICIADO))
            throw new EmpresaException("Pedido no esta en estado de iniciado.");
        p.estadoEvaluacion(fechaPropuesta);
    }
    
    /**
     * Metodo: reservarMateriales
     * A partir de un pedido, reserva(retira del stock) todos los materiales
     * necesarios para cumplimentarlo.
     * Precondicon:
     * Este es un metodo privado, se supone que los materiales en la
     * receta se encuentran en el inventario (hecho que corroboran otros
     * metodos de la clase).
     * PostCondicion:
     * Las existencias del inventario se reducen en las cantidades 
     * requeridas por la receta de la maquina multiplicada
     * por la cantidad de maquinas del pedido.
     * @param nPed
     * int: numero de pedido
     * @throws EmpresaException
     * Si el inventario no fuese suficiente para cumplir algun pedido
     * se lanza esta excepcion
     */
    private void reservarMateriales(int nPed)
        throws EmpresaException
    {
        if(!consultaFaltantes(nPed).isEmpty())
            throw new EmpresaException("Stock de materiales insuficiente.");
        Iterator<Material> it = pedidos.get(nPed).materialesNecesarios().values().iterator();
        while(it.hasNext()){
            Material matReceta = it.next();
            Material matStock = inventario.get(matReceta.getCodigoMaterial());
            matStock.registrarRetiro(matReceta.getCantidad());
        }
    }
    
    
    /**
     * Metodo generarLote
     * Genera un lote de produccion a partir de un pedido que se encuentra en estado de evaluacion.
     * Al hacerlo el estado del pedido pasa a ser ACEPTADO y se asigna 
     * una fecha definitiva.
     * Precondicion:
     * El empleado debe poseer permisos para realizar la operación.
     * La fecha definitiva no puede ser nula.
     * La fecha definitiva debe estar en el futuro.
     * PostCondicion:
     * El listado de pedidos tiene un pedido mas en estado de aceptado.
     * @param nPed
     * int: Numero de pedido a aceptar.
     * @throws EmpresaException
     * Si el pedido no existe o su estado no es en evaluacion se lanza esta excepcion.
     */
    public void generarLote(int nPed, Calendar fechaDefinitiva)
        throws EmpresaException
    {
        assert(user.autorizaOperacion(OP_GENLOTE)) : ("Usuario no esta autorizado para realizar operación.");
        assert(fechaDefinitiva != null) : ("Fecha definitiva nula");
        assert(fechaDefinitiva.after(GregorianCalendar.getInstance())) : ("Fecha definitiva en el pasado.");
        if(!pedidos.containsKey(nPed))
            throw new EmpresaException("Pedido inexistente.");
        Pedido p = pedidos.get(nPed);
        if(!(p.getEstado() == Pedido.EN_EVALUACION))
            throw new EmpresaException("Pedido no esta en estado de evaluacion.");
        reservarMateriales(nPed);
        p.estadoAceptado(fechaDefinitiva);
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
        Observacion o = new Observacion(tema, user.getLegajo(), obs);
        p.insertarObservacion(o);
    }
    
    /**
     * Metodo: materialesNecesarios
     * Devuelve un listado de los materiales necesarios
     * para cumplir un pedido.
     * PreCondicion:
     * 
     * PostCondicion:
     * Se devuelve una cadena con el listado de materiales necesarios.
     * @param nPed
     * int: numero de pedido.
     * @return
     * String: cadena que contiene el listado de materiales.
     * @throws EmpresaException
     * Si el codigo de maquina no existe se lanza esta excepcion.
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
    
    /**
     * Metodo: listarObservaciones
     * Para un determinado numero de pedido se obtiene un listado
     * de todas las observaciones realizadas.
     * PreCondicion:
     * 
     * PostCondicion:
     * Se obtiene cadena con todas las observaciones.
     * @param nPed
     * int: numero de pedido a consultar.
     * @return
     * String listado de Observaciones.
     * @throws EmpresaException
     * Si el pedido no se encuentra en la lista de pedidos se
     * lanza esta excepcion.
     */
    public String listarObservaciones(int nPed)
        throws EmpresaException
    {
        if(!pedidos.containsKey(nPed))
            throw new EmpresaException("Pedido inexistente.");
        Iterator<Observacion> it = pedidos.get(nPed).getListaObservaciones().iterator();
        String info = "";
        while(it.hasNext())
            info += it.next().toString() + System.lineSeparator();
        return info;
    }
    
}
