package TestCajaBlanca;

import empresa.EmpresaException;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class MaquinaTest
{
  private FixtureMaquina fixture = new FixtureMaquina();

  public MaquinaTest()
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
  public void testMAQ02A()
  {
    try
    {
      fixture.getMaquinaTest().modificarCantidadMaterial(401, 300);
      assertTrue("MAQ02A: No se registro la cantidad correctamente",fixture.getMaquinaTest().getListadoMateriales().get(401).getCantidad()==300);
    }
    catch (EmpresaException e)
    {
      fail("MAQ02A: Excepcion inesperada: " + e.toString());
    }
  }
  
  @Test
  public void testMAQ02B()
  {
    try
    {
      fixture.getMaquinaTest().modificarCantidadMaterial(402, 300);
      fail("MAQ02B: Se esperaba una excepcion.");
    }
    catch (EmpresaException e)
    {
      assertTrue("MAQ02B: La cantidad del material ha sido modificada.",fixture.getMaquinaTest().getListadoMateriales().get(401).getCantidad()==500);
    }
  }
}
