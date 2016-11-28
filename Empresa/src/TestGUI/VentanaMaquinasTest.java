package TestGUI;

import empresa.Material;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;

import java.awt.event.InputEvent;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class VentanaMaquinasTest {
    private FixtureVentanaMaquinas fixture;
    private Robot robot;
    
    public VentanaMaquinasTest() {
        fixture = new FixtureVentanaMaquinas();
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
    public void testVMAQ01A(){
        fixture.setUpM01_A();
        fixture.getInterfaz().refresh();
        assertTrue("VMAQ01A: Cantidad de elementos en combo incorrecta.",
                   fixture.getInterfaz().getJComboBox1().getItemCount() == 0);
        assertTrue("VMAQ01A: Cantidad de elementos en tabla receta maquina incorrecta.",
                   fixture.getInterfaz().getJTable1().getRowCount() == 0);
    }
    
    @Test
    public void testVMAQ01B(){
        fixture.setUpM01_B();
        fixture.getInterfaz().refresh();
        assertTrue("VMAA01B: Cantidad de elementos en combo incorrecta.",
                   fixture.getInterfaz().getJComboBox1().getItemCount() == 1);
        assertEquals("VMAQ01B: Elementos en combo incorrectos.",
                    fixture.getInterfaz().getJComboBox1().getItemAt(0), "1");
    }
    
    @Test
    public void testVMAQ01C(){
        fixture.setUpM01_C();
        fixture.getInterfaz().refresh();
        assertTrue("VMAA01C: Cantidad de elementos en combo incorrecta.",
                   fixture.getInterfaz().getJComboBox1().getItemCount() == 4);
        assertEquals("VMAQ01C: Elementos en combo incorrectos.",
                    fixture.getInterfaz().getJComboBox1().getItemAt(0), "1");
        assertEquals("VMAQ01C: Elementos en combo incorrectos.",
                    fixture.getInterfaz().getJComboBox1().getItemAt(1), "2");
        assertEquals("VMAQ01C: Elementos en combo incorrectos.",
                    fixture.getInterfaz().getJComboBox1().getItemAt(2), "3");
        assertEquals("VMAQ01C: Elementos en combo incorrectos.",
                    fixture.getInterfaz().getJComboBox1().getItemAt(3), "4");
    }
    
    @Test
    public void testVMAQ02A(){
        fixture.setUpM02_A();
        Point p = fixture.getInterfaz().getJComboBox1().getLocationOnScreen();
        assertEquals("VMAQ02A: Descripcion previa incorrecta.",
                   "Prueba1", fixture.getInterfaz().getJTextArea1().getText());
        robot.mouseMove(p.x + 5, p.y);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        assertEquals("VMAQ02A: Descripcion final incorrecta.",
                   "Prueba1", fixture.getInterfaz().getJTextArea1().getText());
        assertTrue("VMAQ02A: Cantidad de materiales en listado incorrecta",
                   fixture.getInterfaz().getJTable1().getRowCount() == 1);
        assertEquals("VMAQ02A: Elemento incorrecto en listado",
                     fixture.getInterfaz().getJTable1().getValueAt(0, 0), 
                     new Material(1, "M1", 5.0));
    }
    
    @Test
    public void testVMAQ02B(){
        fixture.setUpM02_A();
        Point p = fixture.getInterfaz().getJComboBox1().getLocationOnScreen();
        assertEquals("VMAQ02B: Descripcion previa incorrecta.",
                   "Prueba1", fixture.getInterfaz().getJTextArea1().getText());
        robot.mouseMove(p.x + 5, p.y);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        robot.mouseMove(p.x + 5, p.y + 75);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        assertEquals("VMAQ02B: Descripcion final incorrecta.",
                   "Prueba3", fixture.getInterfaz().getJTextArea1().getText());
        assertTrue("VMAQ02B: Cantidad de materiales en listado incorrecta",
                   fixture.getInterfaz().getJTable1().getRowCount() == 2);
        assertEquals("VMAQ02B: Elemento incorrecto en listado",
                     fixture.getInterfaz().getJTable1().getValueAt(0, 0), 
                     new Material(1, "M1", 1.0));
        assertEquals("VMAQ02B: Elemento incorrecto en listado",
                     fixture.getInterfaz().getJTable1().getValueAt(1, 0), 
                     new Material(2, "M2", 4.0));
    }
    
    @Test
    public void testVMAQ02C(){
        fixture.setUpM02_A();
        Point p = fixture.getInterfaz().getJComboBox1().getLocationOnScreen();
        assertEquals("VMAQ02C: Descripcion previa incorrecta.",
                   "Prueba1", fixture.getInterfaz().getJTextArea1().getText());
        robot.mouseMove(p.x + 5, p.y);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        robot.mouseMove(p.x + 5, p.y + 110);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        assertEquals("VMAQ02C: Descripcion final incorrecta.",
                   "Prueba5", fixture.getInterfaz().getJTextArea1().getText());
        assertTrue("VMAQ02C: Cantidad de materiales en listado incorrecta",
                   fixture.getInterfaz().getJTable1().getRowCount() == 0);        
    }
    
    @Test
    public void testVMAQ03A(){
        fixture.setUpM03_A();
        Point pTabla = fixture.getInterfaz().getJTable1().getLocationOnScreen();
        Rectangle rFila = fixture.getInterfaz().getJTable1().getCellRect(2, 2, false);
        robot.mouseMove(pTabla.x + rFila.x, pTabla.y + rFila.y);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        try{
            Material matSel = fixture.getInterfaz().getMatProdSeleccionado();
            assertEquals("VMAQ03A: Material seleccionado incorrecto",
                         matSel, new Material(3, "M3", 20.0));
        } catch(Exception ex){
            fail("VMAQ03A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ03B(){
        fixture.setUpM03_A();
        Point pTabla = fixture.getInterfaz().getJTable1().getLocationOnScreen();
        Rectangle rFila = fixture.getInterfaz().getJTable1().getCellRect(0, 2, false);
        robot.mouseMove(pTabla.x + rFila.x, pTabla.y + rFila.y);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        try{
            Material matSel = fixture.getInterfaz().getMatProdSeleccionado();
            assertEquals("VMAQ03B: Material seleccionado incorrecto",
                         matSel, new Material(1, "M1", 10.0));
        } catch(Exception ex){
            fail("VMAQ03B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ03C(){
        fixture.setUpM03_A();
        Point pTabla = fixture.getInterfaz().getJTable1().getLocationOnScreen();
        Rectangle rFila = fixture.getInterfaz().getJTable1().getCellRect(4, 2, false);
        robot.mouseMove(pTabla.x + rFila.x, pTabla.y + rFila.y);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        try{
            Material matSel = fixture.getInterfaz().getMatProdSeleccionado();
            assertEquals("VMAQ03C: Material seleccionado incorrecto",
                         matSel, new Material(5, "M5", 50.0));
        } catch(Exception ex){
            fail("VMAQ03C: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ03D(){
        fixture.setUpM03_A();
        try{
            Material matSel = fixture.getInterfaz().getMatProdSeleccionado();
            fail("VMAQ03D: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVMAQ04A(){
        fixture.setUpM04_A();
        Point pTabla = fixture.getInterfaz().getJTable2().getLocationOnScreen();
        Rectangle rFila = fixture.getInterfaz().getJTable2().getCellRect(2, 2, false);
        robot.mouseMove(pTabla.x + rFila.x, pTabla.y + rFila.y);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        try{
            Material matSel = fixture.getInterfaz().getMatStockSeleccionado();
            assertEquals("VMAQ04A: Material seleccionado incorrecto",
                         matSel, new Material(3, "M3", 20.0));
        } catch(Exception ex){
            fail("VMAQ04A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ04B(){
        fixture.setUpM04_A();
        Point pTabla = fixture.getInterfaz().getJTable2().getLocationOnScreen();
        Rectangle rFila = fixture.getInterfaz().getJTable2().getCellRect(0, 2, false);
        robot.mouseMove(pTabla.x + rFila.x, pTabla.y + rFila.y);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        try{
            Material matSel = fixture.getInterfaz().getMatStockSeleccionado();
            assertEquals("VMAQ04B: Material seleccionado incorrecto",
                         matSel, new Material(1, "M1", 10.0));
        } catch(Exception ex){
            fail("VMAQ04B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ04C(){
        fixture.setUpM04_A();
        Point pTabla = fixture.getInterfaz().getJTable2().getLocationOnScreen();
        Rectangle rFila = fixture.getInterfaz().getJTable2().getCellRect(4, 2, false);
        robot.mouseMove(pTabla.x + rFila.x, pTabla.y + rFila.y);
        robot.delay(20);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        try{
            Material matSel = fixture.getInterfaz().getMatStockSeleccionado();
            assertEquals("VMAQ04C: Material seleccionado incorrecto",
                         matSel, new Material(5, "M5", 50.0));
        } catch(Exception ex){
            fail("VMAQ04C: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ04D(){
        fixture.setUpM04_A();
        try{
            Material matSel = fixture.getInterfaz().getMatStockSeleccionado();
            fail("VMAQ04D: Se esperaba una excepcion");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVMAQ05A(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("300.0");
            assertTrue("VMAQ05A: Cantidad incorrecta.",
                       cant == 300.0);
        } catch(Exception ex){
            fail("VMAQ05A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ05B(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("0.001");
            assertTrue("VMAQ05B: Cantidad incorrecta.",
                       cant == 0.001);
        } catch(Exception ex){
            fail("VMAQ05B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ05C(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("300");
            assertTrue("VMAQ05C: Cantidad incorrecta.",
                       cant == 300.0);
        } catch(Exception ex){
            fail("VMAQ05C: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ05D(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("1");
            assertTrue("VMAQ05D: Cantidad incorrecta.",
                       cant == 1.0);
        } catch(Exception ex){
            fail("VMAQ05D: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ05E(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("TarararaHey");
            fail("VMAQ05E: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVMAQ05F(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("12A");
            fail("VMAQ05F: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVMAQ05G(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("");
            fail("VMAQ05G: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVMAQ05H(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad(null);
            fail("VMAQ05H: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVMAQ05I(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("-40.0");
            fail("VMAQ05I: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVMAQ05J(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("0.0");
            fail("VMAQ05E: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVMAQ05K(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("-40");
            fail("VMAQ05K: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVMAQ05L(){
        fixture.setUpM05_A();
        try{
            double cant = fixture.getInterfaz().getCantidad("0");
            fail("VMAQ05L: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testVMAQ06A(){
        fixture.setUpM06_A();
        try{
            fixture.getInterfaz().getJComboBox1().setSelectedIndex(1);
            int cod = fixture.getInterfaz().getCodigoMaquina();
            assertTrue("VMAQ06A: Codigo incorrecto",
                       cod == 2);
        } catch(Exception ex){
            fail("VMAQ06A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testVMAQ06B(){
        fixture.setUpM06_B();
        try{
            int cod = fixture.getInterfaz().getCodigoMaquina();
            fail("VMAQ06B: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
}
