package empresa;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Observacion implements Comparable{
    private String tema;
    private Calendar fecha;
    private Empleado creador;
    private String observacion;
    
    public static final String FECHAS = "FECHAS";
    public static final String INSUMOS = "INSUMOS";
    public static final String OTROS = "OTROS";
    
    public Observacion(String tema, Empleado creador, String observacion)
    {
        this.tema = tema;
        this.fecha = GregorianCalendar.getInstance();
        this.creador = creador;
        this.observacion = observacion;
    }


    public String getTema() {
        return tema;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public Empleado getCreador() {
        return creador;
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
    
}
