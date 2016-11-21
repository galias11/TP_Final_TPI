package TestCajaNegra;

import empresa.Operacion;

import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

public class OperacionTest {
    private FixtureOperacion fixture = new FixtureOperacion();
    
    public OperacionTest() {
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
    public void testOPE01A(){
        fixture.setUpM01A();
        Operacion op = new Operacion(4, "Prueba");
        assertTrue("OPE01A: Codigo no se registro correctamente",
                   op.getCodigo() == 4);
        assertEquals("OPE01A: Descripcion no se registro correctamente",
                     "Prueba", op.getDescripcion());
    }
    
    @Test
    public void testOPE01B(){
        fixture.setUpM01A();
        Operacion op = new Operacion(1, "Prueba");
        assertTrue("OPE01B: Codigo no se registro correctamente",
                   op.getCodigo() == 1);
        assertEquals("OPE01B: Descripcion no se registro correctamente",
                     "Prueba", op.getDescripcion());
    }
    
    @Test
    public void testOPE01C(){
        fixture.setUpM01A();
        Operacion op = new Operacion(4, "P");
        assertTrue("OPE01C: Codigo no se registro correctamente",
                   op.getCodigo() == 4);
        assertEquals("OPE01C: Descripcion no se registro correctamente",
                     "P", op.getDescripcion());
    }
    
    @Test
    public void testOPE01D(){
        fixture.setUpM01A();
        try{
            Operacion op = new Operacion(-4, "Prueba");
            fail("OPE01D: Se esperaba error de assercion");
        } catch(AssertionError e){
            
        }
    }
    
    @Test
    public void testOPE01E(){
        fixture.setUpM01A();
        try{
            Operacion op = new Operacion(0, "Prueba");
            fail("OPE01E: Se esperaba error de assercion");
        } catch(AssertionError e){
            
        }
    }
    
    @Test
    public void testOPE01F(){
        fixture.setUpM01A();
        try{
            Operacion op = new Operacion(4, "");
            fail("OPE01F: Se esperaba error de assercion");
        } catch(AssertionError e){
            
        }
    }
    
    @Test
    public void testOPE01G(){
        fixture.setUpM01A();
        try{
            Operacion op = new Operacion(4, null);
            fail("OPE01D: Se esperaba error de assercion");
        } catch(AssertionError e){
            
        }
    }
}
