package TestCajaNegra;

import empresa.EmpresaException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmpresaExceptionTest
{
  public EmpresaExceptionTest()
  {
    super();
  }

  @Before
  public void setUp()
  {

  }

  @After
  public void tearDown()
  {

  }

  @Test
  public void testEMEM01A()
  {
    EmpresaException empEx = new EmpresaException("Error");
    assertEquals("EMEM01A: La descripcion no se registro correctamente.", empEx.getMessage(), "Error");
  }

  @Test
  public void testEMEM01B()
  {
    boolean assertError = false;
    try
    {
      EmpresaException empEx = new EmpresaException(null);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if (!assertError)
      fail("EMEM01B: Se esperaba un error de assercion");
  }

  @Test
  public void testEMEM01C()
  {
    boolean assertError = false;
    try
    {
      EmpresaException empEx = new EmpresaException("");
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if (!assertError)
      fail("EMEM01C: Se esperaba un error de assercion");
  }
}
