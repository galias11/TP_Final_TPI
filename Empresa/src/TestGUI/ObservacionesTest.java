package TestGUI;

import empresa.Observacion;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;

import java.awt.event.InputEvent;

import java.awt.event.KeyEvent;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObservacionesTest {
    private FixtureObservaciones fixture;
    private Robot robot;
    
    public ObservacionesTest() {
        fixture = new FixtureObservaciones();
        try{
            robot = new Robot();
        } catch(AWTException ex){
            
        }
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
    public void testVOBS01A(){
        fixture.setUpM01_A();
        fixture.getInterfaz().refresh();
        assertTrue("VOBS01A: Tabla observaciones con cantidad de elementos incorrecta",
                   fixture.getInterfaz().getTablaObservaciones().getRowCount() == 0);
    }
            
    @Test 
    public void testVOBS01B(){
        fixture.setUpM01_B();
        fixture.getInterfaz().refresh();
        assertTrue("VOBS01B: Tabla observaciones con cantidad de elementos incorrecta",
                   fixture.getInterfaz().getTablaObservaciones().getRowCount() == 1);
    }
    
    @Test 
    public void testVOBS01C(){
        fixture.setUpM01_C();
        fixture.getInterfaz().refresh();
        assertTrue("VOBS01C: Tabla observaciones con cantidad de elementos incorrecta",
                   fixture.getInterfaz().getTablaObservaciones().getRowCount() == 4);
        Observacion obs1 = new Observacion("OTROS", 1, "P");
        Observacion obs2 = new Observacion("INSUMOS", 2, "P");
        Observacion obs3 = new Observacion("OTROS", 2, "P");
        Observacion obs4 = new Observacion("FECHAS", 10, "P");
        obs1.setFecha(new GregorianCalendar(2017, 11, 31));
        obs2.setFecha(new GregorianCalendar(2016, 11, 31));
        obs3.setFecha(new GregorianCalendar(2016, 11, 31));
        obs4.setFecha(new GregorianCalendar(2019, 11, 31));
        assertTrue("VOBS01C: Orden elementos incorrecto.",
                   obs4.compareTo(fixture.getInterfaz().getTablaObservaciones().getValueAt(0, 0)) == 0);
        assertTrue("VOBS01C: Orden elementos incorrecto.",
                   obs2.compareTo(fixture.getInterfaz().getTablaObservaciones().getValueAt(1, 0)) == 0);
        assertTrue("VOBS01C: Orden elementos incorrecto.",
                   obs3.compareTo(fixture.getInterfaz().getTablaObservaciones().getValueAt(2, 0)) == 0);
        assertTrue("VOBS01C: Orden elementos incorrecto.",
                   obs1.compareTo(fixture.getInterfaz().getTablaObservaciones().getValueAt(3, 0)) == 0);
        
    }
    
    @Test
    public void testVOBS02A(){
        fixture.setUpM02_A();
        Observacion obs3 = new Observacion("INSUMOS", 5, "PI2");
        obs3.setFecha(new GregorianCalendar(2017, 06, 31));
        Point pTabla = fixture.getInterfaz().getTablaObservaciones().getLocationOnScreen();
        Rectangle rTabla = fixture.getInterfaz().getTablaObservaciones().getCellRect(2, 2, false); 
        robot.mouseMove(pTabla.x + rTabla.x, pTabla.y + rTabla.y + 5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(20);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(20);
        try{
            Observacion obsSeleccionada = fixture.getInterfaz().getObsSeleccionada();
            assertTrue("VOBS02A: Observacion obtenida incorrecta.",
                       obsSeleccionada.compareTo(obs3) == 0);
        } catch(Exception ex){
            fail("VOBS02A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVOBS02B(){
        fixture.setUpM02_A();
        Observacion obs3 = new Observacion("FECHAS", 3, "PF1");
        obs3.setFecha(new GregorianCalendar(2017, 05, 30));
        Point pTabla = fixture.getInterfaz().getTablaObservaciones().getLocationOnScreen();
        Rectangle rTabla = fixture.getInterfaz().getTablaObservaciones().getCellRect(0, 2, false); 
        robot.mouseMove(pTabla.x + rTabla.x, pTabla.y + rTabla.y + 5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(20);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(20);
        try{
            Observacion obsSeleccionada = fixture.getInterfaz().getObsSeleccionada();
            assertTrue("VOBS02B: Observacion obtenida incorrecta.",
                       obsSeleccionada.compareTo(obs3) == 0);
        } catch(Exception ex){
            fail("VOBS02B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVOBS02C(){
        fixture.setUpM02_A();
        Observacion obs3 = new Observacion("OTROS", 12, "PO1");
        obs3.setFecha(new GregorianCalendar(2017, 05, 30));
        Point pTabla = fixture.getInterfaz().getTablaObservaciones().getLocationOnScreen();
        Rectangle rTabla = fixture.getInterfaz().getTablaObservaciones().getCellRect(4, 2, false); 
        robot.mouseMove(pTabla.x + rTabla.x, pTabla.y + rTabla.y + 5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(20);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(20);
        try{
            Observacion obsSeleccionada = fixture.getInterfaz().getObsSeleccionada();
            assertTrue("VOBS02C: Observacion obtenida incorrecta.",
                       obsSeleccionada.compareTo(obs3) == 0);
        } catch(Exception ex){
            fail("VOBS02C: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVOBS02D(){
        fixture.setUpM02_A();
        try{
            Observacion obsSeleccionada = fixture.getInterfaz().getObsSeleccionada();
            fail("VOBS02D: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    
}
