package TestCajaBlanca;

import empresa.Material;

import org.junit.After;
import static org.junit.Assert.*;;
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
    assertTrue("MAT01A: El objeto ingresado no es la misma instancia.", fixture.getMaterialTest().equals(fixture.getMaterialTest()));
  }
  
  @Test
  public void testMAT01B()
  {
    assertFalse("MAT01B: El objeto ingresado es un Material.", fixture.getMaterialTest().equals(new Object()));
  }
  
  @Test
  public void testMAT01C()
  {
    Material mat1 = new Material(401, "Madera", 500);
    Material mat2 = new Material(402, "Madera", 500);
    assertTrue("MAT01C: El objeto ingresado no es el mismo material.", fixture.getMaterialTest().equals(new Object()));
    assertFalse("MAT01C: El objeto ingresado es el mismo material.", fixture.getMaterialTest().equals(new Object()));
  }
}
