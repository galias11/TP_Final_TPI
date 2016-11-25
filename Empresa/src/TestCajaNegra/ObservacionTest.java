package TestCajaNegra;


import empresa.Observacion;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObservacionTest {
    private FixtureObservacion fixture= new FixtureObservacion();
    
    
    public ObservacionTest() {
        super();
    }
    
    private void resetHoraFecha(Calendar fecha){
        fecha.set(Calendar.HOUR, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }
    
    @Before
    public void setUp(){
        fixture.setUp();
    }
    
    @After
    public void tearDown(){
        fixture.tearDown();
    }
    
    
    @Test
    public void testOBS01A(){
        Observacion aux= new Observacion(Observacion.TEMA_INSUMOS, 1, "Falta material 1");
        assertTrue("No se registro tema correctamente", aux.getTema()==Observacion.TEMA_INSUMOS);
        assertTrue("No se registro legajo correctamente", aux.getNLegCreador()==1);
        assertTrue("No se registro tema correctamente", aux.getObservacion()=="Falta material 1");
    }
    
    @Test
    public void testOBS01B(){
        boolean assertError=false;
        try{
        Observacion aux= new Observacion(Observacion.TEMA_INSUMOS, -1, "Falta material 1");
        }                                                                                
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testOBS01C(){
        boolean assertError=false;
        try{
        Observacion aux= new Observacion(Observacion.TEMA_INSUMOS, 0, "Falta material 1");
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testOBS01D(){
        boolean assertError=false;
        try{
        Observacion aux= new Observacion(Observacion.TEMA_INSUMOS, 1, null);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testOBS01E(){
        boolean assertError=false;
        try{
        Observacion aux= new Observacion(Observacion.TEMA_INSUMOS, 1,"");
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testOBSO1F(){
        boolean assertError=false;
        try{
        Observacion aux= new Observacion(null, 1,"Falta material 1");
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testOBS01G(){
        boolean assertError=false;
        try{
        Observacion aux= new Observacion("", 1,"Falta material 1");
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testOBS01H(){
        boolean assertError=false;
        try{
        Observacion aux= new Observacion("COMPRAS", 1,"Falta material 1");
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testOBS02A(){
        fixture.setUpM02A();   
        Calendar fechaAux = new GregorianCalendar(2016, 12, 12);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_OTROS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02A: Comparacion incorrecta.", cmp < 0);
    }
    
    @Test 
    public void testOBS02B(){
        fixture.setUpM02B();   
        Calendar fechaAux = new GregorianCalendar(2016, 10, 12);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_OTROS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02B: Comparacion incorrecta.", cmp < 0);
    }
    
    @Test
    public void testOBS02C(){
        fixture.setUpM02A();   
        Calendar fechaAux = new GregorianCalendar(2016, 10, 12);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_OTROS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02C: Comparacion incorrecta.", cmp < 0);
    }
    
    @Test
    public void testOBS02D(){
        fixture.setUpM02D();
        Calendar fechaAux = new GregorianCalendar(2016, 12, 12);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_INSUMOS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02D: Comparacion incorrecta.", cmp > 0);
    }
    
    @Test
    public void testOBS02E(){
        fixture.setUpM02E();
        Calendar fechaAux = new GregorianCalendar(2016, 10, 12);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_INSUMOS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02E: Comparacion incorrecta.", cmp > 0);
    }
    
    @Test
    public void testOBS02F(){
        fixture.setUpM02D();
        Calendar fechaAux = new GregorianCalendar(2016, 10, 12);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_INSUMOS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02F: Comparacion incorrecta.", cmp > 0);
    }
    
    @Test
    public void testOBS02G(){
        fixture.setUpM02A();
        Calendar fechaAux = new GregorianCalendar(2016, 12, 12);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_INSUMOS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02G: Comparacion incorrecta.", cmp < 0);
    }
    
    @Test
    public void testOBS02H(){
        fixture.setUpM02A();
        Calendar fechaAux = new GregorianCalendar(2016, 10, 13);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_INSUMOS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02H: Comparacion incorrecta.", cmp < 0);
    }
    
    @Test
    public void testOBS02I(){
        fixture.setUpM02B();
        Calendar fechaAux = new GregorianCalendar(2016, 12, 12);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_INSUMOS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02I: Comparacion incorrecta.", cmp == 0);
    }
    
    @Test
    public void testOBS02J(){
        fixture.setUpM02B();
        Calendar fechaAux = new GregorianCalendar(2016, 10, 12);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_INSUMOS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02J: Comparacion incorrecta.", cmp > 0);
    }
    
    @Test
    public void testOBS02K(){
        fixture.setUpM02B();
        Calendar fechaAux = new GregorianCalendar(2016, 12, 11);
        resetHoraFecha(fechaAux);
        Observacion o = new Observacion(Observacion.TEMA_INSUMOS, 1, "Prueba");
        o.setFecha(fechaAux);
        int cmp = fixture.getObservacionTest().compareTo(o);
        assertTrue("OBS02K: Comparacion incorrecta.", cmp > 0);
    }
    
    @Test
    public void testOBS02L(){
        fixture.setUpM02A();
        int cmp = fixture.getObservacionTest().compareTo(null);
        assertTrue("OBS02L: Comparacion incorrecta.", cmp == 0);
    }
    
    @Test
    public void testOBS02M(){
        fixture.setUpM02A();
        int cmp = fixture.getObservacionTest().compareTo("Ponenos 10");
        assertTrue("OBS02M: Comparacion incorrecta.", cmp == 0);
    }
    
}
