package TestGUI;

import Controladora.InterfazException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
    private FixtureLogin fixture;
    
    public LoginTest() {
        fixture = new FixtureLogin();
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
    public void testVLOG01A(){
        fixture.setUpM01_A();
        try{
            fixture.getInterfaz().getJTextField1().setText("15");
            int leg = fixture.getInterfaz().getNroLegajo();
            assertTrue("VLOG01A: Legajo incorrecto.",
                       leg == 15);
        } catch(Exception ex){
            fail("VLOG01A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVLOG01B(){
        fixture.setUpM01_A();
        try{
            fixture.getInterfaz().getJTextField1().setText("No quiero testear mas");
            int leg = fixture.getInterfaz().getNroLegajo();
            fail("VLOG01B: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVLOG01C(){
        fixture.setUpM01_A();
        try{
            fixture.getInterfaz().getJTextField1().setText("15A");
            int leg = fixture.getInterfaz().getNroLegajo();
            fail("VLOG01C: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVLOG01D(){
        fixture.setUpM01_A();
        try{
            fixture.getInterfaz().getJTextField1().setText("");
            int leg = fixture.getInterfaz().getNroLegajo();
            fail("VLOG01D: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
}
