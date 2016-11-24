package empresa;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JOptionPane;

/**
 * Clase Pedido.
 * Representa a cada pedido de la empresa.
 * inv:
 * (Por estado):
 * **INICIADO**
 *  nroPedido mayor que 0 y menor que 1000000.
 *  fechaPedido no nulo.
 *  maquina no nula.
 *  cantidad mayor que 0 y menor que o igual 999.
 *  fechaEntrega no nulo.
 *  fechaPropPodroduccion nulo.
 *  fechaDefinitiva nula.
 *  fechaAceptacion nula.
 *  estado = INICIADO.
 *  listaObservaciones no nula.
 *  nroLote -1;
 * **EVALUACION** (se enumeran solo los cambios)
 *  fechaPropProduccion no nula.
 *  estado = EN_EVALUACION.
 * **ACEPTADO** (se enumeran solo los cambios)
 *  fechaDefinitiva no nula.
 *  fechaAceptacion no nula.
 *  estado = ACEPTADO.
 *  nroLote > 0
 * **CANCELADO**
 *  Estado de evaluacion o iniciado (fechaPropProduccion puede ser null) + estado = Cancelado.
 */
public class Pedido {
    private static int ultPedido = 0;
    private static int ultLote = 0;
    private int nroPedido;
    private Calendar fechaPedido;
    private Maquina maquina;
    private int cantidad;
    private Calendar fechaEntrega;
    private Calendar fechaPropProduccion;
    private Calendar fechaDefinitiva;
    private Calendar fechaAceptacion;
    private int estado;
    private TreeSet<Observacion> listaObservaciones;
    private int nroLote;
    
    public static final int INICIADO = 0;
    public static final int EN_EVALUACION = 1;
    public static final int ACEPTADO = 2;
    public static final int CANCELADO = 3;
    
    /**
     * Constructor vacio para serializacion.
     * No utilizar.
     */
    public Pedido(){
        
    }
    
    /**
     * Constructor con parametros.
     * PreCondicion:
     * maquina no nulo
     * fechaEntrega no nulo
     * cantidad > 0 y cantidad <= 999
     * PostCondicion:
     * 
     * @param maquina
     * Maquina: maquina a producir para el pedido.
     * @param cantidad
     * int: cantidad de maquinas a fabricar.
     * @param fechaEntrega
     * Calendar: fecha de entrega solicitada.
     */
    public Pedido(Maquina maquina, int cantidad, Calendar fechaEntrega) {
        assert(maquina != null) : ("Maquina nula");
        assert(cantidad > 0 && cantidad <= 999) : ("Cantidad fuera de rango.");
        assert(fechaEntrega != null) : ("Fecha nula");
        this.nroPedido = ++ultPedido;
        this.fechaPedido = fechaActual();
        this.maquina = maquina;
        this.cantidad = cantidad;
        resetHoraFecha(fechaEntrega);
        this.fechaEntrega = fechaEntrega;
        this.fechaPropProduccion = null;
        this.fechaDefinitiva = null;
        this.fechaAceptacion = null;
        this.estado = this.INICIADO;
        this.listaObservaciones = new TreeSet<Observacion>();
        this.nroLote = -1;
        verificarInvariante();
    }
    
    /*
     * ***********************************************
     * Getters y setters agregados para serializacion.
     * ***********************************************
     */


    public static void setUltPedido(int ultPedido) {
        Pedido.ultPedido = ultPedido;
    }

    public static int getUltPedido() {
        return ultPedido;
    }

    public static void setUltLote(int ultLote) {
        Pedido.ultLote = ultLote;
    }

    public static int getUltLote() {
        return ultLote;
    }

    public void setNroPedido(int nroPedido) {
        this.nroPedido = nroPedido;
    }

    public void setFechaPedido(Calendar fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFechaEntrega(Calendar fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setFechaPropProduccion(Calendar fechaPropProduccion) {
        this.fechaPropProduccion = fechaPropProduccion;
    }

    public void setFechaDefinitiva(Calendar fechaDefinitiva) {
        this.fechaDefinitiva = fechaDefinitiva;
    }

    public void setFechaAceptacion(Calendar fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setListaObservaciones(TreeSet<Observacion> listaObservaciones) {
        this.listaObservaciones = listaObservaciones;
    }

    public void setNroLote(int nroLote) {
        this.nroLote = nroLote;
    }

    /*
     * ***********************************************
     */


    public int getNroPedido() {
        return nroPedido;
    }

    public Calendar getFechaPedido() {
        return fechaPedido;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public Calendar getFechaEntrega() {
        return fechaEntrega;
    }

    public Calendar getFechaPropProduccion() {
        return fechaPropProduccion;
    }


    public int getCantidad() {
        return cantidad;
    }

    public Calendar getFechaDefinitiva() {
        return fechaDefinitiva;
    }

    public Calendar getFechaAceptacion() {
        return fechaAceptacion;
    }

    public int getEstado() {
        return estado;
    }

    public TreeSet<Observacion> getListaObservaciones() {
        return listaObservaciones;
    }

    public int getNroLote() {
        return nroLote;
    }
    
    /**
     * Metodo: materialesNecesarios
     * Metodo que devuelve la receta para la maquina que se 
     * pide por pedido multiplicada por la cantidad solicitada
     * por el pedido.
     * @return
     * HashMap: Listado de materiales necesarios
     * para cumplimentar el pedido.
     */
    public HashMap<Integer, Material> materialesNecesarios(){
        HashMap<Integer, Material> necesidad = new HashMap<Integer, Material>();
        Iterator<Material> it = maquina.getListadoMateriales().values().iterator();
        while(it.hasNext()){
            Material itMat = it.next();
            Material auxM = new Material(itMat.getCodigoMaterial(), itMat.getDescripcion(), (Math.rint(itMat.getCantidad() * 1000) * cantidad) / 1000);
            necesidad.put(auxM.getCodigoMaterial(), auxM);
        }
        return necesidad;
    }
    
    /**
     * Metodo: estadoEvaluacion.
     * Cambia el estado del pedido iniciado a en evaluacion.
     * PreCondicion: 
     * El pedido debe encontrarse en estado de inciado.
     * fechaPropuesta no nula.
     * PostCondicion:
     * El pedido cambia su estado a en evaluacion.
     * @param fechaPropuesta
     * Calendar: fecha de fabricación propuesta por el departamento
     * de produccion.
     */
    public void estadoEvaluacion(Calendar fechaPropuesta)
    {
        assert(fechaPropuesta != null) : ("Fecha propuesta nula");
        assert(estado == INICIADO) : ("Pedido no esta en estado de iniciado.");
        estado = EN_EVALUACION;
        resetHoraFecha(fechaPropuesta);
        this.fechaPropProduccion = fechaPropuesta;
        verificarInvariante();
    }
    
    /**
     * Metodo: estadoAceptado
     * Cambia el estado de un pedido de en evaluacion a aceptado.
     * Genera automaticamente el numero de lote.
     * PreCondicion:
     * El pedido debe encontrarse en estado de en evaluacion.
     * fechaDefinitiva no nula.
     * PostCondicion:
     * El pedido cambia su estado a aceptado.
     * @param fechaDefinitiva
     * Calendar: fecha definitiva de produccion.
     */
    public void estadoAceptado(Calendar fechaDefinitiva){
        assert(fechaDefinitiva != null) : ("Fecha definitiva nula.");
        assert(estado == EN_EVALUACION) : ("Pedido no se encuentra en estado de evaluacion.");
        estado = ACEPTADO;
        resetHoraFecha(fechaDefinitiva);
        this.fechaDefinitiva = fechaDefinitiva;
        this.fechaAceptacion = fechaActual();
        nroLote = ++ultLote;
        verificarInvariante();
    }
    
    /**
     * Metodo: estadoCancelado.
     * Cancela un pedido, cambiando su estado.
     * PreCondicion:
     * La observacion no es nula.
     * El pedido se encuentra en estado de iniciado o en evaluacion.
     * PostCondicion:
     * Se agrega una observacion al pedido indicando el motivo de cancelacion.
     * El estado del pedido pasa a ser cancelado.
     * @param observacion
     * Observacion: observacion que indica cual fue el motivo de la cancelacion.
     */
    public void estadoCancelado(Observacion observacion){
        assert (observacion != null) : ("Observacion nula.");
        assert (estado == EN_EVALUACION || estado == INICIADO) :
            ("Pedido no esta en estado de evaluacion / iniciado.");
        listaObservaciones.add(observacion);
        estado = CANCELADO;
        verificarInvariante();
    }
    
    /**
     * Metodo: insertarObservacion
     * Agrega una observacion al pedido.
     * PreCondicion:
     * La observacion no es nula.
     * PostCondicion:
     * El listado de observaciones contiene un elemento mas que antes de ejecutar el metodo.
     * @throws EmpresaException
     * Si el pedido no se encuentra en estado de evaluacion lanza
     * esta excepcion.
     */
    public void insertarObservacion(Observacion obs)
        throws EmpresaException
    {
        assert(obs != null) : ("Observacion nula.");
        if(!(estado == EN_EVALUACION))
            throw new EmpresaException("El pedido no se encuentra en estado de evaluación");
        if(listaObservaciones.contains(obs))
            throw new EmpresaException("La observacion ya existe (?).");
        listaObservaciones.add(obs);
        verificarInvariante();
    }
    
    
    /**
     * Metodo: listadoMateriales
     * Devuelve una cadena con el listado de materiales necesarios para
     * construir la maquina solicitada por el pedido.
     * PreCondicion:
     * 
     * PostCondicion:
     * La cadena puede contener datos o estar vacia, dependiendo de la lista de materiales.
     * @return
     * String: cadena con la lista de materiales.
     */
    public String listadoMateriales(){
        HashMap<Integer, Material> lista = maquina.getListadoMateriales();
        Iterator<Material> it = lista.values().iterator();
        String info = "";
        while(it.hasNext()){
            Material auxM = it.next();
            info += String.format("Cod: MAT%05d\t\t%-100.100s\t%4.3f" + System.lineSeparator(), 
                                  auxM.getCodigoMaterial() , auxM.getDescripcion(),
                                  auxM.getCantidad() * cantidad);
            
        }
        return info;
    }
    
    public static void actualizarVariablesClase(int nPed, int nLot){
        ultPedido = nPed;
        ultLote = nLot;
    }
    
    @Override  
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String strMaquina = String.format("TIP%06d  --  %s", maquina.getCodigo(), maquina.getDescripcion());
        String fechaPed = fechaPedido != null ? sdf.format(fechaPedido.getTime()) : "";
        String fechaEnt = fechaEntrega != null ? sdf.format(fechaEntrega.getTime()): "";
        String fechaPro = fechaPropProduccion != null ? sdf.format(fechaPropProduccion.getTime()): "";
        String fechaAce = fechaAceptacion != null ? sdf.format(fechaAceptacion.getTime()) : "";
        String fechaDef = fechaDefinitiva != null ? sdf.format(fechaDefinitiva.getTime()) : "";
        String strEstado = "";
        switch(estado){
            case ACEPTADO:
                strEstado = "ACEPTADO";
                break;
            case INICIADO:
                strEstado = "INICIADO";
                break;
            case EN_EVALUACION:
                strEstado = "EN EVALUACION";
                break;
            case CANCELADO:
                strEstado = "CANCELADO";
                break;
            default:
                strEstado = "";
                break;
        }
        String str = String.format("Numero de pedido: PED%06d        " +
            "Fecha de pedido: %s" + System.lineSeparator() +
            "Tipo de máquina: %-75.75s             Cantidad: %03d" + System.lineSeparator() +
            "Fecha de entrega solicitada por ventas: %-10.10s" + System.lineSeparator() +
            "Fecha propuesta por Producción: %-10.10s" + System.lineSeparator() +
            "Fecha aceptado: %-10.10s          Fecha definitiva: %-10.10s" + System.lineSeparator() +
            "Estado: %-15.15s" + System.lineSeparator() +
            "Número de lote: LOT%6.6s", nroPedido, fechaPed, strMaquina,
            cantidad, fechaEnt, fechaPro, fechaAce, fechaDef, 
            strEstado, (nroLote == -1 ? "" : String.format("%06d", nroLote)));
        return str; 
    }
    
    private void resetHoraFecha(Calendar fecha){
        fecha.set(Calendar.HOUR_OF_DAY, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }
    
    private Calendar fechaActual(){
        Calendar fechaAct = GregorianCalendar.getInstance();
        resetHoraFecha(fechaAct);
        return fechaAct;
    }
    
    private void verificarInvariante(){
        switch(estado){
            case INICIADO:
                assert(nroPedido > 0 && nroPedido < 1000000) : 
                    ("Nro. pedido fuera de rango.");
                assert(fechaPedido != null) : ("fechaPedido nula");
                assert(maquina != null) : ("Maquina nula");
                assert(cantidad > 0 && cantidad <= 999) :
                    ("Cantidad fuera de rango");
                assert(fechaEntrega != null) : ("fechaEntrega nula");
                assert(fechaPropProduccion == null) :
                    ("fechaPropProduccion deberia ser nula");
                assert(fechaDefinitiva == null) : 
                    ("fechaDefinitiva deberia ser nula");
                assert(fechaAceptacion == null) :
                    ("fechaAceptacion deberia ser nula");
                assert(nroLote == -1) : ("No deberia haber un nro lote asignado.");
                assert(listaObservaciones != null) : ("Lista de observaciones nula.");
                break;
            case EN_EVALUACION:
                assert(nroPedido > 0 && nroPedido < 1000000) : 
                    ("Nro. pedido fuera de rango.");
                assert(fechaPedido != null) : ("fechaPedido nula");
                assert(maquina != null) : ("Maquina nula");
                assert(cantidad > 0 && cantidad <= 999) :
                    ("Cantidad fuera de rango");
                assert(fechaEntrega != null) : ("fechaEntrega nula");
                assert(fechaPropProduccion != null) :
                    ("fechaPropProduccion nula");
                assert(fechaDefinitiva == null) : 
                    ("fechaDefinitiva deberia ser nula");
                assert(fechaAceptacion == null) :
                    ("fechaAceptacion deberia ser nula");
                assert(nroLote == -1) : ("No deberia haber un nro lote asignado.");
                assert(listaObservaciones != null) : ("Lista de observaciones nula.");
                break;
            case ACEPTADO:
                assert(nroPedido > 0 && nroPedido < 1000000) : 
                    ("Nro. pedido fuera de rango.");
                assert(fechaPedido != null) : ("fechaPedido nula");
                assert(maquina != null) : ("Maquina nula");
                assert(cantidad > 0 && cantidad <= 999) :
                    ("Cantidad fuera de rango");
                assert(fechaEntrega != null) : ("fechaEntrega nula");
                assert(fechaPropProduccion != null) :
                    ("fechaPropProduccion nula");
                assert(fechaDefinitiva != null) : 
                    ("fechaDefinitiva nula");
                assert(fechaAceptacion != null) :
                    ("fechaAceptacion nula");
                assert(nroLote > 0) : ("No hay nro de lote asignado.");
                assert(listaObservaciones != null) : ("Lista de observaciones nula.");            
                break;
            case CANCELADO:
                assert(nroPedido > 0 && nroPedido < 1000000) : 
                    ("Nro. pedido fuera de rango.");
                assert(fechaPedido != null) : ("fechaPedido nula");
                assert(maquina != null) : ("Maquina nula");
                assert(cantidad > 0 && cantidad <= 999) :
                    ("Cantidad fuera de rango");
                assert(fechaEntrega != null) : ("fechaEntrega nula");
                assert(fechaDefinitiva == null) : 
                    ("fechaDefinitiva deberia ser nula");
                assert(fechaAceptacion == null) :
                    ("fechaAceptacion deberia ser nula");
                assert(nroLote == -1) : ("No deberia haber un nro lote asignado.");
                assert(listaObservaciones != null) : ("Lista de observaciones nula.");
                assert(listaObservaciones.size() > 0) :
                    ("Lista observaciones deberia contener por lo menos una observacion.");
                break;
            default:
                break;
        }
    }
}
