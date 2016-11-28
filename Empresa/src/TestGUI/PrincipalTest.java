package TestGUI;

import Controladora.InterfazException;

import empresa.Pedido;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class PrincipalTest {
    private Robot robot;
    private FixturePrincipal fixture = new FixturePrincipal();
    
    
    public PrincipalTest() {
        try{
            robot = new Robot();
        } catch(AWTException e){
            
        }
        
    }
    
    private void resetFechaHora(Calendar fecha){
        fecha.set(Calendar.HOUR, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
        fecha.set(Calendar.AM_PM, 0);
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
    public void testPRI01A(){
        fixture.setUpM01_A();
        assertTrue("PRI01A: Error boton deslogueo." , 
                   fixture.getInterfaz().getDeslog().isEnabled());
        assertTrue("PRI01A: Error boton iniciar pedido.",
                   fixture.getInterfaz().getNuevoPedido().isEnabled());
        assertFalse("PRI01A: Error boton iniciar evaluacion pedido.",
                    fixture.getInterfaz().getAceptPedido().isEnabled());
        assertFalse ("PRI01A: Error boton aceptar pedido.",
                    fixture.getInterfaz().getGenLote().isEnabled());
        assertTrue("PRI01A: Error boton cancelar.",
                   fixture.getInterfaz().getCancelar().isEnabled());
        assertTrue("PRI01A: Error boton observaciones.",
                   fixture.getInterfaz().getObservaciones().isEnabled());
        assertFalse("PRI01A: Error boton materiales necesarios.",
                    fixture.getInterfaz().getMatNecesarios().isEnabled());
        assertFalse("PRI01A: Error boton consulta faltantes.",
                    fixture.getInterfaz().getFaltantes().isEnabled());
        assertFalse("PRI01A: Error boton administrar productos.",
                    fixture.getInterfaz().getAdmProductos().isEnabled());
    }
    
    @Test
    public void testPRI01B(){
        fixture.setUpM01_B();
        assertTrue("PRI01B: Error boton deslogueo." , 
                   fixture.getInterfaz().getDeslog().isEnabled());
        assertFalse("PRI01B: Error boton iniciar pedido.",
                   fixture.getInterfaz().getNuevoPedido().isEnabled());
        assertTrue("PRI01B: Error boton iniciar evaluacion pedido.",
                    fixture.getInterfaz().getAceptPedido().isEnabled());
        assertTrue("PRI01B: Error boton aceptar pedido.",
                    fixture.getInterfaz().getGenLote().isEnabled());
        assertTrue("PRI01B: Error boton cancelar.",
                   fixture.getInterfaz().getCancelar().isEnabled());
        assertTrue("PRI01B: Error boton observaciones.",
                   fixture.getInterfaz().getObservaciones().isEnabled());
        assertTrue("PRI01B: Error boton materiales necesarios.",
                    fixture.getInterfaz().getMatNecesarios().isEnabled());
        assertTrue("PRI01B: Error boton consulta faltantes.",
                    fixture.getInterfaz().getFaltantes().isEnabled());
        assertTrue("PRI01B: Error boton administrar productos.",
                    fixture.getInterfaz().getAdmProductos().isEnabled());
    }
    
    @Test
    public void testPRI01C(){
        fixture.setUpM01_C();
        assertTrue("PRI01C: Error boton deslogueo." , 
                   fixture.getInterfaz().getDeslog().isEnabled());
        assertFalse("PRI01C: Error boton iniciar pedido.",
                   fixture.getInterfaz().getNuevoPedido().isEnabled());
        assertFalse("PRI01C: Error boton iniciar evaluacion pedido.",
                    fixture.getInterfaz().getAceptPedido().isEnabled());
        assertFalse("PRI01C: Error boton aceptar pedido.",
                    fixture.getInterfaz().getGenLote().isEnabled());
        assertFalse("PRI01C: Error boton cancelar.",
                   fixture.getInterfaz().getCancelar().isEnabled());
        assertTrue("PRI01C: Error boton observaciones.",
                   fixture.getInterfaz().getObservaciones().isEnabled());
        assertFalse("PRI01C: Error boton materiales necesarios.",
                    fixture.getInterfaz().getMatNecesarios().isEnabled());
        assertFalse("PRI01C: Error boton consulta faltantes.",
                    fixture.getInterfaz().getFaltantes().isEnabled());
        assertFalse("PRI01C: Error boton administrar productos.",
                    fixture.getInterfaz().getAdmProductos().isEnabled());
    }
    
    @Test
    public void testPRI01D(){
        fixture.setUpM01_D();
        assertTrue("PRI01D: Error boton deslogueo." , 
                   fixture.getInterfaz().getDeslog().isEnabled());
        assertFalse("PRI01D: Error boton iniciar pedido.",
                   fixture.getInterfaz().getNuevoPedido().isEnabled());
        assertFalse("PRI01D: Error boton iniciar evaluacion pedido.",
                    fixture.getInterfaz().getAceptPedido().isEnabled());
        assertFalse("PRI01D: Error boton aceptar pedido.",
                    fixture.getInterfaz().getGenLote().isEnabled());
        assertFalse("PRI01D: Error boton cancelar.",
                   fixture.getInterfaz().getCancelar().isEnabled());
        assertTrue("PRI01D: Error boton observaciones.",
                   fixture.getInterfaz().getObservaciones().isEnabled());
        assertFalse("PRI01D: Error boton materiales necesarios.",
                    fixture.getInterfaz().getMatNecesarios().isEnabled());
        assertFalse("PRI01D: Error boton consulta faltantes.",
                    fixture.getInterfaz().getFaltantes().isEnabled());
        assertFalse("PRI01D: Error boton administrar productos.",
                    fixture.getInterfaz().getAdmProductos().isEnabled());
    }
    
    @Test
    public void testPRI01E(){
        fixture.setUpM01_E();
        assertTrue("PRI01E: Error boton deslogueo." , 
                   fixture.getInterfaz().getDeslog().isEnabled());
        assertFalse("PRI01E: Error boton iniciar pedido.",
                   fixture.getInterfaz().getNuevoPedido().isEnabled());
        assertFalse("PRI01E: Error boton iniciar evaluacion pedido.",
                    fixture.getInterfaz().getAceptPedido().isEnabled());
        assertFalse("PRI01E: Error boton aceptar pedido.",
                    fixture.getInterfaz().getGenLote().isEnabled());
        assertFalse("PRI01E: Error boton cancelar.",
                   fixture.getInterfaz().getCancelar().isEnabled());
        assertTrue("PRI01E: Error boton observaciones.",
                   fixture.getInterfaz().getObservaciones().isEnabled());
        assertFalse("PRI01E: Error boton materiales necesarios.",
                    fixture.getInterfaz().getMatNecesarios().isEnabled());
        assertFalse("PRI01E: Error boton consulta faltantes.",
                    fixture.getInterfaz().getFaltantes().isEnabled());
        assertFalse("PRI01E: Error boton administrar productos.",
                    fixture.getInterfaz().getAdmProductos().isEnabled());
    }
    
    @Test
    public void testPRI01F(){
        fixture.setUpM01_F();
        assertTrue("PRI01F: Error boton deslogueo." , 
                   fixture.getInterfaz().getDeslog().isEnabled());
        assertTrue("PRI01F: Error boton iniciar pedido.",
                   fixture.getInterfaz().getNuevoPedido().isEnabled());
        assertTrue("PRI01F: Error boton iniciar evaluacion pedido.",
                    fixture.getInterfaz().getAceptPedido().isEnabled());
        assertTrue("PRI01F: Error boton aceptar pedido.",
                    fixture.getInterfaz().getGenLote().isEnabled());
        assertTrue("PRI01F: Error boton cancelar.",
                   fixture.getInterfaz().getCancelar().isEnabled());
        assertTrue("PRI01F: Error boton observaciones.",
                   fixture.getInterfaz().getObservaciones().isEnabled());
        assertTrue("PRI01F: Error boton materiales necesarios.",
                    fixture.getInterfaz().getMatNecesarios().isEnabled());
        assertTrue("PRI01F: Error boton consulta faltantes.",
                    fixture.getInterfaz().getFaltantes().isEnabled());
        assertTrue("PRI01F: Error boton administrar productos.",
                    fixture.getInterfaz().getAdmProductos().isEnabled());
    }
    
    @Test 
    public void testPRI02A(){
        fixture.setUpM02_A();
        fixture.getInterfaz().refresh();
        assertTrue("PRI02A: Cantidad de elementos en listado incorrecta.",
                   fixture.getInterfaz().getTablaPedidos().getRowCount() == 0);
    }
    
    @Test
    public void testPRI02B(){
        fixture.setUpM02_A();
        Pedido p1 = new Pedido(fixture.getModelo().getProductos().get(100002), 5, new GregorianCalendar(2017, 12, 31));
        fixture.getModelo().getPedidos().put(p1.getNroPedido(), p1);
        fixture.getInterfaz().refresh(); 
        assertTrue("PRI02B: Cantidad de elementos en listado incorrecta.",
                   fixture.getInterfaz().getTablaPedidos().getRowCount() == 1);
    }
    
    @Test
    public void testPRI02C(){
        fixture.setUpM02_A();
        Pedido p1 = new Pedido(fixture.getModelo().getProductos().get(100002), 5, new GregorianCalendar(2017, 12, 31));
        Pedido p2 = new Pedido(fixture.getModelo().getProductos().get(100003), 15, new GregorianCalendar(2017, 12, 31));
        Pedido p3 = new Pedido(fixture.getModelo().getProductos().get(100001), 25, new GregorianCalendar(2017, 12, 31));
        Pedido p4 = new Pedido(fixture.getModelo().getProductos().get(100001), 5, new GregorianCalendar(2017, 12, 31));
        fixture.getModelo().getPedidos().put(p1.getNroPedido(), p1);
        fixture.getModelo().getPedidos().put(p2.getNroPedido(), p2);
        fixture.getModelo().getPedidos().put(p3.getNroPedido(), p3);
        fixture.getModelo().getPedidos().put(p4.getNroPedido(), p4);
        fixture.getInterfaz().refresh();
        assertTrue("PRI02C: Cantidad de elementos en listado incorrecta.",
                   fixture.getInterfaz().getTablaPedidos().getRowCount() == 4);
    }
    
    @Test
    public void testPRI03A(){
        fixture.setUpM03_A();
        Rectangle rect = fixture.getInterfaz().getTablaPedidos().getCellRect(2, 3, false);
        fixture.getInterfaz().setVisible(true);
        Point p = fixture.getInterfaz().getTablaPedidos().getLocationOnScreen();
        robot.mouseMove(p.x + rect.x, p.y + rect.y + 5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        assertEquals("PRI03A: No se actualizo campo de visualizacion de pedido.",
                   fixture.getInterfaz().getDescPedido().getText(), 
                   fixture.getModelo().getPedidos().get(3).toString());
        try{
            int nPed = fixture.getInterfaz().pedidoSeleccionado().getNroPedido();
            
            assertTrue("PRI03A: No se recupero el pedido correcto",
                       nPed == 3);
        } catch(InterfazException ex){
            fail("PRI03A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testPRI03B(){
        fixture.setUpM03_A();
        Rectangle rect = fixture.getInterfaz().getTablaPedidos().getCellRect(0, 3, false);
        fixture.getInterfaz().setVisible(true);
        Point p = fixture.getInterfaz().getTablaPedidos().getLocationOnScreen();
        robot.mouseMove(p.x + rect.x, p.y + rect.y + 5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        assertEquals("PRI03B: No se actualizo campo de visualizacion de pedido.",
                   fixture.getInterfaz().getDescPedido().getText(), 
                   fixture.getModelo().getPedidos().get(1).toString());
        try{
            int nPed = fixture.getInterfaz().pedidoSeleccionado().getNroPedido();
            
            assertTrue("PRI03B: No se recupero el pedido correcto",
                       nPed == 1);
        } catch(InterfazException ex){
            fail("PRI03B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testPRI03C(){
        fixture.setUpM03_A();
        Rectangle rect = fixture.getInterfaz().getTablaPedidos().getCellRect(4, 3, false);
        fixture.getInterfaz().setVisible(true);
        Point p = fixture.getInterfaz().getTablaPedidos().getLocationOnScreen();
        robot.mouseMove(p.x + rect.x, p.y + rect.y + 5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(20);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(20);
        assertEquals("PRI03C: No se actualizo campo de visualizacion de pedido.",
                   fixture.getInterfaz().getDescPedido().getText(), 
                   fixture.getModelo().getPedidos().get(5).toString());
        try{
            int nPed = fixture.getInterfaz().pedidoSeleccionado().getNroPedido();
            
            assertTrue("PRI03C: No se recupero el pedido correcto",
                       nPed == 5);
        } catch(InterfazException ex){
            fail("PRI03C: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testPRI03D(){
        fixture.setUpM03_A();
        String descAnterior = fixture.getInterfaz().getDescPedido().getText();
        try{
            int nPed = fixture.getInterfaz().pedidoSeleccionado().getNroPedido();
            fail("PRI03D: Se esperaba una excepcion.");
        } catch(InterfazException ex){
            assertEquals("PRI03D: Se modifico el campo descripcion.",
                         descAnterior, fixture.getInterfaz().getDescPedido().getText());
        }
    }
    
    @Test
    public void testPRI04A(){
        fixture.setUpM04_A();
        try{
            Calendar fecha = fixture.getInterfaz().getFecha("2017/12/31");
            Calendar fechaEsperada = new GregorianCalendar(2017, 11, 31);
            assertEquals("PRI04A: Fecha incorrecta",
                         fechaEsperada, fecha);    
        }catch(Exception ex){
            fail("PRI04A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testPRI04B(){
        fixture.setUpM04_A();
        try{
            Calendar fechaActual = GregorianCalendar.getInstance();
            fechaActual.add(Calendar.DAY_OF_YEAR, 1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Calendar fecha = fixture.getInterfaz().getFecha(sdf.format(fechaActual.getTime()));
            Calendar fechaEsperada = GregorianCalendar.getInstance();
            fechaEsperada.add(Calendar.DAY_OF_YEAR, 1);
            resetFechaHora(fechaEsperada);
            assertEquals("PRI04B: Fecha incorrecta",
                         fechaEsperada, fecha);    
        }catch(Exception ex){
            fail("PRI04B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testPRI04C(){
        fixture.setUpM04_A();
        try{
            Calendar fecha = fixture.getInterfaz().getFecha("Esto amerita un 10");
            fail("PRI04C: Se esperaba una excepcion.");    
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testPRI04D(){
        fixture.setUpM04_A();
        try{
            Calendar fecha = fixture.getInterfaz().getFecha("");
            fail("PRI04D: Se esperaba una excepcion.");    
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testPRI04E(){
        fixture.setUpM04_A();
        try{
            Calendar fecha = fixture.getInterfaz().getFecha("2015/14/30");
            fail("PRI04E: Se esperaba una excepcion.");    
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testPRI04F(){
        fixture.setUpM04_A();
        try{
            Calendar fecha = fixture.getInterfaz().getFecha("2017/02/29");
            fail("PRI04F: Se esperaba una excepcion.");    
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testPRI04G(){
        fixture.setUpM04_A();
        try{
            Calendar fecha = fixture.getInterfaz().getFecha(null);
            fail("PRI04G: Se esperaba una excepcion.");    
        }catch(Exception ex){
            assertEquals("PRI04G: La excepcion debia indicar cancelacion operacion",
                         "CANCEL", ex.toString());
        }
    }
    
    @Test
    public void testPRI04H(){
        fixture.setUpM04_A();
        try{
            Calendar fecha = fixture.getInterfaz().getFecha("2015/12/31");
            fail("PRI04H: Se esperaba una excepcion.");    
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testPRI04I(){
        fixture.setUpM04_A();
        try{
            Calendar fechaActual = GregorianCalendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Calendar fecha = fixture.getInterfaz().getFecha(sdf.format(fechaActual.getTime()));
            fail("PRI04I: Se esperaba una excepcion.");    
        }catch(Exception ex){
        }
    }
    
    @Test
    public void testPRI05A(){
        fixture.setUpM05_A();
        try{
            String motivo = fixture.getInterfaz().motivoCancelacion("101010");
            assertEquals("PRI05A: motivo no registrado correctamente.",
                         "[MOTIVO CANCELACION]: 101010", motivo);
        } catch(Exception ex){
            fail("PRI05A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testPRI05B(){
        fixture.setUpM05_A();
        try{
            String motivo = fixture.getInterfaz().motivoCancelacion("1");
            assertEquals("PRI05B: motivo no registrado correctamente.",
                         "[MOTIVO CANCELACION]: 1", motivo);
        } catch(Exception ex){
            fail("PRI05B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testPRI05C(){
        fixture.setUpM05_A();
        try{
            String motivoEsperado = "";
            for(int i = 0; i < 475; i++)
                motivoEsperado += "P";
            String motivo = fixture.getInterfaz().motivoCancelacion(motivoEsperado);
            assertEquals("PRI05C: motivo no registrado correctamente.",
                         "[MOTIVO CANCELACION]: " + motivoEsperado, motivo);
        } catch(Exception ex){
            fail("PRI05C: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testPRI05D(){
        fixture.setUpM05_A();
        try{
            String motivo = fixture.getInterfaz().motivoCancelacion("");
            fail("PRI05D: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testPRI05E(){
        fixture.setUpM05_A();
        try{
            String motivoLargo = "";
            for(int i = 0; i < 500; i++)
                motivoLargo += "P";
            String motivo = fixture.getInterfaz().motivoCancelacion(motivoLargo);
            fail("PRI05E: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testPRI05F(){
        fixture.setUpM05_A();
        try{
            String motivoLargo = "";
            for(int i = 0; i < 476; i++)
                motivoLargo += "P";
            String motivo = fixture.getInterfaz().motivoCancelacion(motivoLargo);
            fail("PRI05F: Se esperaba una excepcion.");
        } catch(Exception ex){
        }
    }
    
    @Test
    public void testPRI05G(){
        fixture.setUpM05_A();
        try{
            String motivo = fixture.getInterfaz().motivoCancelacion(null);
            fail("PRI05E: Se esperaba una excepcion.");
        } catch(Exception ex){
            assertEquals("PRI05G: Se esperaba una indicacion de cancelacion",
                         "CANCEL", ex.toString());
        }
    }
    
}
