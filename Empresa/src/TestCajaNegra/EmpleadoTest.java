package TestCajaNegra;

import empresa.Empleado;

import empresa.Sector;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmpleadoTest {
    private FixtureEmpleado fixture= new FixtureEmpleado();
    
    
    public EmpleadoTest() {
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
    public void testEmp01A(){
        Sector ventas= new Sector("Ventas");
        Empleado aux= new Empleado(1, "Empleado ventas", ventas);
        assertTrue("El legajo no se almaceno correctamente", aux.getLegajo()==1);
        assertTrue("El apellido y nombre no se almacenaron correctamente", aux.getAyn()=="Empleado ventas");
        assertTrue("El sector no se almaceno correctamente", aux.getSector().equals(ventas));
    }
    
    @Test
    public void testEmp02A(){
        boolean assertError=false;
        Sector ventas= new Sector("Ventas");
        try{
        Empleado aux= new Empleado(-10, "Empleado ventas", ventas);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testEmp02B(){
        boolean assertError=false;
        Sector ventas= new Sector("Ventas");
        try{
            Empleado aux= new Empleado(0, "Empleado ventas", ventas);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testEmp03A(){
        boolean assertError=false;
        Sector ventas= new Sector("Ventas");
        try{
        Empleado aux= new Empleado( 3000000, "Empleado ventas", ventas);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testEmp03B(){
        boolean assertError=false;
        Sector ventas= new Sector("Ventas");
        try{
        Empleado aux= new Empleado( 1000000, "Empleado ventas", ventas);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
        
    }
    
    @Test
    public void testEmp04A(){
        boolean assertError=false;
        Sector ventas= new Sector("Ventas");
        try{
        Empleado aux= new Empleado(1, null, ventas);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testEmp05A(){
        boolean assertError=false;
        Sector ventas= new Sector("Ventas");
        try{
        Empleado aux= new Empleado(1, "", ventas);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testEmp06A(){
        boolean assertError=false;
        try{
        Empleado aux= new Empleado(1, "Empleado ventas", null);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    @Test
    public void testEmp07A(){
        boolean assertError=false;
        Sector ventas= new Sector("Ventas");
        try{
        Empleado aux= new Empleado(1, "Empleado ventas111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",ventas);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
    
    @Test
    public void testEmp07B(){
        boolean assertError=false;
        Sector ventas= new Sector("Ventas");
        try{
        Empleado aux= new Empleado(1, "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",ventas);
        }
        catch(AssertionError e){
            assertError=true;
        }
        if (!assertError)
            fail("Se esperaba un error de asercion");
    }
    
   
    
    
     
}
