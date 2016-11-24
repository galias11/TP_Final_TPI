package empresa;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase observacion.
 * Representa a las observaciones que se realizan de los 
 * diferentes pedidos.
 * inv:
 * tema no nulo y uno de TEMA_FECHAS, TEMA_INSUMOS, TEMA_OTROS
 * observacion no nula, no vacia y longitud menor a 500 caracteres.
 * el numero de legajo debe ser mayor que 0.
 */
public class Observacion implements Comparable{
    private String tema;
    private Calendar fecha;
    private int nLegCreador;
    private String observacion;
    
    public static final String TEMA_FECHAS = "FECHAS";
    public static final String TEMA_INSUMOS = "INSUMOS";
    public static final String TEMA_OTROS = "OTROS";
    
    /**
     * Constructor vacio para serializacion.
     * No utilizar.
     */
    public Observacion(){
        
    }
    
    /**
     * Constructor con parametros.
     * Precondicion:
     * el parametro observación debe ser una cadena no nula ni vacia.
     * el parametro observacion puede tener como maximo 500 caracteres.
     * el parametro tema debe ser no nulo y uno de: Fechas, insumos, otros.
     * el parametro nLeg debe ser un entero mayor que 0.
     * @param tema
     * String: tema de la observacion
     * @param nLegCreador
     * int: nro. de legajo del emisor de la obs.
     * @param observacion
     * String: texto descriptivo de la observacion
     */
    public Observacion(String tema, int nLegCreador, String observacion)
    {
        assert(tema != null) : ("Tema nulo.");
        assert(tema.equals(TEMA_FECHAS) || tema.equals(TEMA_INSUMOS)
               || tema.equals(TEMA_OTROS)) : ("Tema no valido.");
        assert(observacion != null) : ("Observacion nula");
        assert(!observacion.isEmpty()) : ("Observacion vacia.");
        assert(observacion.length() <= 500) : ("Observacion muy larga");
        assert(nLegCreador > 0) : ("Legajo no valido.");
        this.tema = tema;
        this.fecha = GregorianCalendar.getInstance();
        this.nLegCreador = nLegCreador;
        this.observacion = observacion;
        verificarInvariante();
    }
    
    /*
     * ***********************************************
     * Getters y setters agregados para serializacion.
     * ***********************************************
     */


    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public void setNLegCreador(int nLegCreador) {
        this.nLegCreador = nLegCreador;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /*
     * ***********************************************
     */


    public String getTema() {
        return tema;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public int getNLegCreador() {
        return nLegCreador;
    }

    public String getObservacion() {
        return observacion;
    }
    
    @Override 
    public int compareTo(Object o){
        int cmp = 0;
        if(o != this && o instanceof Observacion){
            cmp = tema.compareTo(((Observacion)o).tema);
            if(cmp == 0)
                cmp = fecha.compareTo(((Observacion)o).fecha);
        }
        return cmp;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return String.format("TEMA: %-10.10s    FECHA: %-10.10s    EMITIO: LEG%06d" +
                             System.lineSeparator() + "%s",
                             tema, sdf.format(fecha.getTime()), nLegCreador,
                             observacion);
    }
    
    private void verificarInvariante(){
        assert(tema != null) : ("Tema nulo.");
        assert(tema.equals(TEMA_FECHAS) || tema.equals(TEMA_OTROS)
               || tema.equals(TEMA_INSUMOS)) : ("Tema no valido.");
        assert(observacion != null) : ("Observacion nula");
        assert(!observacion.isEmpty()) : ("Observacion vacia");
        assert(observacion.length() <= 500) : ("Observacion fuera de limite");
        assert(nLegCreador > 0) : ("Legajo no valido");
    }
}
