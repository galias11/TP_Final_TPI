package empresa;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Observacion implements Comparable{
    private String tema;
    private Calendar fecha;
    private int nLegCreador;
    private String observacion;
    
    public static final String FECHAS = "FECHAS";
    public static final String INSUMOS = "INSUMOS";
    public static final String OTROS = "OTROS";
    
    public Observacion(String tema, int nLegCreador, String observacion)
    {
        this.tema = tema;
        this.fecha = GregorianCalendar.getInstance();
        this.nLegCreador = nLegCreador;
        this.observacion = observacion;
    }


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
        if(!(o == this || !(o instanceof Observacion))){
            cmp = tema.compareTo(((Observacion)o).tema);
            if(cmp == 0)
                cmp = fecha.compareTo(((Observacion)o).fecha);
        }
        return cmp;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat format = new SimpleDateFormat("AAAA/MM/DD");
        return String.format("TEMA: %-10.10s\tFECHA: %-10.10s\tEMITIO: LEG%6.6d\n%s",
                             tema, format.format(fecha.getTime()), nLegCreador,
                             observacion);
    }
    
}
