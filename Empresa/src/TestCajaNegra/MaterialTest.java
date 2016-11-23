package TestCajaNegra;

import empresa.EmpresaException;
import empresa.Maquina;

import empresa.Material;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class MaterialTest
{

  private FixtureMaterial fixture = new FixtureMaterial();

  public MaterialTest()
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
  public void testMAT01A()
  {
    fixture.setUpM01();
    Material mat = new Material(401, "Madera", 500);
    assertTrue("MAT01A: No se registro codigo correctamente", mat.getCodigoMaterial() == 401);
    assertEquals("MAT01A: No se registro descricion correctamente", "Madera", mat.getDescripcion());
    assertTrue("MAT01A: No se registro cantidad correctamente", mat.getCantidad() == 500);
  }

  @Test
  public void testMAT01B()
  {
    fixture.setUpM01();
    Material mat = new Material(1, "M", 1);
    assertTrue("MAT01B: No se registro codigo correctamente", mat.getCodigoMaterial() == 1);
    assertEquals("MAT01B: No se registro descricion correctamente", "M", mat.getDescripcion());
    assertTrue("MAT01B: No se registro cantidad correctamente", mat.getCantidad() == 1);
  }

  @Test
  public void testMAT01C()
  {
    fixture.setUpM01();
    Material mat =
      new Material(99999,
                   "MaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaa",
                   1);
    assertTrue("MAT01C: No se registro codigo correctamente", mat.getCodigoMaterial() == 99999);
    assertEquals("MAT01C: No se registro descricion correctamente",
                 "MaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaa",
                 mat.getDescripcion());
    assertTrue("MAT01C: No se registro cantidad correctamente", mat.getCantidad() == 1);
  }

  @Test
  public void testMAT01D()
  {
    fixture.setUpM01();
    boolean assertError = false;
    try
    {
      Material mat = new Material(-320, "Madera", 500);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT01D: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT01E()
  {
    fixture.setUpM01();
    boolean assertError = false;
    try
    {
      Material mat = new Material(320000, "Madera", 500);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT01E: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT01F()
  {
    fixture.setUpM01();
    boolean assertError = false;
    try
    {
      Material mat = new Material(0, "Madera", 500);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT01F: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT01G()
  {
    fixture.setUpM01();
    boolean assertError = false;
    try
    {
      Material mat = new Material(100000, "Madera", 500);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT01G: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT01H()
  {
    fixture.setUpM01();
    boolean assertError = false;
    try
    {
      Material mat = new Material(401, null, 500);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT01H: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT01I()
  {
    fixture.setUpM01();
    boolean assertError = false;
    try
    {
      Material mat = new Material(401, "", 500);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT01I: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT01J()
  {
    fixture.setUpM01();
    boolean assertError = false;
    try
    {
      Material mat =
        new Material(401,
                     "MaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaa",
                     1);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT01J: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT01K()
  {
    fixture.setUpM01();
    boolean assertError = false;
    try
    {
      Material mat =
        new Material(401,
                     "MaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaMaderaaaaaa",
                     1);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT01K: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT01L()
  {
    fixture.setUpM01();
    boolean assertError = false;
    try
    {
      Material mat = new Material(401, "Madera", -500);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT01L: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT01M()
  {
    fixture.setUpM01();
    boolean assertError = false;
    try
    {
      Material mat = new Material(401, "Madera", -1);
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT01M: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT02A()
  {
    fixture.setUpM02();
    try
    {
      fixture.getMaterialTest().registrarIngreso(100);
      assertTrue("MAT02A: Cantidad registrada incorrecta", fixture.getMaterialTest().getCantidad() == 600);
    }
    catch (EmpresaException ex)
    {
      fail("MAT02A: Exception inesperada: " + ex.toString());
    }
  }

  @Test
  public void testMAT02B()
  {
    fixture.setUpM02();
    try
    {
      fixture.getMaterialTest().registrarIngreso(1);
      assertTrue("MAT02B: Cantidad registrada incorrecta", fixture.getMaterialTest().getCantidad() == 501);
    }
    catch (EmpresaException ex)
    {
      fail("MAT02B: Exception inesperada: " + ex.toString());
    }
  }

  @Test
  public void testMAT02C()
  {
    fixture.setUpM02();
    boolean assertError = false;
    try
    {
      fixture.getMaterialTest().registrarIngreso(-100);
    }
    catch (EmpresaException ex)
    {
      fail("MAT02C: Exception inesperada: " + ex.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT02C: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT02D()
  {
    fixture.setUpM02();
    boolean assertError = false;
    try
    {
      fixture.getMaterialTest().registrarIngreso(0);
    }
    catch (EmpresaException ex)
    {
      fail("MAT02D: Exception inesperada: " + ex.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT02D: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT03A()
  {
    fixture.setUpM03();
    try
    {
      fixture.getMaterialTest().registrarRetiro(100);
      assertTrue("MAT03A: Cantidad registrada incorrecta", fixture.getMaterialTest().getCantidad() == 400);
    }
    catch (EmpresaException ex)
    {
      fail("MAT03A: Exception inesperada: " + ex.toString());
    }
  }

  @Test
  public void testMAT03B()
  {
    fixture.setUpM03();
    try
    {
      fixture.getMaterialTest().registrarRetiro(1);
      assertTrue("MAT03B: Cantidad registrada incorrecta", fixture.getMaterialTest().getCantidad() == 499);
    }
    catch (EmpresaException ex)
    {
      fail("MAT03B: Exception inesperada: " + ex.toString());
    }
  }

  @Test
  public void testMAT03C()
  {
    fixture.setUpM03();
    try
    {
      fixture.getMaterialTest().registrarRetiro(500);
      assertTrue("MAT03C: Cantidad registrada incorrecta", fixture.getMaterialTest().getCantidad() == 0);
    }
    catch (EmpresaException ex)
    {
      fail("MAT03C: Exception inesperada: " + ex.toString());
    }
  }

  @Test
  public void testMAT03D()
  {
    fixture.setUpM03();
    boolean assertError = false;
    try
    {
      fixture.getMaterialTest().registrarRetiro(-100);
    }
    catch (EmpresaException ex)
    {
      fail("MAT03D: Exception inesperada: " + ex.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT03D: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT03E()
  {
    fixture.setUpM03();
    boolean assertError = false;
    try
    {
      fixture.getMaterialTest().registrarRetiro(0);
      fail("MAT03E: Se esperaba un error de assercion");
    }
    catch (EmpresaException ex)
    {
      fail("MAT03E: Exception inesperada: " + ex.toString());
    }
    catch (AssertionError e)
    {
      assertError = true;
    }
    if(!assertError)
      fail("MAT03E: Se esperaba un error de assercion");
  }

  @Test
  public void testMAT03F()
  {
    fixture.setUpM03();
    boolean assertError = false;
    try
    {
      fixture.getMaterialTest().registrarRetiro(700);
    }
    catch (EmpresaException ex)
    {
      assertTrue("MAT03F: La cantidad del material ha cambiado.", fixture.getMaterialTest().getCantidad()==500);
    }
  }

  @Test
  public void testMAT03G()
  {
    fixture.setUpM03();
    boolean assertError = false;
    try
    {
      fixture.getMaterialTest().registrarRetiro(501);
    }
    catch (EmpresaException ex)
    {
      assertTrue("MAT03G: La cantidad del material ha cambiado.", fixture.getMaterialTest().getCantidad()==500);
    }
  }

  @Test
  public void testMAT04A()
  {
    fixture.setUpM04();
    assertTrue("MAT04A: Variable booleana incorrecta", fixture.getMaterialTest().satisfacePedido(100));
  }

  @Test
  public void testMAT04B()
  {
    fixture.setUpM04();
    assertTrue("MAT04B: Variable booleana incorrecta", fixture.getMaterialTest().satisfacePedido(500));
  }

  @Test
  public void testMAT04C()
  {
    fixture.setUpM04();
    assertFalse("MAT04C: Variable booleana incorrecta", fixture.getMaterialTest().satisfacePedido(700));
  }

  @Test
  public void testMAT04D()
  {
    fixture.setUpM04();
    assertFalse("MAT04D: Variable booleana incorrecta", fixture.getMaterialTest().satisfacePedido(501));
  }
}
