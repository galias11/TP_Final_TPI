package TestCajaBlanca;

import empresa.EmpresaException;
import empresa.Material;
import empresa.Observacion;
import empresa.Operacion;

import empresa.Pedido;

import java.util.HashMap;

import javax.swing.JOptionPane;

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
      boolean assertError = false;
      try
      {
        HashMap<Integer, Material> faltante=fixture.getEmpresaTest().consultaFaltantes(10);
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
  public void testEMP01B()
  {
      fixture.setUpM01B();
      boolean assertError = false;
      try
      {
        HashMap<Integer, Material> faltante=fixture.getEmpresaTest().consultaFaltantes(10);
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
  public void testEMP01C()
  {
    fixture.setUpM01C();
        try {
            HashMap<Integer, Material> faltante=fixture.getEmpresaTest().consultaFaltantes(10);
            assertTrue("La lista de materiales deberia ser vacía", faltante.isEmpty());
            
        } catch (EmpresaException e) {
        }

    }

  @Test
  public void testEMP01D()
  {
      fixture.setUpM01D();
      try {
          HashMap<Integer, Material> faltante=fixture.getEmpresaTest().consultaFaltantes(10);
          assertTrue("La lista de materiales deberia ser vacía", !faltante.isEmpty());
          
      } catch (EmpresaException e) {
      }
  }

  @Test
  public void testEMP01E()
  {
      fixture.setUpM01E();
      try {
          HashMap<Integer, Material> faltante=fixture.getEmpresaTest().consultaFaltantes(10);
          assertTrue("La lista de materiales deberia ser vacía", faltante.isEmpty());
          
      } catch (EmpresaException e) {
      }
  }

  @Test
  public void testEMP01F()
  {
      fixture.setUpM01F();
      try {
          HashMap<Integer, Material> faltante=fixture.getEmpresaTest().consultaFaltantes(10);
          assertTrue("La lista de materiales deberia ser vacía", !faltante.isEmpty());
          
      } catch (EmpresaException e) {
      }
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
      assertTrue("EMP02B: El estado del pedido es iniciado o en evaluacion.",
                 !(fixture.getEmpresaTest().getPedidos().get(5).getEstado() == 0 ||
                 fixture.getEmpresaTest().getPedidos().get(5).getEstado() == 1));
      
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
      fixture.setUpM03B();
        try {
            fixture.getEmpresaTest().reservarMateriales(10);
            Material aux= new Material(1,"Material1",0);
            assertTrue("No se redujo el inventario correctamente", fixture.getEmpresaTest().getInventario().get(aux).getCantidad()==250.0);
        } catch (EmpresaException e) {
        }

    }

  @Test
  public void testEMP03C()
  {
      fixture.setUpM03B();
        try {
            fixture.getEmpresaTest().reservarMateriales(10);
            Material aux= new Material(1,"Material1",0);
            assertTrue("No se redujo el inventario correctamente", fixture.getEmpresaTest().getInventario().get(aux).getCantidad()==500.0);
        } catch (EmpresaException e) {
        }
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
      String str2= (new Observacion(Observacion.TEMA_FECHAS,1,"obs")).toString();
      str2 += System.lineSeparator();
      assertTrue("EMP04C: La descripcion es vacia.",str.compareTo(str2)==0);
    }
    catch (EmpresaException e)
    {
      fail("EMP04C: Excepcion inesperada: " + e.toString());
    }
  }

}
