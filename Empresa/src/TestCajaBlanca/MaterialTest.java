package TestCajaBlanca;

import org.junit.After;
import org.junit.Before;

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
}
