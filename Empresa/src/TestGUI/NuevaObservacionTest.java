package TestGUI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NuevaObservacionTest {
    private FixtureNuevaObservacion fixture;
    
    public NuevaObservacionTest() {
        fixture = new FixtureNuevaObservacion();
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
    public void testNOBS01A(){
        fixture.setUpM01_A();
        fixture.getInterfaz().getJComboBox1().setSelectedIndex(0);
        assertEquals("NOBS01A: Combo box no contiene elementos correctos.",
                     fixture.getInterfaz().getJComboBox1().getSelectedItem(), "FECHAS");
        fixture.getInterfaz().getJComboBox1().setSelectedIndex(1);
        assertEquals("NOBS01A: Combo box no contiene elementos correctos.",
                     fixture.getInterfaz().getJComboBox1().getSelectedItem(), "INSUMOS");
        fixture.getInterfaz().getJComboBox1().setSelectedIndex(2);
        assertEquals("NOBS01A: Combo box no contiene elementos correctos.",
                     fixture.getInterfaz().getJComboBox1().getSelectedItem(), "OTROS");
    }
    
    @Test
    public void testNOBS02A(){
        fixture.setUpM02_A();
        fixture.getInterfaz().getObservacion1().setText("Prueba");
        try{
            String obs = fixture.getInterfaz().getObservacion();
            assertEquals("NOBS02A: Observacion incorrecta.",
                         obs, "Prueba");
        } catch(Exception ex){
            fail("NOBS02A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testNOBS02B(){
        fixture.setUpM02_A();
        fixture.getInterfaz().getObservacion1().setText("P");
        try{
            String obs = fixture.getInterfaz().getObservacion();
            assertEquals("NOBS02B: Observacion incorrecta.",
                         obs, "P");
        } catch(Exception ex){
            fail("NOBS02B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testNOBS02C(){
        fixture.setUpM02_A();
        String inputStr = "";
        for(int i = 0; i < 500; i++)
            inputStr += "P";
        fixture.getInterfaz().getObservacion1().setText(inputStr);
        try{
            String obs = fixture.getInterfaz().getObservacion();
            assertEquals("NOBS02C: Observacion incorrecta.",
                         obs, inputStr);
        } catch(Exception ex){
            fail("NOBS02C: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testNOBS02D(){
        fixture.setUpM02_A();
        fixture.getInterfaz().getObservacion1().setText("");
        try{
            String obs = fixture.getInterfaz().getObservacion();
            fail("NOBS02D: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testNOBS02E(){
        fixture.setUpM02_A();
        String inputStr = "";
        for(int i = 0; i < 750; i++)
            inputStr += "P";
        fixture.getInterfaz().getObservacion1().setText(inputStr);
        try{
            String obs = fixture.getInterfaz().getObservacion();
            fail("NOBS02E: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testNOBS02F(){
        fixture.setUpM02_A();
        String inputStr = "";
        for(int i = 0; i < 501; i++)
            inputStr += "P";
        fixture.getInterfaz().getObservacion1().setText(inputStr);
        try{
            String obs = fixture.getInterfaz().getObservacion();
            fail("NOBS02E: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
}
