package TestCajaBlanca;

import empresa.EmpresaException;
import empresa.Observacion;
import empresa.Operacion;

import empresa.Pedido;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EmpresaTest
{
  private FixtureEmpresa fixture = new FixtureEmpresa();

  public EmpresaTest()
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
  public void testEMP01A()
  {

  }


  @Test
  public void testEMP01B()
  {

  }

  @Test
  public void testEMP01C()
  {

  }

  @Test
  public void testEMP01D()
  {

  }

  @Test
  public void testEMP01E()
  {

  }

  @Test
  public void testEMP01F()
  {

  }

  @Test
  public void testEMP02A()
  {
    try
    {
      fixture.getEmpresaTest().cancelarPedido(5, "motivo");
      fail("EMP02A: Se esperaba una excepcion");
    }
    catch (EmpresaException e)
    {
      assertTrue("EMP02A: La lista de pedidos contiene el pedido especificado.",
                 !fixture.getEmpresaTest().getPedidos().containsKey(5));
    }
  }


  @Test
  public void testEMP02B()
  {
    fixture.setUpM02B();
    try
    {
      fixture.getEmpresaTest().cancelarPedido(5, "motivo");
      fail("EMP02B: Se esperaba una excepcion");
    }
    catch (EmpresaException e)
    {
      assertTrue("EMP02A: El estado del pedido es iniciado o en evaluacion.",
                 fixture.getEmpresaTest().getPedidos().get(5).getEstado() == Pedido.INICIADO ||
                 fixture.getEmpresaTest().getPedidos().get(5).getEstado() == Pedido.EN_EVALUACION);
    }
  }

  @Test
  public void testEMP02C()
  {
    fixture.setUpM02C();
    try
    {
      fixture.getEmpresaTest().cancelarPedido(5, "motivo");
      assertTrue("EMP02C: El pedido no se ha cancelado.",
                 fixture.getEmpresaTest().getPedidos().get(5).getEstado() == Pedido.CANCELADO);
    }
    catch (EmpresaException e)
    {
      fail("EMP02C: Excepcion inesperada: " + e.toString());
    }
  }

  @Test
  public void testEMP03A()
  {
    fixture.setUpM03A();
    boolean assertError = false;
    try
    {
      fixture.getEmpresaTest().reservarMateriales(10);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    catch (EmpresaException e)
    {
      assertError = true;
    }
    if (!assertError)
      fail("Se esperaba un error de asercion");
  }

  @Test
  public void testEMP03B()
  {

  }

  @Test
  public void testEMP03C()
  {

  }

  @Test
  public void testEMP04A()
  {
    try
    {
      String str = fixture.getEmpresaTest().listarObservaciones(5);
      fail("EMP02A: Se esperaba una excepcion");
    }
    catch (EmpresaException e)
    {
      assertTrue("EMP02A: La lista de pedidos contiene el pedido especificado.",
                 !fixture.getEmpresaTest().getPedidos().containsKey(5));
    }
  }


  @Test
  public void testEMP04B()
  {
    fixture.setUpM04B();
    try
    {
      String str = fixture.getEmpresaTest().listarObservaciones(5);
      assertTrue("EMP04B: La descripcion no es vacia.",str=="");
    }
    catch (EmpresaException e)
    {
      fail("EMP04B: Excepcion inesperada: " + e.toString());
    }
  }

  @Test
  public void testEMP04C()
  {
    fixture.setUpM04C();
    try
    {
      String str = fixture.getEmpresaTest().listarObservaciones(5);
      assertTrue("EMP04C: La descripcion no es vacia.",str==(new Observacion(Observacion.TEMA_FECHAS,1,"obs")).toString());
    }
    catch (EmpresaException e)
    {
      fail("EMP04C: Excepcion inesperada: " + e.toString());
    }
  }

}
