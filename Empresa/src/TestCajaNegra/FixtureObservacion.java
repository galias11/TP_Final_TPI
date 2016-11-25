package TestCajaNegra;

import empresa.Observacion;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FixtureObservacion {
    private Observacion observacionTest = new Observacion();
    
    
    public FixtureObservacion() {
        super();
    }
    
    private void resetHoraFecha(Calendar fecha){
        fecha.set(Calendar.HOUR, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }


    public Observacion getObservacionTest() {
        return observacionTest;
    }

    public void setUp(){
        observacionTest= new Observacion(Observacion.TEMA_INSUMOS, 1, "Falta material 1");
    }
    
    public void tearDown(){
        observacionTest = new Observacion();
    }
    
    public void setUpM02A(){
        Calendar fechaAux = new GregorianCalendar(2016, 10, 12);
        resetHoraFecha(fechaAux);
        observacionTest.setFecha(fechaAux);  
    }
    
    public void setUpM02B(){
        Calendar fechaAux = new GregorianCalendar(2016, 12, 12);
        resetHoraFecha(fechaAux);
        observacionTest.setFecha(fechaAux);  
    }
    
    public void setUpM02C(){
        Calendar fechaAux = new GregorianCalendar(2016, 10, 13);
        resetHoraFecha(fechaAux);
        observacionTest.setFecha(fechaAux);  
    }
    
    public void setUpM02D(){
        setUpM02A();
        observacionTest.setTema(Observacion.TEMA_OTROS);
    }
    
    public void setUpM02E(){
        setUpM02B();
        observacionTest.setTema(Observacion.TEMA_OTROS);
    }
    
}
