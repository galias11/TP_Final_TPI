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
     * Metodo: insertarObservacion
     * Agrega una observacion al pedido.
     * PreCondicion:
     * La observacion no es nula.
     * PostCondicion:
     * El listado de observaciones contiene un elemento mas que antes de ejecutar el metodo.
     */
    public void insertarObservacion(Observacion obs)
        throws EmpresaException
    {
        assert(obs != null) : ("Observacion nula.");
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
            info += String.format("Cod: MAT%5.5d\t%-100.100s\t%3.4f" + System.lineSeparator(), 
                                  auxM.getCodigoMaterial() , auxM.getDescripcion(),
                                  auxM.getCantidad() * cantidad);
            
        }
        return info;
    }
}
