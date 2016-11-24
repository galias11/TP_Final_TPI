package TestCajaNegra;


import empresa.Observacion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObservacionTest {
    private FixtureObservacion fixture= new FixtureObservacion();
    
    
    public ObservacionTest() {
        super();
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
    public void testOBS01F(){
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
        Observacion o= new Observacion(Observacion.TEMA_INSUMOS, 1,"Falta material 1");
        int iguales= fixture.getObservacionTest().compareTo(o);
        assertTrue("Ambas observaciones deberian ser iguales", iguales==0);
    }
    
    @Test
    public void testOBS02B(){
        boolean assertError=false;
        try{
        int iguales= fixture.getObservacionTest().compareTo(null);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    
    
    
}
