package empresa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

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
    
    public Pedido(Maquina maquina, int cantidad, Calendar fechaEntrega) {
        this.nroPedido = ++ultPedido;
        this.fechaPedido = GregorianCalendar.getInstance();
        this.maquina = maquina;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
        this.fechaPropProduccion = null;
        this.fechaDefinitiva = null;
        this.fechaAceptacion = null;
        this.estado = this.INICIADO;
        this.listaObservaciones = new TreeSet<Observacion>();
        this.nroLote = -1;
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
            Material auxM = new Material(itMat.getCodigoMaterial(), itMat.getDescripcion(), itMat.getCantidad() * cantidad);
            necesidad.put(auxM.getCodigoMaterial(), auxM);
        }
        return necesidad;
    }
    /**
     * Metodo: estadoEvaluacion.
     * Cambia el estado del pedido iniciado a en evaluacion.
     * PreCondicion: 
     * El pedido debe encontrarse en estado de inciado.
     * PostCondicion:
     * El pedido cambia su estado a en evaluacion.
     * @param fechaPropuesta
     * Calendar: fecha de fabricación propuesta por el departamento
     * de produccion.
     */
    public void estadoEvaluacion(Calendar fechaPropuesta)
    {
        assert(estado == INICIADO) : ("Pedido no esta en estado de iniciado.");
        estado = EN_EVALUACION;
        this.fechaPropProduccion = fechaPropuesta;
        this.fechaAceptacion = GregorianCalendar.getInstance();
    }
    
    /**
     * Metodo: estadoAceptado
     * Cambia el estado de un pedido de en evaluacion a aceptado.
     * Genera automaticamente el numero de lote.
     * PreCondicion:
     * El pedido debe encontrarse en estado de en evaluacion.
     * PostCondicion:
     * El pedido cambia su estado a aceptado.
     * @param fechaDefinitiva
     * Calendar: fecha definitiva de produccion.
     */
    public void estadoAceptado(Calendar fechaDefinitiva){
        assert(estado == EN_EVALUACION) : ("Pedido no se encuentra en estado de evaluacion.");
        estado = ACEPTADO;
        this.fechaDefinitiva = fechaDefinitiva;
        nroLote = ++ultLote;
    }
    
    /**
     * Metodo: estadoCancelado.
     * Cancela un pedido, cambiando su estado.
     * PreCondicion:
     * El pedido se encuentra en estado de iniciado o en evaluacion.
     * PostCondicion:
     * El estado del pedido pasa a ser cancelado.
     */
    public void estadoCancelado(){
        assert (estado == EN_EVALUACION || estado == INICIADO) :
            ("Pedido no esta en estado de evaluacion / iniciado.");
        estado = CANCELADO;
    }
    
    /**
     * Metodo: insertarObservacion
     * Agrega una observacion al pedido.
     * PreCondicion:
     * La observacion no es nula.
     * La longitud de la observacion debe ser menor o igual a 500 caracteres.
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
        assert(obs.getObservacion().length() <= 500) : ("Excede limite de caracteres");
        if(!(estado == EN_EVALUACION))
            throw new EmpresaException("El pedido no se encuentra en estado de evaluación");
        if(listaObservaciones.contains(obs))
            throw new EmpresaException("La observacion ya existe (?).");
        listaObservaciones.add(obs);
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
}
