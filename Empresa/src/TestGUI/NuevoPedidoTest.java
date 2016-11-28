package TestGUI;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NuevoPedidoTest {
    private FixtureNuevoPedido fixture = new FixtureNuevoPedido();
    
    public NuevoPedidoTest() {
    }
    
    @Before
    public void setUp(){
        fixture.setUp();
    }
    
    private void resetHoraFecha(Calendar fecha){
        fecha.set(Calendar.HOUR, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
        fecha.set(Calendar.AM_PM, 0);
    }
    
    @After
    public void tearDown(){
        fixture.tearDown();
    }
    
    @Test
    public void testNPED01A(){
        fixture.setUpM01_A();
        fixture.getInterfaz().getComboMaq().setSelectedIndex(0);
        try{
            int codMaq = fixture.getInterfaz().getCodigoMaquina();
            assertTrue("NPED01A: Codigo de maquina incorrecto.",
                       codMaq == 100);
        } catch(Exception ex){
            fail("NPED01A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testNPED01B(){
        fixture.setUpM01_A();
        fixture.getInterfaz().getComboMaq().removeAllItems();
        fixture.getInterfaz().getComboMaq().addItem("Sabes que tenes que poner 10");
        fixture.getInterfaz().getComboMaq().setSelectedIndex(0);
        try{
            int codMaq = fixture.getInterfaz().getCodigoMaquina();
            fail("NPED01B: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testNPED01C(){
        fixture.setUpM01_A();
        fixture.getInterfaz().getComboMaq().removeAllItems();
        fixture.getInterfaz().getComboMaq().addItem("2.1");
        fixture.getInterfaz().getComboMaq().setSelectedIndex(0);
        try{
            int codMaq = fixture.getInterfaz().getCodigoMaquina();
            fail("NPED01C: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testNPED01D(){
        fixture.setUpM01_A();
        fixture.getInterfaz().getComboMaq().removeAllItems();
        fixture.getInterfaz().getComboMaq().addItem("");
        fixture.getInterfaz().getComboMaq().setSelectedIndex(0);
        try{
            int codMaq = fixture.getInterfaz().getCodigoMaquina();
            fail("NPED01D: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test 
    public void testNPED02A(){
        fixture.setUpM02_A();
        try{
            fixture.getInterfaz().getTextCantMaq().setText("15");
            int cantidad = fixture.getInterfaz().getCantidad();
            assertTrue("NPED02A: Cantidad incorrecta",
                       cantidad == 15);
        } catch(Exception ex){
            fail("NPED02A: Exception inesperada: " + ex.toString());
        }
    }
    
    @Test 
    public void testNPED02B(){
        fixture.setUpM02_A();
        try{
            fixture.getInterfaz().getTextCantMaq().setText("1");
            int cantidad = fixture.getInterfaz().getCantidad();
            assertTrue("NPED02B: Cantidad incorrecta",
                       cantidad == 1);
        } catch(Exception ex){
            fail("NPED02B: Exception inesperada: " + ex.toString());
        }
    }
    
    @Test 
    public void testNPED02C(){
        fixture.setUpM02_A();
        try{
            fixture.getInterfaz().getTextCantMaq().setText("999");
            int cantidad = fixture.getInterfaz().getCantidad();
            assertTrue("NPED02C: Cantidad incorrecta",
                       cantidad == 999);
        } catch(Exception ex){
            fail("NPED02C: Exception inesperada: " + ex.toString());
        }
    }
    
    @Test 
    public void testNPED02D(){
        fixture.setUpM02_A();
        try{
            fixture.getInterfaz().getTextCantMaq().setText("10 or die");
            int cantidad = fixture.getInterfaz().getCantidad();
            fail("NPED02D: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test 
    public void testNPED02E(){
        fixture.setUpM02_A();
        try{
            fixture.getInterfaz().getTextCantMaq().setText("2.1");
            int cantidad = fixture.getInterfaz().getCantidad();
            fail("NPED02E: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test 
    public void testNPED02F(){
        fixture.setUpM02_A();
        try{
            fixture.getInterfaz().getTextCantMaq().setText("");
            int cantidad = fixture.getInterfaz().getCantidad();
            fail("NPED02F: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test 
    public void testNPED02G(){
        fixture.setUpM02_A();
        try{
            fixture.getInterfaz().getTextCantMaq().setText("-55");
            int cantidad = fixture.getInterfaz().getCantidad();
            fail("NPED02G: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test 
    public void testNPED02H(){
        fixture.setUpM02_A();
        try{
            fixture.getInterfaz().getTextCantMaq().setText("0");
            int cantidad = fixture.getInterfaz().getCantidad();
            fail("NPED02H: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test 
    public void testNPED02I(){
        fixture.setUpM02_A();
        try{
            fixture.getInterfaz().getTextCantMaq().setText("1055");
            int cantidad = fixture.getInterfaz().getCantidad();
            fail("NPED02I: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test 
    public void testNPED02J(){
        fixture.setUpM02_A();
        try{
            fixture.getInterfaz().getTextCantMaq().setText("1000");
            int cantidad = fixture.getInterfaz().getCantidad();
            fail("NPED02J: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testNPED03A(){
        fixture.setUpM03_A();
        try{
            fixture.getInterfaz().getFechaSol().setText("2017/12/31");
            Calendar fecha = fixture.getInterfaz().getFecha();
            assertEquals("NPED03A: Fecha incorrecta",
                         fecha, new GregorianCalendar(2017, 11, 31));    
        }catch(Exception ex){
            fail("NPED03A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testNPED03B(){
        fixture.setUpM03_A();
        try{
            Calendar fechaActual = GregorianCalendar.getInstance();
            fechaActual.add(Calendar.DAY_OF_YEAR, 1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            fixture.getInterfaz().getFechaSol().setText(sdf.format(fechaActual.getTime()));
            Calendar fecha = fixture.getInterfaz().getFecha();
            Calendar fechaEsperada = GregorianCalendar.getInstance();
            fechaEsperada.add(Calendar.DAY_OF_YEAR, 1);
            resetHoraFecha(fechaEsperada);
            assertEquals("NPED03B: Fecha incorrecta",
                         fecha, fechaEsperada);    
        }catch(Exception ex){
            fail("NPED03B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testNPED03C(){
        fixture.setUpM03_A();
        try{
            fixture.getInterfaz().getFechaSol().setText("lalala");
            Calendar fecha = fixture.getInterfaz().getFecha();
            fail("NPED03C: Se esperaba una excepcion");
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testNPED03D(){
        fixture.setUpM03_A();
        try{
            fixture.getInterfaz().getFechaSol().setText("");
            Calendar fecha = fixture.getInterfaz().getFecha();
            fail("NPED03D: Se esperaba una excepcion");
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testNPED03E(){
        fixture.setUpM03_A();
        try{
            fixture.getInterfaz().getFechaSol().setText("2017/14/30");
            Calendar fecha = fixture.getInterfaz().getFecha();
            fail("NPED03E: Se esperaba una excepcion");
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testNPED03F(){
        fixture.setUpM03_A();
        try{
            fixture.getInterfaz().getFechaSol().setText("2017/02/29");
            Calendar fecha = fixture.getInterfaz().getFecha();
            fail("NPED03F: Se esperaba una excepcion");
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testNPED03G(){
        fixture.setUpM03_A();
        try{
            fixture.getInterfaz().getFechaSol().setText("2015/12/31");
            Calendar fecha = fixture.getInterfaz().getFecha();
            fail("NPED03G: Se esperaba una excepcion");
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testNPED03H(){
        fixture.setUpM03_A();
        try{            
            Calendar fechaActual = GregorianCalendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            fixture.getInterfaz().getFechaSol().setText(sdf.format(fechaActual.getTime()));
            Calendar fecha = fixture.getInterfaz().getFecha();
            fail("NPED03H: Se esperaba una excepcion");
        }catch(Exception ex){
        }
    }
    
    
}
