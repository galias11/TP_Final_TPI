package TestCajaNegra;

import empresa.EmpresaException;
import empresa.Maquina;
import empresa.Material;
import empresa.Observacion;
import empresa.Pedido;

import java.util.Calendar;

import java.util.GregorianCalendar;

import java.util.HashMap;

import java.util.Iterator;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

public class PedidoTest
{
  private FixturePedido fixture = new FixturePedido();

  public PedidoTest()
  {
    super();
  }

  @Before
  public void setUp()
  {
    fixture.setUp();
  }

  @After
  public void tearDown()
  {
    fixture.tearDown();
  }
  
    private void resetHoraFecha(Calendar fecha){
        fecha.set(Calendar.HOUR_OF_DAY, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }
    
    private Calendar fechaActual(){
        Calendar fechaAct = GregorianCalendar.getInstance();
        resetHoraFecha(fechaAct);
        return fechaAct;
    }

  @Test
  public void testPED01A()
  {
    try
    {
      fixture.setUpM01();
      Maquina maq = new Maquina(100004,"Flipper");
      maq.agregarMaterial(new Material(401,"Madera",500));
      maq.agregarMaterial(new Material(402,"Metal",500));
      Pedido ped = new Pedido(maq,500,new GregorianCalendar(2017,11,19));
      assertTrue("PED01A: No se registro el nro de Pedido correctamente",ped.getNroPedido()==105);
      assertEquals("PED01A: No se registro la fecha de pedido correctamente",
                   ped.getFechaPedido(), fechaActual());
      assertEquals("PED01A: No se registro la fecha de entrega correctamente",
                   ped.getFechaEntrega(), new GregorianCalendar(2017,11,19));
      assertEquals("PED01A: No se registro la maquina correctamente",ped.getMaquina(),maq);
      assertTrue("PED01A: No se registro la cantidad correctamente",ped.getCantidad()==500);
      assertTrue("PED01A: No se registro el estado correctamente",ped.getEstado()==Pedido.INICIADO);
      assertTrue("PED01A: No se registro el nro de Lote correctamente",ped.getNroLote()==-1);
    }
    catch (EmpresaException e)
    {
      fail("PED01A: Excepcion inesperada: " + e.toString());
    }
  }
  
  @Test
  public void testPED01B()
  {
    try
    {
      fixture.setUpM01();
      Maquina maq = new Maquina(100004,"Flipper");
      maq.agregarMaterial(new Material(401,"Madera",500));
      maq.agregarMaterial(new Material(402,"Metal",500));
      Pedido ped = new Pedido(maq,1,new GregorianCalendar(2017,11,19));
      assertTrue("PED01B: No se registro el nro de Pedido correctamente",ped.getNroPedido()==105);
      assertEquals("PED01B: No se registro la fecha de pedido correctamente",
                   ped.getFechaPedido(), fechaActual());
      assertEquals("PED01B: No se registro la fecha de entrega correctamente",
                   ped.getFechaEntrega(), new GregorianCalendar(2017,11,19));
      assertEquals("PED01B: No se registro la maquina correctamente",ped.getMaquina(),maq);
      assertTrue("PED01B: No se registro la cantidad correctamente",ped.getCantidad()==1);
      assertTrue("PED01B: No se registro el estado correctamente",ped.getEstado()==Pedido.INICIADO);
      assertTrue("PED01B: No se registro el nro de Lote correctamente",ped.getNroLote()==-1);
    }
    catch (EmpresaException e)
    {
      fail("PED01B: Excepcion inesperada: " + e.toString());
    }
  }
  
  @Test
  public void testPED01C()
  {
    try
    {
      fixture.setUpM01();
      Maquina maq = new Maquina(100004,"Flipper");
      maq.agregarMaterial(new Material(401,"Madera",500));
      maq.agregarMaterial(new Material(402,"Metal",500));
      Pedido ped = new Pedido(maq,998,new GregorianCalendar(2017,11,19));
      assertTrue("PED01C: No se registro el nro de Pedido correctamente",ped.getNroPedido()==105);
      assertEquals("PED01C: No se registro la fecha de pedido correctamente",
                   ped.getFechaPedido(), fechaActual());
      assertEquals("PED01C: No se registro la fecha de entrega correctamente",
                   ped.getFechaEntrega(), new GregorianCalendar(2017,11,19));
      assertTrue("PED01C: No se registro la cantidad correctamente",ped.getCantidad()==998);
      assertTrue("PED01C: No se registro el estado correctamente",ped.getEstado()==Pedido.INICIADO);
      assertTrue("PED01C: No se registro el nro de Lote correctamente",ped.getNroLote()==-1);
    }
    catch (EmpresaException e)
    {
      fail("PED01C: Excepcion inesperada: " + e.toString());
    }
  }

  @Test
  public void testPED01D()
  {
    boolean assertError = false;
    try
    {
      fixture.setUpM01();
      Pedido ped = new Pedido(null,500,new GregorianCalendar(2017,11,19));
    }
    catch (AssertionError e)
    {
      assertError = true; 
    }
    if(!assertError)
      fail("PED01D: Se esperaba un error de assercion");
  }

  @Test
  public void testPED01E()
  {
    boolean assertError = false;
    try
    {
      fixture.setUpM01();
      Maquina maq = new Maquina(100004,"Flipper");
      maq.agregarMaterial(new Material(401,"Madera",500));
      maq.agregarMaterial(new Material(402,"Metal",500));
      Pedido ped = new Pedido(maq,-200,new GregorianCalendar(2017,11,19));
    }
    catch (EmpresaException e)
    {
      fail("PED01E: Excepcion inesperada: " + e.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("PED01E: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED01F()
  {
    boolean assertError = false;
    try
    {
      fixture.setUpM01();
      Maquina maq = new Maquina(100004,"Flipper");
      maq.agregarMaterial(new Material(401,"Madera",500));
      maq.agregarMaterial(new Material(402,"Metal",500));
      Pedido ped = new Pedido(maq,0,new GregorianCalendar(2017,11,19));
    }
    catch (EmpresaException e)
    {
      fail("PED01F: Excepcion inesperada: " + e.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("PED01F: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED01G()
  {
    boolean assertError = false;
    try
    {
      fixture.setUpM01();
      Maquina maq = new Maquina(100004,"Flipper");
      maq.agregarMaterial(new Material(401,"Madera",500));
      maq.agregarMaterial(new Material(402,"Metal",500));
      Pedido ped = new Pedido(maq,1500,new GregorianCalendar(2017,11,19));
    }
    catch (EmpresaException e)
    {
      fail("PED01G: Excepcion inesperada: " + e.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("PED01G: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED01H()
  {
    boolean assertError = false;
    try
    {
      fixture.setUpM01();
      Maquina maq = new Maquina(100004,"Flipper");
      maq.agregarMaterial(new Material(401,"Madera",500));
      maq.agregarMaterial(new Material(402,"Metal",500));
      Pedido ped = new Pedido(maq,1000,new GregorianCalendar(2017,11,19));
    }
    catch (EmpresaException e)
    {
      fail("PED01H: Excepcion inesperada: " + e.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("PED01H: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED01I()
  {
    boolean assertError = false;
    try
    {
      fixture.setUpM01();
      Maquina maq = new Maquina(100004,"Flipper");
      maq.agregarMaterial(new Material(401,"Madera",500));
      maq.agregarMaterial(new Material(402,"Metal",500));
      Pedido ped = new Pedido(maq,500,null);
    }
    catch (EmpresaException e)
    {
      fail("PED01I: Excepcion inesperada: " + e.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("PED01I: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED02A()
  {
    fixture.setUpM02A();
    HashMap<Integer, Material> materiales = new HashMap<Integer, Material>();
    materiales = fixture.getPedidoTest().materialesNecesarios();
    assertTrue("PED02A: La lista de materiales no es nula.",materiales.isEmpty());
  }
  
  @Test
  public void testPED02B()
  {
    fixture.setUpM02B();
    HashMap<Integer, Material> materiales = new HashMap<Integer, Material>();
    materiales = fixture.getPedidoTest().materialesNecesarios();
    assertFalse("PED02B: La lista de materiales es nula.",materiales.isEmpty());
    assertTrue("PED02B: La cantidad del material no se registro correctamente.",materiales.get(401).getCantidad()==1000);
  }
  
  @Test
  public void testPED02C()
  {
    fixture.setUpM02C();
    HashMap<Integer, Material> materiales = new HashMap<Integer, Material>();
    materiales = fixture.getPedidoTest().materialesNecesarios();
    assertFalse("PED02C: La lista de materiales es nula.",materiales.isEmpty());
    assertTrue("PED02C: La cantidad del material no se registro correctamente.",materiales.get(401).getCantidad()==1000);
    assertTrue("PED02C: La cantidad del material no se registro correctamente.",materiales.get(402).getCantidad()==1500);
    assertTrue("PED02C: La cantidad del material no se registro correctamente.",materiales.get(403).getCantidad()==25000);
  }
  
  @Test
  public void testPED03A()
  {
    fixture.setUpM03();
    fixture.getPedidoTest().estadoEvaluacion(new GregorianCalendar(2017,11,25));
    assertNotNull("PED03A: La fecha propuesta por produccion es nula.",fixture.getPedidoTest().getFechaPropProduccion());
    assertTrue("PED03A: El estado no se registro correctamente.",fixture.getPedidoTest().getEstado()==Pedido.EN_EVALUACION);
  }
  
  @Test
  public void testPED03B()
  {
    boolean assertError = false;
    try
    {
    fixture.setUpM03();
    fixture.getPedidoTest().estadoEvaluacion(null);
    
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
        fail("PED03B: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED03C()
  {
    boolean assertError = false;
    try
    {
    fixture.setUpM03C();
    fixture.getPedidoTest().estadoEvaluacion(new GregorianCalendar(2017,11,25));
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
        fail("PED03C: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED04A()
  {
    fixture.setUpM04();
    fixture.getPedidoTest().estadoAceptado(new GregorianCalendar(2017,11,25));
    assertNotNull("PED04A: La fecha definitiva es nula.",fixture.getPedidoTest().getFechaDefinitiva());
    assertNotNull("PED04A: La fecha de aceptacion es nula.",fixture.getPedidoTest().getFechaAceptacion());
    assertTrue("PED04A: El estado no se registro correctamente.",fixture.getPedidoTest().getEstado()==Pedido.ACEPTADO);
    assertTrue("PED04A: El nro de lote no se registro correctamente.",fixture.getPedidoTest().getNroLote()==6);
  }
  
  @Test
  public void testPED04B()
  {
    boolean assertError = false;
    try
    {
    fixture.setUpM04();
    fixture.getPedidoTest().estadoAceptado(null);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
        fail("PED04B: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED04C()
  {
    boolean assertError = false;
    try
    {
    fixture.setUpM04C();
    fixture.getPedidoTest().estadoAceptado(new GregorianCalendar(2017,11,25));
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
        fail("PED04C: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED05A()
  {
    fixture.setUpM05A();
    int cantObs = fixture.getPedidoTest().getListaObservaciones().size();
    Observacion obs = new Observacion("OTROS", 2, "Prueba");
    fixture.getPedidoTest().estadoCancelado(obs);
    assertTrue("PED05A: El estado no se registro correctamente.",
               fixture.getPedidoTest().getEstado() == Pedido.CANCELADO);
    assertTrue("PED05A: La observacion de cancelacion no se agrego.", 
               fixture.getPedidoTest().getListaObservaciones().size() == cantObs + 1);
  }
  
  @Test
  public void testPED05B()
  {
    fixture.setUpM05B();
    int cantObs = fixture.getPedidoTest().getListaObservaciones().size();
    Observacion obs = new Observacion("OTROS", 2, "Prueba");
    fixture.getPedidoTest().estadoCancelado(obs);
    assertTrue("PED05B: El estado no se registro correctamente.",
               fixture.getPedidoTest().getEstado() == Pedido.CANCELADO);
    assertTrue("PED05B: La observacion de cancelacion no se agrego.",
               fixture.getPedidoTest().getListaObservaciones().size() == cantObs + 1);
  }
  
  @Test
  public void testPED05C()
  {
    fixture.setUpM05B();
    int cantObs = fixture.getPedidoTest().getListaObservaciones().size();
    boolean assertError = false;
    try
    {
        fixture.getPedidoTest().estadoCancelado(null);
    }
    catch (AssertionError e)
    {
      assertError = true;
      assertTrue("PED05C: No se debia agregar la observacion",
                 cantObs == fixture.getPedidoTest().getListaObservaciones().size());
      assertTrue("PED05C: Se modifico el estado",
                 fixture.getPedidoTest().getEstado() == Pedido.EN_EVALUACION);
    }
    if(!assertError)
        fail("PED05C: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED05D(){
      fixture.setUpM05C();
      int cantObs = fixture.getPedidoTest().getListaObservaciones().size();
      Observacion obs = new Observacion("OTROS", 2, "Prueba");
      boolean assertError = false;
      try
      {
          fixture.getPedidoTest().estadoCancelado(obs);
      }
      catch (AssertionError e)
      {
        assertError = true;
        assertTrue("PED05D: No se debia agregar la observacion",
                   cantObs == fixture.getPedidoTest().getListaObservaciones().size());
        assertTrue("PED05D: Se modifico el estado",
                   fixture.getPedidoTest().getEstado() == Pedido.ACEPTADO);
      }
      if(!assertError)
          fail("PED05D: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED05E(){
      fixture.setUpM05D();
      int cantObs = fixture.getPedidoTest().getListaObservaciones().size();
      Observacion obs = new Observacion("OTROS", 2, "Prueba");
      boolean assertError = false;
      try
      {
          fixture.getPedidoTest().estadoCancelado(obs);
      }
      catch (AssertionError e)
      {
        assertError = true;
        assertTrue("PED05E: No se debia agregar la observacion",
                   cantObs == fixture.getPedidoTest().getListaObservaciones().size());
        assertTrue("PED05E: Se modifico el estado",
                   fixture.getPedidoTest().getEstado() == Pedido.CANCELADO);
      }
      if(!assertError)
          fail("PED05E: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED06A()
  {
    fixture.setUpM06A();
    try
    {
      Observacion obs = new Observacion("FECHAS", 1, "Modificar fecha");
      fixture.getPedidoTest().insertarObservacion(obs);
      assertTrue("PED06A: La observacion no se registro correctamente.",fixture.getPedidoTest().getListaObservaciones().contains(obs));
    }
    catch (EmpresaException e)
    {
      fail("PED06A: Excepcion inesperada: " + e.toString());
    }
  }
  
  @Test
  public void testPED06B()
  {
    boolean assertError = false;
    fixture.setUpM06B();
    try
    {
      Observacion obs = new Observacion("FECHA", 1, "Modificar fecha");
      fixture.getPedidoTest().insertarObservacion(obs);
    }
    catch (EmpresaException e)
    {
      fail("PED06B: Excepcion inesperada: " + e.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
        fail("PED06B: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED06C()
  {
    boolean assertError = false;
    fixture.setUpM06C();
    try
    {
      fixture.getPedidoTest().insertarObservacion(null);
    }
    catch (EmpresaException e)
    {
      fail("PED06C: Excepcion inesperada: " + e.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
        fail("PED06C: Se esperaba un error de assercion");
  }
  
  @Test
  public void testPED06D()
  {
    boolean assertError = false;
    fixture.setUpM06D();
    try
    {
      Observacion obs = new Observacion("FECHAS", 1, "Modificar fecha");
      fixture.getPedidoTest().insertarObservacion(obs);
    }
    catch (EmpresaException e)
    {
      assertTrue("La observacion se agrego a la lista.", fixture.getPedidoTest().getListaObservaciones().size()==3);
    }
  }
  
    @Test
    public void testPED07A()
    {
      fixture.setUpM07A();
      HashMap<Integer, Material> materiales = new HashMap<Integer, Material>();
      materiales = fixture.getPedidoTest().getMaquina().getListadoMateriales();
      assertEquals("PED07A: La lista de materiales no es nula.",fixture.getPedidoTest().listadoMateriales(),"");
    }
    
    @Test
    public void testPED07B()
    {
      fixture.setUpM07B();
      HashMap<Integer, Material> materiales = new HashMap<Integer, Material>();
      materiales = fixture.getPedidoTest().getMaquina().getListadoMateriales();
      Iterator<Material> it = materiales.values().iterator();
      String info = "";
      info += String.format("Cod: MAT%05d\t\t%-100.100s\t%4.3f" + System.lineSeparator(), 
                                401 , "Madera", 1000.0);
      assertEquals("PED07B: La lista de materiales no es la esperada",
                   fixture.getPedidoTest().listadoMateriales(),info);
    }
    
    @Test
    public void testPED07C()
    {
      fixture.setUpM07C();
      HashMap<Integer, Material> materiales = new HashMap<Integer, Material>();
      materiales = fixture.getPedidoTest().getMaquina().getListadoMateriales();
      Iterator<Material> it = materiales.values().iterator();
      String info = "";
      info += String.format("Cod: MAT%05d\t\t%-100.100s\t%4.3f" + System.lineSeparator(), 
                                  401 , "Madera", 1000.0);
      info += String.format("Cod: MAT%05d\t\t%-100.100s\t%4.3f" + System.lineSeparator(), 
                                  402 , "Metal", 1500.0);
      info += String.format("Cod: MAT%05d\t\t%-100.100s\t%4.3f" + System.lineSeparator(), 
                                  403 , "Clavos", 25000.0);
      assertEquals("PED07C: La lista de materiales no es la esperada.",
                   fixture.getPedidoTest().listadoMateriales(),info);
    }
    
    
}
