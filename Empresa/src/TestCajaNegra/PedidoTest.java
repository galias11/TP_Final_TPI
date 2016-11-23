package TestCajaNegra;

import empresa.EmpresaException;
import empresa.Maquina;
import empresa.Material;
import empresa.Observacion;
import empresa.Pedido;

import java.util.Calendar;

import java.util.GregorianCalendar;

import java.util.HashMap;

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

  @Test
  public void testPED01A()
  {
    try
    {
      fixture.setUpM01();
      Maquina maq = new Maquina(100004,"Flipper");
      maq.agregarMaterial(new Material(401,"Madera",500));
      maq.agregarMaterial(new Material(402,"Metal",500));
      Pedido ped = new Pedido(maq,500,new GregorianCalendar(2016,11,19));
      assertTrue("PED01A: No se registro el nro de Pedido correctamente",ped.getNroPedido()==105);
      assertEquals("PED01A: No se registro la maquina correctamente",ped.getFechaPedido().getInstance(),GregorianCalendar.getInstance());
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
      Pedido ped = new Pedido(maq,1,new GregorianCalendar(2016,11,19));
      assertTrue("PED01B: No se registro el nro de Pedido correctamente",ped.getNroPedido()==105);
      assertEquals("PED01B: No se registro la maquina correctamente",ped.getFechaPedido().getInstance(),GregorianCalendar.getInstance());
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
      Pedido ped = new Pedido(maq,998,new GregorianCalendar(2016,11,19));
      assertTrue("PED01C: No se registro el nro de Pedido correctamente",ped.getNroPedido()==105);
      assertEquals("PED01C: No se registro la maquina correctamente",ped.getFechaPedido().getInstance(),GregorianCalendar.getInstance());
      assertEquals("PED01C: No se registro la maquina correctamente",ped.getMaquina(),maq);
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
      Pedido ped = new Pedido(null,500,new GregorianCalendar(2016,11,19));
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
      Pedido ped = new Pedido(maq,-200,new GregorianCalendar(2016,11,19));
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
      Pedido ped = new Pedido(maq,0,new GregorianCalendar(2016,11,19));
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
      Pedido ped = new Pedido(maq,1500,new GregorianCalendar(2016,11,19));
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
      Pedido ped = new Pedido(maq,999,new GregorianCalendar(2016,11,19));
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
    fixture.getPedidoTest().estadoEvaluacion(new GregorianCalendar(2016,11,25));
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
    fixture.getPedidoTest().estadoEvaluacion(new GregorianCalendar(2016,11,25));
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
    fixture.getPedidoTest().estadoAceptado(new GregorianCalendar(2016,11,25));
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
    fixture.getPedidoTest().estadoCancelado();
    assertTrue("PED05A: El estado no se registro correctamente.",fixture.getPedidoTest().getEstado()==Pedido.CANCELADO);
  }
  
  @Test
  public void testPED05B()
  {
    fixture.setUpM05B();
    fixture.getPedidoTest().estadoCancelado();
    assertTrue("PED05B: El estado no se registro correctamente.",fixture.getPedidoTest().getEstado()==Pedido.CANCELADO);
  }
  
  @Test
  public void testPED05C()
  {
    boolean assertError = false;
    try
    {
    fixture.setUpM05C();
    fixture.getPedidoTest().estadoCancelado();
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
        fail("PED05C: Se esperaba un error de assercion");
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
}
