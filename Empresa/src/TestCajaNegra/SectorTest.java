package TestCajaNegra;

import empresa.EmpresaException;
import empresa.Operacion;
import empresa.Sector;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SectorTest {
    public SectorTest() {
        super();
    }
    
    private FixtureSector fixture = new FixtureSector();
    
    
    
    @Before
    public void setUp(){
        fixture.setUp();
    }
    
    @After
    public void tearDown(){
        fixture.tearDown();
    }
    
    @Test 
    public void testSec01A()  {
        Operacion op = new Operacion(1, "Iniciar pedido.");
            try {
                fixture.getSectorTest().agregarPermiso(op);
            } catch (EmpresaException e) {
                fail("Tendria que agregar la operacion correctamente");
            }
            assertTrue("No se agrego la nueva operacion",
                   fixture.getSectorTest().getPermisos().containsKey(1));
    }
    
    
    @Test 
    public void testSec02A() {
        boolean assertError=false;
        Operacion op=null;
        try {
            fixture.getSectorTest().agregarPermiso(op);
        }
        catch(AssertionError e){
            assertError=true;
        } catch (EmpresaException e) {
            assertError=true;
        }
        if (!assertError)
                fail("Se esperaba un error de asercion");
    }

    
    @Test 
    public void testSec03A() {
        boolean assertError=false;
        fixture.setUpM03A();
        Operacion op = new Operacion(1, "Iniciar pedido.");
        try {
            fixture.getSectorTest().agregarPermiso(op);
        }
        catch(AssertionError e){
             assertError=true;
        } catch (EmpresaException e) {
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
        
           
    }
    
    @Test
    public void testSec04A(){
        Sector aux = new Sector("Produccion");
        assertTrue(" No se registro nombre correctamente",
                   aux.getNombre() == "Produccion");
    }
    
   
    
    @Test
    public void testSec04B(){
        boolean assertError=false;
        try{
        Sector aux = new Sector("");
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
   
    
    
}

