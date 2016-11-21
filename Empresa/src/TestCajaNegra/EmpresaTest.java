package TestCajaNegra;

import empresa.EmpresaException;

import empresa.Maquina;
import empresa.Material;
import empresa.Pedido;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.GregorianCalendar;

import java.util.HashMap;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

public class EmpresaTest {
    private FixtureEmpresa fixture = new FixtureEmpresa();
    
    public EmpresaTest() {
    }
    
    private void resetHoraFecha(Calendar fecha){
        fecha.set(Calendar.HOUR_OF_DAY, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }
    
    @Before
    public void setUp(){
        fixture.setUp();
    }
    
    @After
    public void tearDown(){
        fixture.tearDown();
    }
    
    /*
     * A continuacion se codifican los test de unidad para la clase Empresa.
     * Para ver correlación metodo, clases testeadas, fixture ver archivo de tabla
     * fixtures.
     */
    @Test
    public void testEMP01A(){
        fixture.setUpM01_A();
        try{
            Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
            int ultNroLote = p.getUltLote();
            fixture.getEmpresaTest().aceptarPedido(1,  new GregorianCalendar(2017, 12, 31));
            assertTrue("EMP01A: Fallo cambio de estado", p.getEstado() == Pedido.ACEPTADO);
            Calendar fechaAceptacion = p.getFechaAceptacion();
            resetHoraFecha(fechaAceptacion);
            Calendar fechaActual = GregorianCalendar.getInstance();
            resetHoraFecha(fechaActual);
            assertTrue("EMP01A: Fallo en fecha aceptacion", fechaAceptacion.equals(fechaActual));
            assertTrue("EMP01A: Fallo en fecha definitiva", p.getFechaDefinitiva().equals(new GregorianCalendar(2017, 12, 31)));
            assertTrue("EMP01A: Fallo en asignación nro lote", p.getNroLote() == ultNroLote + 1);
            assertTrue("EMP01A: fallo en actualizacion de inventarios",
                fixture.getEmpresaTest().getInventario().get(401).getCantidad() == 200.0
                && fixture.getEmpresaTest().getInventario().get(402).getCantidad() == 150.0
                && fixture.getEmpresaTest().getInventario().get(403).getCantidad() == 325.0);
        } catch(EmpresaException ex){
            fail("EMP01A: Excepcion no esperada");
        }
    }
    
    @Test
    public void testEMP01B(){
        fixture.setUpM01_A();
        try{
            Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
            int ultNroLote = p.getUltLote();
            Calendar fechaAux = GregorianCalendar.getInstance();
            fechaAux.add(Calendar.DAY_OF_YEAR, 1);
            fixture.getEmpresaTest().aceptarPedido(1,  fechaAux);
            assertTrue("EMP01B: Fallo cambio de estado", p.getEstado() == Pedido.ACEPTADO);
            Calendar fechaAceptacion = p.getFechaAceptacion();
            resetHoraFecha(fechaAceptacion);
            Calendar fechaActual = GregorianCalendar.getInstance();
            resetHoraFecha(fechaActual);
            assertTrue("EMP01B: Fallo en fecha aceptacion", fechaAceptacion.equals(fechaActual));
            assertTrue("EMP01A: Fallo en fecha definitiva", p.getFechaDefinitiva().equals(fechaAux));
            assertTrue("EMP01B: Fallo en asignación nro lote", p.getNroLote() == ultNroLote + 1);
            assertTrue("EMP01B: fallo en actualizacion de inventarios", fixture.getEmpresaTest().getInventario().get(401).getCantidad() == 200.0
                && fixture.getEmpresaTest().getInventario().get(402).getCantidad() == 150.0
                && fixture.getEmpresaTest().getInventario().get(403).getCantidad() == 325.0);
        } catch(EmpresaException ex){
            fail("EMP01B: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP01C(){
        fixture.setUpM01_A();
        double cantMat1 = fixture.getEmpresaTest().getInventario().get(401).getCantidad();
        double cantMat2 = fixture.getEmpresaTest().getInventario().get(402).getCantidad();
        double cantMat3 = fixture.getEmpresaTest().getInventario().get(403).getCantidad();
        try{
            fixture.getEmpresaTest().aceptarPedido(11, new GregorianCalendar(2017, 12, 31));
            fail("EMP01C: Se esperaba excepcion por no encontrar pedido");
        } catch(EmpresaException ex){
            assertTrue("EMP01C: Se modifico el inventario.", 
                fixture.getEmpresaTest().getInventario().get(401).getCantidad() == cantMat1
                && fixture.getEmpresaTest().getInventario().get(402).getCantidad() == cantMat2
                && fixture.getEmpresaTest().getInventario().get(403).getCantidad() == cantMat3);
        }
    }
    
    @Test
    public void testEMP01D(){
        fixture.setUpM01_A();
        boolean assertError = false;
        double cantMat1 = fixture.getEmpresaTest().getInventario().get(401).getCantidad();
        double cantMat2 = fixture.getEmpresaTest().getInventario().get(402).getCantidad();
        double cantMat3 = fixture.getEmpresaTest().getInventario().get(403).getCantidad();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        try{
            fixture.getEmpresaTest().aceptarPedido(1, new GregorianCalendar(2015, 12, 31));
        } catch(EmpresaException ex){
            fail("EMP01D: Excepcion no esperada: " + ex.toString());
        } catch(AssertionError err){
            assertError = true;
            assertTrue("EMP01D: Se modifico el inventario.",
                    fixture.getEmpresaTest().getInventario().get(401).getCantidad() == cantMat1
                    && fixture.getEmpresaTest().getInventario().get(402).getCantidad() == cantMat2
                    && fixture.getEmpresaTest().getInventario().get(403).getCantidad() == cantMat3);
            assertTrue("EMP01D: Se modifico estado pedido", p.getEstado() == Pedido.EN_EVALUACION);
            assertNull("EMP01D: fechaAceptacion no nula", p.getFechaAceptacion());
            assertNull("EMP01D: fechaDefinitiva no nula", p.getFechaDefinitiva());
            assertTrue("EMP01D: Nro de lote modificado", p.getNroLote() == -1);
        }
        if(!assertError)
            fail("EMP01D: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP01E(){
        fixture.setUpM01_A();
        boolean assertError = false;
        double cantMat1 = fixture.getEmpresaTest().getInventario().get(401).getCantidad();
        double cantMat2 = fixture.getEmpresaTest().getInventario().get(402).getCantidad();
        double cantMat3 = fixture.getEmpresaTest().getInventario().get(403).getCantidad();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        try{
            fixture.getEmpresaTest().aceptarPedido(1, GregorianCalendar.getInstance());
        } catch(EmpresaException ex){
            fail("EMP01E: Excepcion no esperada: " + ex.toString());
        } catch(AssertionError err){
            assertError = true;
            assertTrue("EMP01E: Se modifico el inventario.",
                    fixture.getEmpresaTest().getInventario().get(401).getCantidad() == cantMat1
                    && fixture.getEmpresaTest().getInventario().get(402).getCantidad() == cantMat2
                    && fixture.getEmpresaTest().getInventario().get(403).getCantidad() == cantMat3);
            assertTrue("EMP01E: Se modifico estado pedido", p.getEstado() == Pedido.EN_EVALUACION);
            assertNull("EMP01E: fechaAceptacion no nula", p.getFechaAceptacion());
            assertNull("EMP01E: fechaDefinitiva no nula", p.getFechaDefinitiva());
            assertTrue("EMP01E: Nro de lote modificado", p.getNroLote() == -1);    
        }
        if(!assertError)
            fail("EMP01E: Debio lanzarce un error de asercion");
    }
    
    @Test
    public void testEMP01F(){
        fixture.setUpM01_A();
        boolean assertError = false;
        double cantMat1 = fixture.getEmpresaTest().getInventario().get(401).getCantidad();
        double cantMat2 = fixture.getEmpresaTest().getInventario().get(402).getCantidad();
        double cantMat3 = fixture.getEmpresaTest().getInventario().get(403).getCantidad();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        try{
            fixture.getEmpresaTest().aceptarPedido(1, null);
        } catch(EmpresaException ex){
            fail("EMP01F: Excepcion no esperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP01F: Se modifico el inventario.",
                    fixture.getEmpresaTest().getInventario().get(401).getCantidad() == cantMat1
                    && fixture.getEmpresaTest().getInventario().get(402).getCantidad() == cantMat2
                    && fixture.getEmpresaTest().getInventario().get(403).getCantidad() == cantMat3);
            assertTrue("EMP01F: Se modifico estado pedido", p.getEstado() == Pedido.EN_EVALUACION);
            assertNull("EMP01F: fechaAceptacion no nula", p.getFechaAceptacion());
            assertNull("EMP01F: fechaDefinitiva no nula", p.getFechaDefinitiva());
            assertTrue("EMP01F: Nro de lote modificado", p.getNroLote() == -1);    
        }
        if(!assertError)
            fail("EMP01F: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP01G(){
        fixture.setUpM01_B();
        boolean assertError = false;
        double cantMat1 = fixture.getEmpresaTest().getInventario().get(401).getCantidad();
        double cantMat2 = fixture.getEmpresaTest().getInventario().get(402).getCantidad();
        double cantMat3 = fixture.getEmpresaTest().getInventario().get(403).getCantidad();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        try{
            fixture.getEmpresaTest().aceptarPedido(1, new GregorianCalendar(2017, 12, 31));
        } catch(EmpresaException ex){
            fail("EMP01G: Excepcion no esperada: " + ex.toString());
        }  catch(AssertionError e){
            assertError = true;
            assertTrue("EMP01G: Se modifico el inventario.",
                    fixture.getEmpresaTest().getInventario().get(401).getCantidad() == cantMat1
                    && fixture.getEmpresaTest().getInventario().get(402).getCantidad() == cantMat2
                    && fixture.getEmpresaTest().getInventario().get(403).getCantidad() == cantMat3);
            assertTrue("EMP01G: Se modifico estado pedido", p.getEstado() == Pedido.EN_EVALUACION);
            assertNull("EMP01G: fechaAceptacion no nula", p.getFechaAceptacion());
            assertNull("EMP01G: fechaDefinitiva no nula", p.getFechaDefinitiva());
            assertTrue("EMP01G: Nro de lote modificado", p.getNroLote() == -1);    
        }
        if(!assertError)
            fail("EMP01G: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP01H(){
        fixture.setUpM01_C();
        double cantMat1 = fixture.getEmpresaTest().getInventario().get(401).getCantidad();
        double cantMat2 = fixture.getEmpresaTest().getInventario().get(402).getCantidad();
        double cantMat3 = fixture.getEmpresaTest().getInventario().get(403).getCantidad();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        try{
            fixture.getEmpresaTest().aceptarPedido(1, new GregorianCalendar(2017, 12, 31));
            fail("EMP01H: debio haberse lanzado una excepcion.");
        } catch(EmpresaException ex){
            assertTrue("EMP01H: Se modifico el inventario.",
                    fixture.getEmpresaTest().getInventario().get(401).getCantidad() == cantMat1
                    && fixture.getEmpresaTest().getInventario().get(402).getCantidad() == cantMat2
                    && fixture.getEmpresaTest().getInventario().get(403).getCantidad() == cantMat3);
            assertTrue("EMP01H: Se modifico estado pedido", p.getEstado() == Pedido.INICIADO);
            assertNull("EMP01H: fechaPropProduccion no nula", p.getFechaPropProduccion());
            assertNull("EMP01H: fechaAceptacion no nula", p.getFechaAceptacion());
            assertNull("EMP01H: fechaDefinitiva no nula", p.getFechaDefinitiva());
            assertTrue("EMP01H: Nro de lote modificado", p.getNroLote() == -1);   
        }
    }
    
    @Test
    public void testEMP01I(){
        fixture.setUpM01_D();
        double cantMat1 = fixture.getEmpresaTest().getInventario().get(401).getCantidad();
        double cantMat2 = fixture.getEmpresaTest().getInventario().get(402).getCantidad();
        double cantMat3 = fixture.getEmpresaTest().getInventario().get(403).getCantidad();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        Calendar fechaDefinitiva = p.getFechaDefinitiva();
        Calendar fechaAceptacion = p.getFechaAceptacion();
        double nroLote = p.getNroLote();
        try{
            fixture.getEmpresaTest().aceptarPedido(1, new GregorianCalendar(2017, 12, 31));
            fail("EMP01I: Debio haberse lanzado una excepcion.");
        } catch(EmpresaException ex){
            assertTrue("EMP01I: Se modifico el inventario.",
                    fixture.getEmpresaTest().getInventario().get(401).getCantidad() == cantMat1
                    && fixture.getEmpresaTest().getInventario().get(402).getCantidad() == cantMat2
                    && fixture.getEmpresaTest().getInventario().get(403).getCantidad() == cantMat3);
            assertTrue("EMP01I: Se modifico estado pedido", p.getEstado() == Pedido.ACEPTADO);
            assertTrue("EMP01I: fechaAceptacion cambio", p.getFechaAceptacion().equals(fechaAceptacion));
            assertTrue("EMP01I: fechaDefinitiva cambio", p.getFechaDefinitiva().equals(fechaDefinitiva));
            assertTrue("EMP01I: Nro de lote modificado", p.getNroLote() == nroLote);   
        }
    }
    
    @Test
    public void testEMP01J(){
        fixture.setUpM01_E();
        double cantMat1 = fixture.getEmpresaTest().getInventario().get(401).getCantidad();
        double cantMat2 = fixture.getEmpresaTest().getInventario().get(402).getCantidad();
        double cantMat3 = fixture.getEmpresaTest().getInventario().get(403).getCantidad();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        try{
            fixture.getEmpresaTest().aceptarPedido(1, new GregorianCalendar(2017, 12, 31));
            fail("EMP01J: Debio haberse lanzado una excepcion");
        } catch(EmpresaException ex){
            assertTrue("EMP01I: Se modifico el inventario.",
                    fixture.getEmpresaTest().getInventario().get(401).getCantidad() == cantMat1
                    && fixture.getEmpresaTest().getInventario().get(402).getCantidad() == cantMat2
                    && fixture.getEmpresaTest().getInventario().get(403).getCantidad() == cantMat3);
            assertTrue("EMP01I: Se modifico estado pedido", p.getEstado() == Pedido.EN_EVALUACION);
            assertNull("EMP01I: fechaAceptacion no nula", p.getFechaAceptacion());
            assertNull("EMP01I: fechaDefinitiva no nula", p.getFechaDefinitiva());
            assertTrue("EMP01I: Nro de lote modificado", p.getNroLote() == -1);
            
        }
    }
    
    @Test
    public void testEMP02A(){
        fixture.setUpM02_A();
        try{
            fixture.getEmpresaTest().agregarMaterialReceta(100002, 404, 60.0);
            Maquina maq = fixture.getEmpresaTest().getProductos().get(100002);
            assertTrue("EMP02A: Tamaño lista mat errorneo",
                       maq.getListadoMateriales().size() == 3);
            assertTrue("EMP02A: Receta no contiene materiales correctos",
                       maq.getListadoMateriales().containsKey(401) &&
                       maq.getListadoMateriales().containsKey(403) &&
                       maq.getListadoMateriales().containsKey(404));
            assertTrue("EMP02A: Receta no contiene las cantidades correctos",
                       maq.getListadoMateriales().get(401).getCantidad() == 35.0 &&
                       maq.getListadoMateriales().get(403).getCantidad() == 25.0 &&
                       maq.getListadoMateriales().get(404).getCantidad() == 60.0);                 
        } catch(EmpresaException ex){
            fail("EMP02A: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP02B(){
        fixture.setUpM02_A();
        try{
            fixture.getEmpresaTest().agregarMaterialReceta(100002, 404, 0.001);
            Maquina maq = fixture.getEmpresaTest().getProductos().get(100002);
            assertTrue("EMP02B: Tamaño lista mat errorneo",
                       maq.getListadoMateriales().size() == 3);
            assertTrue("EMP02B: Receta no contiene materiales correctos",
                       maq.getListadoMateriales().containsKey(401) &&
                       maq.getListadoMateriales().containsKey(403) &&
                       maq.getListadoMateriales().containsKey(404));
            assertTrue("EMP02B: Receta no contiene las cantidades correctos",
                       maq.getListadoMateriales().get(401).getCantidad() == 35.0 &&
                       maq.getListadoMateriales().get(403).getCantidad() == 25.0 &&
                       maq.getListadoMateriales().get(404).getCantidad() == 0.001);                 
        } catch(EmpresaException ex){
            fail("EMP02B: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP02H(){
        fixture.setUpM02_B();
        try{
            fixture.getEmpresaTest().agregarMaterialReceta(100002, 404, 60.0);
            Maquina maq = fixture.getEmpresaTest().getProductos().get(100002);
            assertTrue("EMP02H: Tamaño lista mat errorneo",
                       maq.getListadoMateriales().size() == 1);
            assertTrue("EMP02H: Receta no contiene materiales correctos",
                       maq.getListadoMateriales().containsKey(404));
            assertTrue("EMP02H: Receta no contiene las cantidades correctos",
                       maq.getListadoMateriales().get(404).getCantidad() == 60.0);                 
        } catch(EmpresaException ex){
            fail("EMP02H: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP02C(){
        fixture.setUpM02_A();
        try{
            fixture.getEmpresaTest().agregarMaterialReceta(100012, 404, 60.0);
            fail("EMP02C: Debio lanzarce una excepcion");
        } catch(EmpresaException ex){
            
        }
    }
    
    @Test
    public void testEMP02D(){
        fixture.setUpM02_A();
        try{
            fixture.getEmpresaTest().agregarMaterialReceta(100002, 403, 60.0);
            fail("EMP02D: Debio lanzarce una excepcion");
        } catch(EmpresaException ex){
            Maquina maq = fixture.getEmpresaTest().getProductos().get(100002);
            assertTrue("EMP02D: Cambio contenido receta",
                       maq.getListadoMateriales().size() == 2);
            assertTrue("EMP02D: Cambio contenido receta",
                       maq.getListadoMateriales().containsKey(401) &&
                       maq.getListadoMateriales().containsKey(403));
            assertTrue("EMP02D: Cambio contenido receta", 
                       maq.getListadoMateriales().get(401).getCantidad() == 35.0 &&
                       maq.getListadoMateriales().get(403).getCantidad() == 25.0);
        }
    }
    
    @Test
    public void testEMP02E(){
        fixture.setUpM02_A();
        try{
            fixture.getEmpresaTest().agregarMaterialReceta(100002, 8, 60.0);
            fail("EMP02E: Debio lanzarce excepcion");
        } catch(EmpresaException ex){
            Maquina maq = fixture.getEmpresaTest().getProductos().get(100002);
            assertTrue("EMP02E: Cambio contenido receta",
                       maq.getListadoMateriales().size() == 2);
            assertTrue("EMP02E: Cambio contenido receta",
                       maq.getListadoMateriales().containsKey(401) &&
                       maq.getListadoMateriales().containsKey(403));
            assertTrue("EMP02E: Cambio contenido receta", 
                       maq.getListadoMateriales().get(401).getCantidad() == 35.0 &&
                       maq.getListadoMateriales().get(403).getCantidad() == 25.0);            
        }
    }
    
    @Test
    public void testEMP02F(){
        fixture.setUpM02_A();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().agregarMaterialReceta(100002, 404, -60.0);
        } catch(EmpresaException ex){
            fail("EMP02F: Excepcion no esperada: " + ex.toString());         
        } catch(AssertionError e){
            assertError = true;
            Maquina maq = fixture.getEmpresaTest().getProductos().get(100002);
            assertTrue("EMP02F: Cambio contenido receta",
                       maq.getListadoMateriales().size() == 2);
            assertTrue("EMP02F: Cambio contenido receta",
                       maq.getListadoMateriales().containsKey(401) &&
                       maq.getListadoMateriales().containsKey(403));
            assertTrue("EMP02F: Cambio contenido receta", 
                       maq.getListadoMateriales().get(401).getCantidad() == 35.0 &&
                       maq.getListadoMateriales().get(403).getCantidad() == 25.0);           
        }
        if(!assertError)
            fail("EMP02F: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP02G(){
        fixture.setUpM02_A();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().agregarMaterialReceta(100002, 404, 0.0);
        } catch(EmpresaException ex){
            fail("EMP02G: Excepcion no esperada: " + ex.toString());         
        } catch(AssertionError e){
            assertError = true;
            Maquina maq = fixture.getEmpresaTest().getProductos().get(100002);
            assertTrue("EMP02G: Cambio contenido receta",
                       maq.getListadoMateriales().size() == 2);
            assertTrue("EMP02G: Cambio contenido receta",
                       maq.getListadoMateriales().containsKey(401) &&
                       maq.getListadoMateriales().containsKey(403));
            assertTrue("EMP02G: Cambio contenido receta", 
                       maq.getListadoMateriales().get(401).getCantidad() == 35.0 &&
                       maq.getListadoMateriales().get(403).getCantidad() == 25.0);           
        }
        if(!assertError)
            fail("EMP02G: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP02I(){
        fixture.setUpM02_C();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().agregarMaterialReceta(100002, 404, 60.0);
        } catch(EmpresaException ex){
            fail("EMP02I: Excepcion no esperada: " + ex.toString());         
        } catch(AssertionError e){
            assertError = true;
            Maquina maq = fixture.getEmpresaTest().getProductos().get(100002);
            assertTrue("EMP02I: Cambio contenido receta",
                       maq.getListadoMateriales().size() == 2);
            assertTrue("EMP02I: Cambio contenido receta",
                       maq.getListadoMateriales().containsKey(401) &&
                       maq.getListadoMateriales().containsKey(403));
            assertTrue("EMP02I: Cambio contenido receta", 
                       maq.getListadoMateriales().get(401).getCantidad() == 35.0 &&
                       maq.getListadoMateriales().get(403).getCantidad() == 25.0);           
        }
        if(!assertError)
            fail("EMP02I: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP03A(){
        fixture.setUpM03_A();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        Calendar fechaAux = GregorianCalendar.getInstance();
        fechaAux.add(Calendar.DAY_OF_YEAR, 1);
        try{
            fixture.getEmpresaTest().cancelarPedido(1, "Prueba");
            assertTrue("EMP03A: Estado pedido no cambio",
                       p.getEstado() == Pedido.CANCELADO);
            assertTrue("EMP03A: Observacion cancelacion no registrada",
                       p.getListaObservaciones().size() == cantObs + 1);
            assertTrue("EMP03A: No se registro fechaPropProduccion",
                       p.getFechaPropProduccion().get(Calendar.DAY_OF_YEAR) + 1 == 
                       fechaAux.get(Calendar.DAY_OF_YEAR));
        } catch(EmpresaException ex){
            fail("EMP03A: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP03B(){
        fixture.setUpM03_A();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        Calendar fechaAux = GregorianCalendar.getInstance();
        fechaAux.add(Calendar.DAY_OF_YEAR, 1);
        try{
            fixture.getEmpresaTest().cancelarPedido(1, "P");
            assertTrue("EMP03B: Estado pedido no cambio",
                       p.getEstado() == Pedido.CANCELADO);
            assertTrue("EMP03B: Observacion cancelacion no registrada",
                       p.getListaObservaciones().size() == cantObs + 1);
            assertTrue("EMP03B: No se registro fechaPropProduccion",
                       p.getFechaPropProduccion().get(Calendar.DAY_OF_YEAR) + 1 == 
                       fechaAux.get(Calendar.DAY_OF_YEAR));
        } catch(EmpresaException ex){
            fail("EMP03B: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP03C(){
        fixture.setUpM03_A();
        try{
            fixture.getEmpresaTest().cancelarPedido(11, "Prueba");
            fail("EMP03C: Debio lanzarce una excepcion");
        } catch(EmpresaException ex){
            
        }
    }
    
    @Test
    public void testEMP03D(){
        fixture.setUpM03_A();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        int estado = p.getEstado();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().cancelarPedido(1, "");
            fail("EMP03D: Debio lanzarce un error de assercion");
        } catch(EmpresaException ex){
            fail("EMP03D: Excepcion no esperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP03D: Se agrego una observacion",
                       p.getListaObservaciones().size() == cantObs);
            assertTrue("EMP03D: Se modifico el estado del pedido",
                       p.getEstado() == estado);
            assertTrue("EMP03D: Se modifico la fechaPropProduccion",
                       p.getFechaPropProduccion() == null);
        }
        if(!assertError)
            fail("EMP03D: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP03E(){
        fixture.setUpM03_A();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        int estado = p.getEstado();
        String motivo = "";
        for(int i = 0; i < 700; i++)
            motivo += "a";
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().cancelarPedido(1, motivo);
            fail("EMP03E: Debio lanzarce un error de assercion");
        } catch(EmpresaException ex){
            fail("EMP03E: Excepcion no esperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP03E: Se agrego una observacion",
                       p.getListaObservaciones().size() == cantObs);
            assertTrue("EMP03E: Se modifico el estado del pedido",
                       p.getEstado() == estado);
            assertTrue("EMP03E: Se modifico la fechaPropProduccion",
                       p.getFechaPropProduccion() == null);
        }
        if(!assertError)
            fail("EMP03E: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP03F(){
        fixture.setUpM03_A();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        int estado = p.getEstado();
        String motivo = "";
        for(int i = 0; i < 501; i++)
            motivo += "a";
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().cancelarPedido(1, motivo);
            fail("EMP03F: Debio lanzarce un error de assercion");
        } catch(EmpresaException ex){
            fail("EMP03F: Excepcion no esperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP03F: Se agrego una observacion",
                       p.getListaObservaciones().size() == cantObs);
            assertTrue("EMP03F: Se modifico el estado del pedido",
                       p.getEstado() == estado);
            assertTrue("EMP03F: Se modifico la fechaPropProduccion",
                       p.getFechaPropProduccion() == null);
        }
        if(!assertError)
            fail("EMP03F: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP03K(){
        fixture.setUpM03_A();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        Calendar fechaAux = GregorianCalendar.getInstance();
        fechaAux.add(Calendar.DAY_OF_YEAR, 1);
        String motivo = "";
        for(int i = 0; i < 500; i++)
            motivo += "A";
        try{
            fixture.getEmpresaTest().cancelarPedido(1, motivo);
            assertTrue("EMP03K: Estado pedido no cambio",
                       p.getEstado() == Pedido.CANCELADO);
            assertTrue("EMP03K: Observacion cancelacion no registrada",
                       p.getListaObservaciones().size() == cantObs + 1);
            assertTrue("EMP03K: No se registro fechaPropProduccion",
                       p.getFechaPropProduccion().get(Calendar.DAY_OF_YEAR) + 1 == 
                       fechaAux.get(Calendar.DAY_OF_YEAR));
        } catch(EmpresaException ex){
            fail("EMP03K: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP03G(){
        fixture.setUpM03_B();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        Calendar fechaAux = GregorianCalendar.getInstance();
        try{
            fixture.getEmpresaTest().cancelarPedido(1, "Prueba");
            assertTrue("EMP03G: Estado pedido no cambio",
                       p.getEstado() == Pedido.CANCELADO);
            assertTrue("EMP03G: Observacion cancelacion no registrada",
                       p.getListaObservaciones().size() == cantObs + 1);
        } catch(EmpresaException ex){
            fail("EMP03G: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP03H(){
        fixture.setUpM03_C();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        int estado = p.getEstado();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().cancelarPedido(1, "Prueba");
            fail("EMP03H: Debio lanzarce un error de assercion");
        } catch(EmpresaException ex){
            fail("EMP03H: Excepcion no esperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP03H: Se agrego una observacion",
                       p.getListaObservaciones().size() == cantObs);
            assertTrue("EMP03H: Se modifico el estado del pedido",
                       p.getEstado() == estado);
            assertTrue("EMP03H: Se modifico la fechaPropProduccion",
                       p.getFechaPropProduccion() == null);
        }
        if(!assertError)
            fail("EMP03H: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP03L(){
        fixture.setUpM03_A();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        int estado = p.getEstado();
        String motivo = "";
        for(int i = 0; i < 501; i++)
            motivo += "a";
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().cancelarPedido(1, null);
            fail("EMP03L: Debio lanzarce un error de assercion");
        } catch(EmpresaException ex){
            fail("EMP03L: Excepcion no esperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP03L: Se agrego una observacion",
                       p.getListaObservaciones().size() == cantObs);
            assertTrue("EMP03L: Se modifico el estado del pedido",
                       p.getEstado() == estado);
            assertTrue("EMP03L: Se modifico la fechaPropProduccion",
                       p.getFechaPropProduccion() == null);
        }
        if(!assertError)
            fail("EMP03L: Debio lanzarce un error de assercion");
    }
    
    @Test
    public void testEMP03I(){
        fixture.setUpM03_D();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        int estado = p.getEstado();
        try{
            fixture.getEmpresaTest().cancelarPedido(1, "Prueba");
            fail("EMP03I: Debio lanzarce un error de assercion");
        } catch(EmpresaException ex){
            assertTrue("EMP03I: Se agrego una observacion",
                       p.getListaObservaciones().size() == cantObs);
            assertTrue("EMP03I: Se modifico el estado del pedido",
                       p.getEstado() == estado);
        }
    }
    
    @Test
    public void testEMP03J(){
        fixture.setUpM03_E();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(1);
        int cantObs = p.getListaObservaciones().size();
        int estado = p.getEstado();
        try{
            fixture.getEmpresaTest().cancelarPedido(1, "Prueba");
            fail("EMP03J: Debio lanzarce un error de assercion");
        } catch(EmpresaException ex){
            assertTrue("EMP03J: Se agrego una observacion",
                       p.getListaObservaciones().size() == cantObs);
            assertTrue("EMP03J: Se modifico el estado del pedido",
                       p.getEstado() == estado);
        }
    }
    
    @Test
    public void testEMP05A(){
        fixture.setUpM05_A();
        HashMap<Integer, Material> listado = null;
        try{
            listado = fixture.getEmpresaTest().consultaFaltantes(2);
            assertNotNull("EMP05A: Listado faltantes nulo", listado);
            assertTrue("EMP05A: Listado faltantes no vacio", listado.isEmpty());
        } catch(EmpresaException ex){
            fail("EMP05A: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP05B(){
        fixture.setUpM05_A();
        HashMap<Integer, Material> listado = null;
        try{
            listado = fixture.getEmpresaTest().consultaFaltantes(8);
            fail("EMP05B: Debio lanzarce excepcion");
        } catch(EmpresaException ex){
        }
    }
    
    @Test
    public void testEMP05C(){
        fixture.setUpM05_B();
        HashMap<Integer, Material> listado = null;
        try{
            listado = fixture.getEmpresaTest().consultaFaltantes(2);
            assertNotNull("EMP05C: Listado faltantes nulo", listado);
            assertTrue("EMP05C: Listado faltantes no vacio", listado.isEmpty());
        } catch(EmpresaException ex){
            fail("EMP05C: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP05D(){
        fixture.setUpM05_C();
        HashMap<Integer, Material> listado = null;
        try{
            listado = fixture.getEmpresaTest().consultaFaltantes(2);
            assertNotNull("EMP05D: Listado faltantes nulo", listado);
            assertTrue("EMP05D: Listado faltantes vacio", !listado.isEmpty());
            assertTrue("EMP05D: Tamaño listado incorrecto", listado.size() == 2);
            assertTrue("EMP05D: Contenido listado incorrecto", 
                       listado.containsKey(401) && listado.containsKey(402));
            assertTrue("EMP05D: Cantidades listado incorrectas",
                       listado.get(401).getCantidad() == 50.0 &&
                       listado.get(402).getCantidad() == 75.0);
        } catch(EmpresaException ex){
            fail("EMP05D: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP05E(){
        fixture.setUpM05_D();
        HashMap<Integer, Material> listado = null;
        try{
            listado = fixture.getEmpresaTest().consultaFaltantes(2);
            assertNotNull("EMP05E: Listado faltantes nulo", listado);
            assertTrue("EMP05E: Listado faltantes vacio", !listado.isEmpty());
            assertTrue("EMP05E: Tamaño listado incorrecto", listado.size() == 1);
            assertTrue("EMP05E: Contenido listado incorrecto", 
                       listado.containsKey(401));
            assertTrue("EMP05E: Cantidades listado incorrectas",
                       listado.get(401).getCantidad() == 0.001);
        } catch(EmpresaException ex){
            fail("EMP05E: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP05F(){
        fixture.setUpM05_E();
        try{
            fixture.getEmpresaTest().consultaFaltantes(2);
            fail("EMP05F: Devio lanzarce excepcion");
        } catch(EmpresaException ex){
            
        }
    }
    
    @Test
    public void testEMP05G(){
        fixture.setUpM05_F();
        try{
            fixture.getEmpresaTest().consultaFaltantes(2);
            fail("EMP05G: Devio lanzarce excepcion");
        } catch(EmpresaException ex){
            
        }
    }
    
   @Test
   public void testEMP05H(){
        fixture.setUpM05_G();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().consultaFaltantes(2);
            fail("EMP05H: Debio lanzarce un error de assercion");
        } catch(EmpresaException ex){
            fail("EMP05H: Excepcion no esperada: " + ex.toString());
        }
   }
   
   @Test
   public void testEMP06A(){
       fixture.setUpM06_A();
       try{
           double stock = fixture.getEmpresaTest().consultaStock(409);
           assertTrue("EMP06A: Cantidad retornada incorrecta",
                      stock == 75.0);
       } catch(EmpresaException ex){
           fail("EMP06A: Excepcion no esperada: " + ex.toString());
       }
   }
   
   @Test
   public void testEMP06B(){
       fixture.setUpM06_A();
       try{
           double stock = fixture.getEmpresaTest().consultaStock(24);
           fail("EMP06B: Se esperaba una excpecion");
       } catch(EmpresaException ex){
           
       }
   }
   
   @Test 
   public void testEMP06C(){
       fixture.setUpM06_B();
       try{
           double stock = fixture.getEmpresaTest().consultaStock(409);
           assertTrue("EMP06C: Cantidad retornada incorrecta",
                      stock == 200.0);
       } catch(EmpresaException ex){
           fail("EMP06C: Excepcion no esperada: " + ex.toString());
       }
   }
   
   @Test
   public void testEMP06D(){
       fixture.setUpM06_C();
       try{
           double stock = fixture.getEmpresaTest().consultaStock(24);
           fail("EMP06B: Se esperaba una excpecion");
       } catch(EmpresaException ex){
           
       }
   }
   
   @Test
   public void testEMP07A(){
       fixture.getEmpresaTest().deslog();
       assertNull("EMP07A: Usuario activo no nulo", fixture.getEmpresaTest().getUser());
   }
   
   @Test
   public void testEMP07B(){
       fixture.setUpM07_A();
       boolean assertError = false;
       try{
           fixture.getEmpresaTest().deslog();
       } catch(AssertionError e){
           assertError = true;
       }
       if(!assertError)
            fail("EMP07B: Debio lanzarce un error de assercion"); 
   }
   
   @Test
   public void testEMP08A(){
       fixture.setUpM08_A();
       HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100001).getListadoMateriales();
       try{
           fixture.getEmpresaTest().eliminarMaterialReceta(100001, 403);
           assertTrue("EMP08A: Listado de materiales no tiene longitud correcta",
                      receta.size() == 1);
           assertTrue("EMP08A: Listado de materiales no contiene elementos correctos",
                      receta.containsKey(401));
           assertTrue("EMP08A: Listado de materiales no contiene cantidad correcta",
                      receta.get(401).getCantidad() == 35.0);
       } catch(EmpresaException ex){
           fail("EMP08A: Excepcion no esperada: " + ex.toString());
       }
   }
   
   @Test
   public void testEMP08B(){
       fixture.setUpM08_A();
       HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100001).getListadoMateriales();
       try{
           fixture.getEmpresaTest().eliminarMaterialReceta(100011, 403);
           fail("EMP08B: Se esperaba excepcion");
       } catch(EmpresaException ex){    
       }
   }
   
   @Test
   public void testEMP08C(){
       fixture.setUpM08_A();
       HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100001).getListadoMateriales();
       try{
           fixture.getEmpresaTest().eliminarMaterialReceta(100001, 407);
           fail("EMP08C: Se esperaba excepcion");
       } catch(EmpresaException ex){
           assertTrue("EMP08C: Cantidad de elementos listado incorrecta",
                      receta.size() == 2);
           assertTrue("EMP08C: Contenido receta incorrecto",
                      receta.containsKey(401) && receta.containsKey(403));
           assertTrue("EMP08C: Cantidades receta incorrectas",
                      receta.get(401).getCantidad() == 35.0 &&
                      receta.get(403).getCantidad() == 50.0);
       }
   }
   
   @Test
   public void testEMP08D(){
       fixture.setUpM08_B();
       HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100001).getListadoMateriales();
       try{
           fixture.getEmpresaTest().eliminarMaterialReceta(100001, 403);
           assertTrue("EMP08D: Listado de materiales no tiene longitud correcta",
                      receta.size() == 0);
       } catch(EmpresaException ex){
           fail("EMP08D: Excepcion no esperada: " + ex.toString());
       }
   }
   
   @Test
   public void testEMP08E(){
       fixture.setUpM08_C();
       HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100001).getListadoMateriales();
       boolean assertError = false;
       try{
           fixture.getEmpresaTest().eliminarMaterialReceta(100001, 403);
       } catch(EmpresaException ex){
           fail("EMP08E: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
           assertError = true;
           assertTrue("EMP08E: Cantidad de elementos listado incorrecta",
                      receta.size() == 2);
           assertTrue("EMP08E: Contenido receta incorrecto",
                      receta.containsKey(401) && receta.containsKey(403));
           assertTrue("EMP08E: Cantidades receta incorrectas",
                      receta.get(401).getCantidad() == 35.0 &&
                      receta.get(403).getCantidad() == 50.0);
       }
       if(!assertError)
            fail("EMP08E: Debio lanzarce error de assercion");
   }
   
   @Test
   public void testEMP09A(){
       fixture.setUpM09_A();
       Pedido p = fixture.getEmpresaTest().getPedidos().get(3);
       Calendar fechaAux = new GregorianCalendar(2017, 12, 31);
       try{
           fixture.getEmpresaTest().inciarEvaluacionPedido(3, fechaAux);
           assertTrue("EMP09A: Fecha propuesta incorrecta",
                      fechaAux.equals(p.getFechaPropProduccion()));
           assertTrue("EMP09A: Estado pedido incorrecto",
                      p.getEstado() == Pedido.EN_EVALUACION);
       } catch(EmpresaException ex){
           fail("EMP09A: Excepcion no esperada");
       }
   }
   
   @Test
   public void testEMP09B(){
        fixture.setUpM09_A();
        Pedido p = fixture.getEmpresaTest().getPedidos().get(3);
        Calendar fechaAux = new GregorianCalendar(2017, 12, 31);
        try{
            fixture.getEmpresaTest().inciarEvaluacionPedido(7, fechaAux);
            fail("EMP09B: Se esperaba excepcion");
        } catch(EmpresaException ex){
            
        }
   }
   
   @Test
   public void testEMP09C(){
       fixture.setUpM09_A();
       Pedido p = fixture.getEmpresaTest().getPedidos().get(3);
       Calendar fechaAux = new GregorianCalendar(2015, 12, 31);
       boolean assertError = false;
       try{
           fixture.getEmpresaTest().inciarEvaluacionPedido(3, fechaAux);
           fail("EMP09C: Se esperaba error de assercion");
       } catch(EmpresaException ex){
           fail("EMP09C: Se esperaba error de assercion");
        } catch(AssertionError e){
            assertError = true;
            assertNull("EMP09C: fechaPropProduccion se modifico",
                       p.getFechaPropProduccion());
            assertTrue("EMP09C: Estado pedido modificado",
                       p.getEstado() == Pedido.INICIADO);
        }
       if(!assertError)
            fail("EMP09C: Se esperaba error de assercion");
   }
   
   @Test
   public void testEMP09D(){
       fixture.setUpM09_A();
       Pedido p = fixture.getEmpresaTest().getPedidos().get(3);
       Calendar fechaAux = GregorianCalendar.getInstance();
       boolean assertError = false;
       try{
           fixture.getEmpresaTest().inciarEvaluacionPedido(3, fechaAux);
           fail("EMP09D: Se esperaba error de assercion");
       } catch(EmpresaException ex){
           fail("EMP09D: Se esperaba error de assercion");
        } catch(AssertionError e){
            assertError = true;
            assertNull("EMP09D: fechaPropProduccion se modifico",
                       p.getFechaPropProduccion());
            assertTrue("EMP09D: Estado pedido modificado",
                       p.getEstado() == Pedido.INICIADO);
        }
       if(!assertError)
            fail("EMP09D: Se esperaba error de assercion");
   }
   
   @Test
   public void testEMP09E(){
       fixture.setUpM09_A();
       Pedido p = fixture.getEmpresaTest().getPedidos().get(3);
       Calendar fechaAux = null;
       boolean assertError = false;
       try{
           fixture.getEmpresaTest().inciarEvaluacionPedido(3, fechaAux);
           fail("EMP09E: Se esperaba error de assercion");
       } catch(EmpresaException ex){
           fail("EMP09E: Se esperaba error de assercion");
        } catch(AssertionError e){
            assertError = true;
            assertNull("EMP09E: fechaPropProduccion se modifico",
                       p.getFechaPropProduccion());
            assertTrue("EMP09E: Estado pedido modificado",
                       p.getEstado() == Pedido.INICIADO);
        }
       if(!assertError)
            fail("EMP09E: Se esperaba error de assercion");
   }
   
   @Test
   public void testEMP09F(){
       fixture.setUpM09_B();
       Pedido p = fixture.getEmpresaTest().getPedidos().get(3);
       Calendar fechaAux = null;
       boolean assertError = false;
       try{
           fixture.getEmpresaTest().inciarEvaluacionPedido(3, fechaAux);
           fail("EMP09F: Se esperaba error de assercion");
       } catch(EmpresaException ex){
           fail("EMP09F: Se esperaba error de assercion");
        } catch(AssertionError e){
            assertError = true;
            assertNull("EMP09F: fechaPropProduccion se modifico",
                       p.getFechaPropProduccion());
            assertTrue("EMP09F: Estado pedido modificado",
                       p.getEstado() == Pedido.INICIADO);
        }
       if(!assertError)
            fail("EMP09E: Se esperaba error de assercion"); 
   }
   
   @Test
   public void testEMP09G(){
       fixture.setUpM09_C();
       Pedido p = fixture.getEmpresaTest().getPedidos().get(3);
       Calendar fechaPropProduccion = p.getFechaPropProduccion();
       Calendar fechaAux = new GregorianCalendar(2017, 12, 31);
       try{
            fixture.getEmpresaTest().inciarEvaluacionPedido(3, fechaAux);
            fail("EMP09G: Se esperaba excepcion");
       } catch(EmpresaException ex){
            assertTrue("EMP09G: Se modifico fechaPropProduccion",
                       fechaPropProduccion.equals(p.getFechaPropProduccion()));
            assertTrue("EMP09G: Se modifico estado",
                       p.getEstado() == Pedido.ACEPTADO);
       }
   }
   
   @Test
   public void testEMP09H(){
       fixture.setUpM09_D();
       Pedido p = fixture.getEmpresaTest().getPedidos().get(3);
       Calendar fechaPropProduccion = p.getFechaPropProduccion();
       Calendar fechaAux = new GregorianCalendar(2017, 12, 31);
       try{
            fixture.getEmpresaTest().inciarEvaluacionPedido(3, fechaAux);
            fail("EMP09H: Se esperaba excepcion");
       } catch(EmpresaException ex){
            assertTrue("EMP09H: Se modifico fechaPropProduccion",
                       fechaPropProduccion.equals(p.getFechaPropProduccion()));
       }
   }
   
   @Test
   public void testEMP10A(){
       fixture.setUpM10_A();
       int sizeListaPed = fixture.getEmpresaTest().getPedidos().size();
       try{
           fixture.getEmpresaTest().iniciarPedido(100001, 10, new GregorianCalendar(2017, 12, 31));
           int nPed = Pedido.getUltPedido();
           Pedido p = fixture.getEmpresaTest().getPedidos().get(nPed);
           assertTrue("EMP10A: Tamaño lista pedidos incorrecta",
                      fixture.getEmpresaTest().getPedidos().size() == sizeListaPed + 1);
           assertEquals("EMP10A: FechaEntrega incorrecta",
                        p.getFechaEntrega(), new GregorianCalendar(2017, 12, 31));
           assertTrue("EMP10A: Cantidad incorrecta",
                      p.getCantidad() == 10);
       } catch(EmpresaException ex){
           fail("EMP10A: Excepcion inesperada: " + ex.toString());
       }
   }
   
   @Test
   public void testEMP10B(){
       fixture.setUpM10_A();
       int sizeListaPed = fixture.getEmpresaTest().getPedidos().size();
       Calendar fechaAux = GregorianCalendar.getInstance();
       fechaAux.add(Calendar.DAY_OF_YEAR, 1);
       try{
           fixture.getEmpresaTest().iniciarPedido(100001, 10, fechaAux);
           int nPed = Pedido.getUltPedido();
           Pedido p = fixture.getEmpresaTest().getPedidos().get(nPed);
           assertTrue("EMP10B: Tamaño lista pedidos incorrecta",
                      fixture.getEmpresaTest().getPedidos().size() == sizeListaPed + 1);
           assertEquals("EMP10B: FechaEntrega incorrecta",
                        p.getFechaEntrega(), fechaAux);
           assertTrue("EMP10B: Cantidad incorrecta",
                      p.getCantidad() == 10);
       } catch(EmpresaException ex){
           fail("EMP10B: Excepcion inesperada: " + ex.toString());
       }
   }
   
   @Test
   public void testEMP10C(){
       fixture.setUpM10_A();
       int sizeListaPed = fixture.getEmpresaTest().getPedidos().size();
       Calendar fechaAux = GregorianCalendar.getInstance();
       fechaAux.add(Calendar.DAY_OF_YEAR, 1);
       try{
           fixture.getEmpresaTest().iniciarPedido(100001, 1, fechaAux);
           int nPed = Pedido.getUltPedido();
           Pedido p = fixture.getEmpresaTest().getPedidos().get(nPed);
           assertTrue("EMP10C: Tamaño lista pedidos incorrecta",
                      fixture.getEmpresaTest().getPedidos().size() == sizeListaPed + 1);
           assertEquals("EMP10C: FechaEntrega incorrecta",
                        p.getFechaEntrega(), fechaAux);
           assertTrue("EMP10C: Cantidad incorrecta",
                      p.getCantidad() == 1);
       } catch(EmpresaException ex){
           fail("EMP10C: Excepcion inesperada: " + ex.toString());
       }
   }
   
   @Test
   public void testEMP10D(){
       fixture.setUpM10_A();
       int sizeListaPed = fixture.getEmpresaTest().getPedidos().size();
       Calendar fechaAux = GregorianCalendar.getInstance();
       fechaAux.add(Calendar.DAY_OF_YEAR, 1);
       try{
           fixture.getEmpresaTest().iniciarPedido(100001, 999, fechaAux);
           int nPed = Pedido.getUltPedido();
           Pedido p = fixture.getEmpresaTest().getPedidos().get(nPed);
           assertTrue("EMP10D: Tamaño lista pedidos incorrecta",
                      fixture.getEmpresaTest().getPedidos().size() == sizeListaPed + 1);
           assertEquals("EMP10D: FechaEntrega incorrecta",
                        p.getFechaEntrega(), fechaAux);
           assertTrue("EMP10D: Cantidad incorrecta",
                      p.getCantidad() == 999);
       } catch(EmpresaException ex){
           fail("EMP10D: Excepcion inesperada: " + ex.toString());
       }
   }
   
    @Test
    public void testEMP10E(){
        fixture.setUpM10_A();
        int listaPedidosSize = fixture.getEmpresaTest().getPedidos().size();
        try{
            fixture.getEmpresaTest().iniciarPedido(100221, 10, new GregorianCalendar(2017, 12, 31));
            fail("EMP10E: Se esperaba excepcion");
        } catch(EmpresaException ex){
            assertTrue("EMP10E: cambio tamaño lista de pedidos",
                       listaPedidosSize == fixture.getEmpresaTest().getPedidos().size());
        }     
    }
    
    @Test
    public void testEMP10F(){
        fixture.setUpM10_A();
        boolean assertError = false;
        int listaPedidosSize = fixture.getEmpresaTest().getPedidos().size();
        try{
            fixture.getEmpresaTest().iniciarPedido(100001, 10, new GregorianCalendar(2015, 12, 31));
            fail("EMP10F: Se esperaba error de assercion");
        } catch(EmpresaException ex){
            fail("EMP10F: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP10F: Tamaño lista pedidos cambio",
                       listaPedidosSize == fixture.getEmpresaTest().getPedidos().size());
        }
        if(!assertError)
            fail("EMP10F: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP10G(){
        fixture.setUpM10_A();
        boolean assertError = false;
        int listaPedidosSize = fixture.getEmpresaTest().getPedidos().size();
        try{
            fixture.getEmpresaTest().iniciarPedido(100001, 10, GregorianCalendar.getInstance());
            fail("EMP10G: Se esperaba error de assercion");
        } catch(EmpresaException ex){
            fail("EMP10G: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP10G: Tamaño lista pedidos cambio",
                       listaPedidosSize == fixture.getEmpresaTest().getPedidos().size());
        }
        if(!assertError)
            fail("EMP10G: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP10H(){
        fixture.setUpM10_A();
        boolean assertError = false;
        int listaPedidosSize = fixture.getEmpresaTest().getPedidos().size();
        try{
            fixture.getEmpresaTest().iniciarPedido(100001, 10, null);
            fail("EMP10H: Se esperaba error de assercion");
        } catch(EmpresaException ex){
            fail("EMP10H: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP10H: Tamaño lista pedidos cambio",
                       listaPedidosSize == fixture.getEmpresaTest().getPedidos().size());
        }
        if(!assertError)
            fail("EMP10H: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP10I(){
        fixture.setUpM10_A();
        boolean assertError = false;
        int listaPedidosSize = fixture.getEmpresaTest().getPedidos().size();
        try{
            fixture.getEmpresaTest().iniciarPedido(100001, -30, new GregorianCalendar(2017, 12, 31));
            fail("EMP10I: Se esperaba error de assercion");
        } catch(EmpresaException ex){
            fail("EMP10I: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP10I: Tamaño lista pedidos cambio",
                       listaPedidosSize == fixture.getEmpresaTest().getPedidos().size());
        }
        if(!assertError)
            fail("EMP10I: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP10J(){
        fixture.setUpM10_A();
        boolean assertError = false;
        int listaPedidosSize = fixture.getEmpresaTest().getPedidos().size();
        try{
            fixture.getEmpresaTest().iniciarPedido(100001, 0, new GregorianCalendar(2017, 12, 31));
            fail("EMP10J: Se esperaba error de assercion");
        } catch(EmpresaException ex){
            fail("EMP10J: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP10J: Tamaño lista pedidos cambio",
                       listaPedidosSize == fixture.getEmpresaTest().getPedidos().size());
        }
        if(!assertError)
            fail("EMP10J: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP10K(){
        fixture.setUpM10_A();
        boolean assertError = false;
        int listaPedidosSize = fixture.getEmpresaTest().getPedidos().size();
        try{
            fixture.getEmpresaTest().iniciarPedido(100001, 1050, new GregorianCalendar(2017, 12, 31));
            fail("EMP10K: Se esperaba error de assercion");
        } catch(EmpresaException ex){
            fail("EMP10K: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP10K: Tamaño lista pedidos cambio",
                       listaPedidosSize == fixture.getEmpresaTest().getPedidos().size());
        }
        if(!assertError)
            fail("EMP10K: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP10L(){
        fixture.setUpM10_A();
        boolean assertError = false;
        int listaPedidosSize = fixture.getEmpresaTest().getPedidos().size();
        try{
            fixture.getEmpresaTest().iniciarPedido(100001, 1000, new GregorianCalendar(2017, 12, 31));
            fail("EMP10L: Se esperaba error de assercion");
        } catch(EmpresaException ex){
            fail("EMP10L: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP10L: Tamaño lista pedidos cambio",
                       listaPedidosSize == fixture.getEmpresaTest().getPedidos().size());
        }
        if(!assertError)
            fail("EMP10L: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP10M(){
        fixture.setUpM10_B();
        boolean assertError = false;
        int listaPedidosSize = fixture.getEmpresaTest().getPedidos().size();
        try{
            fixture.getEmpresaTest().iniciarPedido(100001, 10, new GregorianCalendar(2017, 12, 31));
            fail("EMP10M: Se esperaba error de assercion");
        } catch(EmpresaException ex){
            fail("EMP10M: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP10M: Tamaño lista pedidos cambio",
                       listaPedidosSize == fixture.getEmpresaTest().getPedidos().size());
        }
        if(!assertError)
            fail("EMP10M: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP11A(){
        fixture.setUpM11_A();
        String listadoObs = "";
        try{
            listadoObs = fixture.getEmpresaTest().listarObservaciones(2);
            assertTrue("EMP11A: ListadoObservaciones no vacio",
                       listadoObs.isEmpty());
        } catch(EmpresaException ex){
            fail("EMP11A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP11B(){
        fixture.setUpM11_A();
        String listadoObs = "";
        try{
            listadoObs = fixture.getEmpresaTest().listarObservaciones(12);
            fail("EMP11B: Se esperaba excepcion");
        } catch(EmpresaException ex){
        }
    }
    
    @Test
    public void testEMP11C(){
        fixture.setUpM11_B();
        String listadoObs = "";
        String listadoEsperado = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        listadoEsperado += String.format("TEMA: %-10.10s\tFECHA: %-10.10s\tEMITIO: LEG%6.6d\n%s",
                             "OTROS", format.format(GregorianCalendar.getInstance().getTime()), 1,
                             "Prueba");
        listadoEsperado += String.format("TEMA: %-10.10s\tFECHA: %-10.10s\tEMITIO: LEG%6.6d\n%s",
                             "INSUMOS", format.format(GregorianCalendar.getInstance().getTime()), 2,
                             "Prueba");
        listadoEsperado += String.format("TEMA: %-10.10s\tFECHA: %-10.10s\tEMITIO: LEG%6.6d\n%s",
                             "FECHAS", format.format(GregorianCalendar.getInstance().getTime()), 1,
                             "Prueba");
        try{
            listadoObs = fixture.getEmpresaTest().listarObservaciones(2);
            assertEquals("EMP11C: listado no coincide con lo esperado",
                         listadoObs, listadoEsperado);
        } catch(EmpresaException ex){
            fail("EMP11C: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP11D(){
        fixture.setUpM11_C();
        String listadoObs = "";
        String listadoEsperado = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        listadoEsperado += String.format("TEMA: %-10.10s\tFECHA: %-10.10s\tEMITIO: LEG%6.6d\n%s",
                             "OTROS", format.format(GregorianCalendar.getInstance().getTime()), 1,
                             "Prueba");
        try{
            listadoObs = fixture.getEmpresaTest().listarObservaciones(2);
            assertEquals("EMP11D: listado no coincide con lo esperado",
                         listadoObs, listadoEsperado);
        } catch(EmpresaException ex){
            fail("EMP11D: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP12A(){
        fixture.setUpM12_A();
        try{
            fixture.getEmpresaTest().login(4);
            assertNotNull("EMP12A: Usuario logueado nulo.",
                          fixture.getEmpresaTest().getUser());
            assertTrue("EMP12A: Usuario logueado incorrecto.",
                       fixture.getEmpresaTest().getUser().getLegajo() == 4);
        } catch(EmpresaException ex){
            fail("EMP12A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP12B(){
        fixture.setUpM12_A();
        try{
            fixture.getEmpresaTest().login(8);
            fail("EMP12B: se esperaba una excepcion");
        } catch(EmpresaException ex){
            assertNull("EMP12B: Se logueo igualmente.",
                       fixture.getEmpresaTest().getUser());
        }
    }
    
    @Test
    public void testEMP12C(){
        fixture.setUpM12_B();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().login(4);
        } catch(EmpresaException ex){
            fail("EMP12C: Se esperaba un error de assercion");
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP12C: Se modifico usuario logueado",
                       fixture.getEmpresaTest().getUser().getLegajo() == 9999);
        }
        if(!assertError)
            fail("EMP12C: Se esperaba un error de assercion");
    }
    
    @Test
    public void testEMP13A(){
        fixture.setUpM13_A();
        String descMat1 = fixture.getEmpresaTest().getInventario().get(401).getDescripcion();
        String descMat4 = fixture.getEmpresaTest().getInventario().get(404).getDescripcion();
        String listadoEsperado = "";
        listadoEsperado += String.format("Cod: MAT%05d\t\t%-100.100s\t%4.3f" + System.lineSeparator(), 
                                  401 , descMat1, 150.000);
        listadoEsperado += String.format("Cod: MAT%05d\t\t%-100.100s\t%4.3f" + System.lineSeparator(), 
                                  404 , descMat4, 125.000);
        String listado = "";
        try{
            listado = fixture.getEmpresaTest().materialesNecesaarios(2);
            assertEquals("EMP13A: listado no coincide con el esperado",
                         listado, listadoEsperado);
        } catch(EmpresaException ex){
            fail("EMP13A: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP13B(){
        fixture.setUpM13_A();
        String listado = "";
        try{
            listado = fixture.getEmpresaTest().materialesNecesaarios(12);
            fail("EMP13B: Se esperaba una excepcion");
        } catch(EmpresaException ex){
        }
    }
    
    @Test
    public void testEMP13C(){
        fixture.setUpM13_B();
        String listadoEsperado = "";
        String listado = "";
        try{
            listado = fixture.getEmpresaTest().materialesNecesaarios(2);
            assertEquals("EMP13C: listado no coincide con el esperado",
                         listado, listadoEsperado);
        } catch(EmpresaException ex){
            fail("EMP13C: Excepcion no esperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP13D(){
        fixture.setUpM13_C();
        String listadoEsperado = "";
        String listado = "";
        boolean assertError = false;
        try{
            listado = fixture.getEmpresaTest().materialesNecesaarios(2);
        } catch(EmpresaException ex){
            fail("EMP13D: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
        }
        if(!assertError)
            fail("EMP13D: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP14A(){
        fixture.setUpM14_A();
        HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100004).getListadoMateriales();
        try{
            fixture.getEmpresaTest().modificarCantidadReceta(100004, 405, 60.0);
            assertTrue("EMP14A: Cantidad de elementos en recerta incorrecta",
                       receta.size() == 2);
            assertTrue("EMP14A: Elementos en receta incorrectos",
                       receta.containsKey(402) && receta.containsKey(405));
            assertTrue("EMP14A: Cantidades en receta incorrectos",
                       receta.get(402).getCantidad() == 30.0 && receta.get(405).getCantidad() == 60.0); 
        } catch(EmpresaException ex){
            fail("EMP14A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP14B(){
        fixture.setUpM14_A();
        HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100004).getListadoMateriales();
        try{
            fixture.getEmpresaTest().modificarCantidadReceta(100004, 405, 0.001);
            assertTrue("EMP14B: Cantidad de elementos en recerta incorrecta",
                       receta.size() == 2);
            assertTrue("EMP14B: Elementos en receta incorrectos",
                       receta.containsKey(402) && receta.containsKey(405));
            assertTrue("EMP14B: Cantidades en receta incorrectos",
                       receta.get(402).getCantidad() == 30.0 && receta.get(405).getCantidad() == 0.001); 
        } catch(EmpresaException ex){
            fail("EMP14B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP14C(){
        fixture.setUpM14_A();
        HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100004).getListadoMateriales();
        try{
            fixture.getEmpresaTest().modificarCantidadReceta(100008, 405, 60.0);
            fail("EMP14C: Se esperaba una excepcion");
        } catch(EmpresaException ex){
        }
    }
    
    @Test
    public void testEMP14D(){
        fixture.setUpM14_A();
        HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100004).getListadoMateriales();
        try{
            fixture.getEmpresaTest().modificarCantidadReceta(100004, 9, 60.0);
            fail("EMP14D: Se esperaba una excepcion");
        } catch(EmpresaException ex){
            assertTrue("EMP14D: Se modifico cantidad elementos receta",
                       receta.size() == 2);
            assertTrue("EMP14D: Se modifico elementos receta",
                       receta.containsKey(402) && receta.containsKey(405));
            assertTrue("EMP14D: Se modifico canitdades receta",
                       receta.get(402).getCantidad() == 30.0 && receta.get(405).getCantidad() == 50.0);
        }
    }
    
    @Test
    public void testEMP14E(){
        fixture.setUpM14_A();
        HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100004).getListadoMateriales();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().modificarCantidadReceta(100004, 405, -6.0);
        } catch(EmpresaException ex){
            fail("EMP14E: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP14E: Se modifico cantidad elementos receta",
                       receta.size() == 2);
            assertTrue("EMP14E: Se modifico elementos receta",
                       receta.containsKey(402) && receta.containsKey(405));
            assertTrue("EMP14E: Se modifico canitdades receta",
                       receta.get(402).getCantidad() == 30.0 && receta.get(405).getCantidad() == 50.0);
        }
        if(!assertError)
            fail("EMP14E: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP14F(){
        fixture.setUpM14_A();
        HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100004).getListadoMateriales();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().modificarCantidadReceta(100004, 405, 0.0);
        } catch(EmpresaException ex){
            fail("EMP14F: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP14F: Se modifico cantidad elementos receta",
                       receta.size() == 2);
            assertTrue("EMP14F: Se modifico elementos receta",
                       receta.containsKey(402) && receta.containsKey(405));
            assertTrue("EMP14F: Se modifico canitdades receta",
                       receta.get(402).getCantidad() == 30.0 && receta.get(405).getCantidad() == 50.0);
        }
        if(!assertError)
            fail("EMP14F: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP14G(){
        fixture.setUpM14_B();
        HashMap<Integer, Material> receta = fixture.getEmpresaTest().getProductos().get(100004).getListadoMateriales();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().modificarCantidadReceta(100004, 405, 60.0);
        } catch(EmpresaException ex){
            fail("EMP14G: Excepcion inesperada: " + ex.toString());
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP14G: Se modifico cantidad elementos receta",
                       receta.size() == 2);
            assertTrue("EMP14G: Se modifico elementos receta",
                       receta.containsKey(402) && receta.containsKey(405));
            assertTrue("EMP14G: Se modifico canitdades receta",
                       receta.get(402).getCantidad() == 30.0 && receta.get(405).getCantidad() == 50.0);
        }
        if(!assertError)
            fail("EMP14G: Se esperaba error de assercion");
    }
    
    @Test
    public void testEMP15A(){
        fixture.setUpM15_A();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        try{
            fixture.getEmpresaTest().observarPedido(3, "OTROS", "Prueba");
            assertTrue("EMP15A: No se agrego observacion",
                       fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size() == cantObs + 1);
        } catch(EmpresaException ex){
            fail("EMP15A: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP15B(){
        fixture.setUpM15_A();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        try{
            fixture.getEmpresaTest().observarPedido(3, "OTROS", "P");
            assertTrue("EMP15B: No se agrego observacion",
                       fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size() == cantObs + 1);
        } catch(EmpresaException ex){
            fail("EMP15B: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP15C(){
        fixture.setUpM15_A();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        String obs = "";
        for(int i = 0; i < 500; ++i)
            obs += "P";
        try{
            fixture.getEmpresaTest().observarPedido(3, "OTROS", obs);
            assertTrue("EMP15C: No se agrego observacion",
                       fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size() == cantObs + 1);
        } catch(EmpresaException ex){
            fail("EMP15C: Excepcion inesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP15D(){
        fixture.setUpM15_A();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        try{
            fixture.getEmpresaTest().observarPedido(13, "OTROS", "Prueba");
            fail("EMP15D: Se esperaba excepcion");
        } catch(EmpresaException ex){
            assertTrue("EMP15D: Listado de observaciones se modifico",
                       cantObs == fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size());
        }
    }
    
    @Test
    public void testEMP15E(){
        fixture.setUpM15_A();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().observarPedido(3, "PRUEBA", "Prueba");
        } catch(EmpresaException ex){
            fail("EMP15E: Se recibio excepcion en lugar de error de asercion");
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP15E: Listado de observaciones se modifico",
                       cantObs == fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size());
        }
        if(!assertError)
            fail("EMP15E: Se esperaba un error de assercion");
    }
    
    @Test
    public void testEMP15F(){
        fixture.setUpM15_A();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().observarPedido(3, "", "Prueba");
        } catch(EmpresaException ex){
            fail("EMP15F: Se recibio excepcion en lugar de error de asercion");
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP15F: Listado de observaciones se modifico",
                       cantObs == fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size());
        }
        if(!assertError)
            fail("EMP15F: Se esperaba un error de assercion");
    }
    
    @Test
    public void testEMP15G(){
        fixture.setUpM15_A();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().observarPedido(3, "OTROS", "");
        } catch(EmpresaException ex){
            fail("EMP15G: Se recibio excepcion en lugar de error de asercion");
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP15G: Listado de observaciones se modifico",
                       cantObs == fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size());
        }
        if(!assertError)
            fail("EMP15G: Se esperaba un error de assercion");
    }
    
    @Test
    public void testEMP15H(){
        fixture.setUpM15_A();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        String obs = "";
        for(int i = 0; i < 750; ++i)
            obs += "P";
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().observarPedido(3, "OTROS", obs);
        } catch(EmpresaException ex){
            fail("EMP15H: Se recibio excepcion en lugar de error de asercion");
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP15H: Listado de observaciones se modifico",
                       cantObs == fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size());
        }
        if(!assertError)
            fail("EMP15H: Se esperaba un error de assercion");
    }
    
    @Test
    public void testEMP15I(){
        fixture.setUpM15_A();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        String obs = "";
        for(int i = 0; i < 501; ++i)
            obs += "P";
        boolean assertError = false;
        try{
            fixture.getEmpresaTest().observarPedido(3, "OTROS", obs);
        } catch(EmpresaException ex){
            fail("EMP15I: Se recibio excepcion en lugar de error de asercion");
        } catch(AssertionError e){
            assertError = true;
            assertTrue("EMP15I: Listado de observaciones se modifico",
                       cantObs == fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size());
        }
        if(!assertError)
            fail("EMP15I: Se esperaba un error de assercion");
    }
    
    @Test
    public void testEMP15J(){
        fixture.setUpM15_B();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        try{
            fixture.getEmpresaTest().observarPedido(3, "OTROS", "Prueba");
            fail("EMP15J: Se esperaba excepcion");
        } catch(EmpresaException ex){
            assertTrue("EMP15J: Listado observaciones se modifico",
                       cantObs == fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size());
        }
    }
    
    @Test
    public void testEMP15K(){
        fixture.setUpM15_C();
        int cantObs = fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size();
        try{
            fixture.getEmpresaTest().observarPedido(3, "OTROS", "Prueba");
            fail("EMP15K: Se esperaba excepcion");
        } catch(EmpresaException ex){
            assertTrue("EMP15K: Listado observaciones se modifico",
                       cantObs == fixture.getEmpresaTest().getPedidos().get(3).getListaObservaciones().size());
        }
    }
    
    @Test
    public void testEMP16A(){
        fixture.setUpM16_A();
        try{
            fixture.getEmpresaTest().reservarMateriales(2);
            assertTrue("EMP16A: Cantidades inventarios no se modificaron correctamente",
                       fixture.getEmpresaTest().getInventario().get(401).getCantidad() == 2375.0 &&
                       fixture.getEmpresaTest().getInventario().get(402).getCantidad() == 200.0 &&
                       fixture.getEmpresaTest().getInventario().get(403).getCantidad() == 500.0 &&
                       fixture.getEmpresaTest().getInventario().get(404).getCantidad() == 250.0);
        } catch(EmpresaException ex){
            fail("EMP16A: Excepcion insesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP16B(){
        fixture.setUpM16_A();
        try{
            fixture.getEmpresaTest().reservarMateriales(12);
            fail("EMP16B: Se esperaba una excepcion");
        } catch(EmpresaException ex){
            assertTrue("EMP16A: Cantidades inventarios se modificaron",
                       fixture.getEmpresaTest().getInventario().get(401).getCantidad() == 2500.0 &&
                       fixture.getEmpresaTest().getInventario().get(402).getCantidad() == 400.0 &&
                       fixture.getEmpresaTest().getInventario().get(403).getCantidad() == 500.0 &&
                       fixture.getEmpresaTest().getInventario().get(404).getCantidad() == 250.0);
        }
    }
    
    @Test
    public void testEMP16C(){
        fixture.setUpM16_B();
        try{
            fixture.getEmpresaTest().reservarMateriales(2);
            assertTrue("EMP16C: Cantidades inventarios no se modificaron correctamente",
                       fixture.getEmpresaTest().getInventario().get(401).getCantidad() == 0.0 &&
                       fixture.getEmpresaTest().getInventario().get(402).getCantidad() == 0.0 &&
                       fixture.getEmpresaTest().getInventario().get(403).getCantidad() == 500.0 &&
                       fixture.getEmpresaTest().getInventario().get(404).getCantidad() == 250.0);
        } catch(EmpresaException ex){
            fail("EMP16C: Excepcion insesperada: " + ex.toString());
        }
    }
    
    @Test
    public void testEMP16D(){
        fixture.setUpM16_C();
        try{
            fixture.getEmpresaTest().reservarMateriales(2);
            fail("EMP16D: Se esperaba una excepcion");
        } catch(EmpresaException ex){
            assertTrue("EMP16D: Cantidades inventarios se modificaron",
                       fixture.getEmpresaTest().getInventario().get(401).getCantidad() == 50.0 &&
                       fixture.getEmpresaTest().getInventario().get(402).getCantidad() == 150.0 &&
                       fixture.getEmpresaTest().getInventario().get(403).getCantidad() == 500.0 &&
                       fixture.getEmpresaTest().getInventario().get(404).getCantidad() == 250.0);
        }
    }
    
    @Test
    public void testEMP16E(){
        fixture.setUpM16_D();
        try{
            fixture.getEmpresaTest().reservarMateriales(2);
            fail("EMP16B: Se esperaba una excepcion");
        } catch(EmpresaException ex){
            assertTrue("EMP16A: Cantidades inventarios se modificaron",
                       fixture.getEmpresaTest().getInventario().get(401).getCantidad() == 350.0 &&
                       fixture.getEmpresaTest().getInventario().get(402).getCantidad() == 199.999 &&
                       fixture.getEmpresaTest().getInventario().get(403).getCantidad() == 500.0 &&
                       fixture.getEmpresaTest().getInventario().get(404).getCantidad() == 250.0);
        }
    }
}
 